package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.R;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.adapter.SearchItemAdapter;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.DrugSearchBean;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.Medicine;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.contract.SearchContract;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.presenter.MedicineSearchPresenter;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.util.system.ActivityManager;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;
import static com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.activity.LoginActivity.USER_HISTORYSEARCH;


/**
 * Description: 搜索fragment
 * Created at: 2018/10/21 11:20
 *
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
public class SearchFragment extends Fragment implements SearchContract.SearchView {

    @BindView(R.id.et_searchMedical)
    SearchView search;

    @BindView(R.id.flowlayout)
    TagFlowLayout historySearchTagFlowLayout;

    @BindView(R.id.erv)
    EasyRecyclerView easyRecyclerView;

    @BindView(R.id.historysearch_tv)
    TextView historysearch_tv;

    private PopupWindow mPopWindow;

    private SearchItemAdapter searchItemAdapter;

    private View view;
    private Context context;

    //全局药的list
    public static ArrayList<Medicine> medicineArrayList = new ArrayList<>();
    public static ArrayList<String> drugnamelist = new ArrayList<>();

    //Tags的文字
    private String[] historylist;
    private TagAdapter<String> historySearchTagAdapter;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private MedicineSearchPresenter searchPresenter;

    public static Fragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, null);
        context = getActivity();
        sharedPreferences = getActivity().getSharedPreferences("user", MODE_PRIVATE);
        ActivityManager.getActivityManager().addF(this);
        historylist = sharedPreferences.getString(USER_HISTORYSEARCH, "").split(" ");
        historySearchTagAdapter = new TagAdapter<String>(historylist) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flowlayout_tv, parent, false);
                TextView textView = view.findViewById(R.id.fl_tv);
                textView.setText(s);
                //如果为空，就设为GONE
                if(s.equals("")||s.equals(" ")){
                    view.setVisibility(View.GONE);
                }
                return view;
            }
        };

        searchPresenter = new MedicineSearchPresenter();
        searchPresenter.attachActivty(this);
        initView();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        searchPresenter.dettachActivity();
        super.onDestroyView();
    }

    private void initView() {
        ButterKnife.bind(this, view);
        searchItemAdapter = new SearchItemAdapter(getActivity());
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        easyRecyclerView.setAdapter(searchItemAdapter);
        easyRecyclerView.setRefreshing(false);
        searchItemAdapter.setOnItemClickListener(position -> {
            search.clearFocus();  //可以收起键盘
            showPopUpWindow(searchItemAdapter.getItem(position));
        });

        search.setSubmitButtonEnabled(true);
        search.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                setHistorySearchTagFlowLayoutInvisible();
                search.setFocusable(true);
            }
        });

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            //筛选过滤
            @Override
            public boolean onQueryTextChange(String newText) {
                searchItemAdapter.clear();
                editor = sharedPreferences.edit();
                StringBuilder history = new StringBuilder(sharedPreferences.getString(USER_HISTORYSEARCH, ""));
                String[] historylist = history.toString().split(" ");
                if ("".equals(newText)) {
                    searchItemAdapter.addAll(medicineArrayList);
                } else {
                    for (int i = 0; i < drugnamelist.size(); i++) {
                        int flag=0;
                        if (drugnamelist.get(i).contains(newText)) {
                            searchItemAdapter.add(medicineArrayList.get(i));
                            //判断原来是否在集合中
                            for (int j = 0; j < historylist.length; j++) {
                                if (historylist[j].equals(drugnamelist.get(i))){
                                    flag=1;
                                }
                            }
                            if (flag == 0) {
                                history.append(drugnamelist.get(i)).append(" ");
                            }
                        }
                    }
                    //更新历史搜索
                    editor.putString(USER_HISTORYSEARCH, history.toString());
                    editor.commit();
                }

                searchItemAdapter.notifyDataSetChanged();
                return true;
            }
        });

        historySearchTagFlowLayout.setAdapter(historySearchTagAdapter);
        //点击历史搜索
        historySearchTagFlowLayout.setOnTagClickListener((View v, int position, FlowLayout parent) -> {
//            int i = 0;
//            for (; i < searchItemAdapter.getAllData().size(); i++) {
//                if (searchItemAdapter.getItem(i).getMedicineName().equals(historylist[position])) {
//                    setHistorySearchTagFlowLayoutInvisible();
//                    break;
//                }
//            }
//            Medicine medicine = searchItemAdapter.getItem(i);
//            searchItemAdapter.clear();
//            searchItemAdapter.add(medicine);
//            searchItemAdapter.notifyDataSetChanged();
            search.setQuery(historylist[position],false);
            return false;
        });
        searchPresenter.getSearchResult();
    }

    @Nullable
    @Override
    public Context getContext() {
        return context;
    }


    /**
     * 获取药材结果
     * @param searchBean
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void acquireSearchResult(DrugSearchBean searchBean) {
        if (searchBean.getResult()) {
            medicineArrayList = searchBean.getMedicineList();
            medicineArrayList.forEach(e -> {
                drugnamelist.add(e.getMedicineName());
            });
            searchItemAdapter.addAll(medicineArrayList);
            searchItemAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getContext(), "获取药材信息失败", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 设置HistorySearchTagFlowLayout消失
     */
    private void setHistorySearchTagFlowLayoutInvisible(){
        //一旦点击就设置为GONE
        historysearch_tv.setVisibility(View.GONE);
        historySearchTagFlowLayout.setVisibility(View.GONE);
    }


    /**
     * 显示中药详情
     *
     * @param medicine
     */
    private void showPopUpWindow(Medicine medicine) {
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.drugdetail_layout, null);
        //获取屏幕高度
        WindowManager manager = getActivity().getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int height = outMetrics.heightPixels;
        mPopWindow = new PopupWindow(contentView,
                width - 100, height - 500, true);
        mPopWindow.setContentView(contentView);

        //设置数据
        TextView back = contentView.findViewById(R.id.back);
        TextView name = contentView.findViewById(R.id.medicine_name);
        TextView intro = contentView.findViewById(R.id.medicine_intro);
        ImageView img = contentView.findViewById(R.id.medicine_img);
        TextView notice = contentView.findViewById(R.id.medicine_notice);
        name.setText(medicine.getMedicineName());
        intro.setText(medicine.getIntro());
        notice.setText(medicine.getNotice());
        Glide.with(context).load(medicine.getMedicinePic()).into(img);
        back.setOnClickListener(v -> {
            mPopWindow.dismiss();
        });

        View rootview = LayoutInflater.from(context).inflate(R.layout.fragment_search, null);
        mPopWindow.setFocusable(true);
        mPopWindow.setAnimationStyle(R.style.PopUpWindowShow);
        mPopWindow.setTouchable(true);
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.showAtLocation(rootview, Gravity.CENTER, 0, 0);
    }

}
