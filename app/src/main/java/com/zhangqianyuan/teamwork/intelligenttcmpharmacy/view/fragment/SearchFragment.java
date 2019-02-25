package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.fragment;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.R;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.lang.reflect.Field;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Description: 搜索fragment
 * Created at: 2018/10/21 11:20
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
//TODO:Tag选项，recyclerView
public class SearchFragment extends Fragment{


//    //药物简介
//    @BindView(R.id.introduction_content)
//    TextView introduction;
//
//    //生长环境
//    @BindView(R.id.growth_habit_content)
//    TextView  growth;
//
//    //药用价值
//    @BindView(R.id.medicinal_value_content)
//    TextView  way;
//
//    //特点
//    @BindView(R.id.character_content)
//    TextView  character;

    @BindView(R.id.et_searchMedical)
    SearchView search;

    @BindView(R.id.flowlayout)
    TagFlowLayout tagFlowLayout;

    private View view;
    private Context context;

    //Tags的文字
    private List<String> mVals;

    public static Fragment newInstance(){
        SearchFragment fragment = new SearchFragment();
        return fragment;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, null);
        context = getContext();
        initView(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void initView(View view){
        ButterKnife.bind(this,view);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        //TODO:待完善
        tagFlowLayout.setAdapter(new TagAdapter<String>(mVals) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                return null;
            }
        });

    }

    @Nullable
    @Override
    public Context getContext() {
        return context;
    }


}
