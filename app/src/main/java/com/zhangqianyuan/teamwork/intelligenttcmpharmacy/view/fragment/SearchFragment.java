package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.fragment;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


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

    public static final String USER_HISTORYSEARCH = "userhistorysearch";

    //全局药的list
    public static ArrayList<Medicine> medicineArrayList = new ArrayList<>();
    public static ArrayList<String> drugnamelist = new ArrayList<>();

    //Tags的文字
    private ArrayList<String> historySearchVals=new ArrayList<>();

    private ArrayList<String> findSearchVals=new ArrayList<>();
    private TagAdapter<String> historySearchTagAdapter;

    private TagAdapter<String> findSearchTagAdapter;


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
        historySearchVals.add("当归");
        historySearchVals.add("枸杞");
        historySearchTagAdapter= new TagAdapter<String>(historySearchVals) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flowlayout_tv, parent, false);
                TextView textView = view.findViewById(R.id.fl_tv);
                textView.setText(s);
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
        searchItemAdapter=new SearchItemAdapter(getActivity());
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        easyRecyclerView.setAdapter(searchItemAdapter);
        easyRecyclerView.setRefreshing(false);

        searchItemAdapter.setOnItemClickListener(position->{
//            Log.e("Searchfragment",String.valueOf(searchItemAdapter.getCount()));
//            Log.e("Searchfragment",String.valueOf(position));
            showPopUpWindow(searchItemAdapter.getItem(position));
        });

        search.setSubmitButtonEnabled(true);

        search.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //一旦点击就设置为GONE
                historysearch_tv.setVisibility(View.GONE);
                historySearchTagFlowLayout.setVisibility(View.GONE);
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
                if ("".equals(newText)) {
                    searchItemAdapter.addAll(medicineArrayList);

                } else {
                    for (int i = 0; i < drugnamelist.size(); i++) {
                        if (drugnamelist.get(i).contains(newText)) {
                            searchItemAdapter.add(medicineArrayList.get(i));
                        }
                    }
                }
                searchItemAdapter.notifyDataSetChanged();
                return true;
            }
        });

        historySearchTagFlowLayout.setAdapter(historySearchTagAdapter);
        historySearchTagFlowLayout.setOnTagClickListener((View v, int position, FlowLayout parent)-> {

            return false;
            });
        searchPresenter.getSearchResult();
    }

    @Nullable
    @Override
    public Context getContext() {
        return context;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void acquireSearchResult(DrugSearchBean searchBean) {
        if (searchBean.getResult()) {
            medicineArrayList = searchBean.getMedicineList();
            medicineArrayList.forEach(e -> {
                drugnamelist.add(e.getMedicineName());});
            searchItemAdapter.addAll(medicineArrayList);
            searchItemAdapter.notifyDataSetChanged();
            Log.e("SearchFragment", drugnamelist.toString());
            Log.e("SearchFragment", medicineArrayList.get(0).getMedicinePic());
            Log.e("SearchFragment", medicineArrayList.get(0).getIntro());
        } else {
            Toast.makeText(getContext(), "获取药材信息失败", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 显示中药详情
     *
     * @param medicine
     */
    private void showPopUpWindow(Medicine medicine) {
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.drugdetail_layout, null);
        mPopWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        mPopWindow.setContentView(contentView);

        //设置数据
        Button back = contentView.findViewById(R.id.back);
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
        mPopWindow.setAnimationStyle(R.style.PopUpWindowShow);
        mPopWindow.setFocusable(true);
        mPopWindow.setTouchable(true);
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.showAtLocation(rootview, Gravity.CENTER, 0, 0);
    }

}
