package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.fragment;


import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.R;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.adapter.SearchItemAdapter;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.DrugSearchBean;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.Medicine;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.contract.SearchContract;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.presenter.MedicineSearchPresenter;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.finalteam.toolsfinal.Logger;


/**
 * Description: 搜索fragment
 * Created at: 2018/10/21 11:20
 *
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
//TODO:Tag选项，recyclerView
public class SearchFragment extends Fragment implements SearchContract.SearchView {

    @BindView(R.id.et_searchMedical)
    SearchView search;

    @BindView(R.id.flowlayout)
    TagFlowLayout historySearchTagFlowLayout;

    @BindView(R.id.erv)
    EasyRecyclerView easyRecyclerView;

    private SearchItemAdapter searchItemAdapter;

    private View view;
    private Context context;

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
                TextView textView=new TextView(parent.getContext());
                textView.setText(s);
                return textView;
            }
        };
        findSearchTagAdapter= new TagAdapter<String>(findSearchVals) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView textView=new TextView(parent.getContext());
                textView.setText(s);
                return textView;
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

        //TODO:待完善
        searchItemAdapter.setOnItemClickListener(position->{

        });

        search.setSubmitButtonEnabled(true);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if ("".equals(newText)) {

                } else {

                }
                return false;
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
                searchItemAdapter.add(e);
                drugnamelist.add(e.getMedicineName());});
            searchItemAdapter.notifyDataSetChanged();
            Log.e("SearchFragment", drugnamelist.toString());
            Log.e("SearchFragment", medicineArrayList.get(0).getMedicinePic());
            Log.e("SearchFragment", medicineArrayList.get(0).getIntro());
        } else {
            Toast.makeText(getContext(), "获取药材信息失败", Toast.LENGTH_SHORT).show();
        }
    }
}
