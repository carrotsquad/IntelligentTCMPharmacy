package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.R;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.interfaces.IBaseView;


/**
 * Description: 搜索fragment
 * Created at: 2018/10/21 11:20
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
// TODO: 2018/10/21 搜索功能，和服务器结合
public class SearchFragment extends Fragment implements IBaseView {


    private View view;
    private Context context;

    public static Fragment newInstance(){
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, null);
        context = getContext();

        initView();
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

    private void initView(){

    }

    @Nullable
    @Override
    public Context getContext() {
        return context;
    }


    @Override
    public void showInfo(Object object, Boolean issucced) {

    }
}
