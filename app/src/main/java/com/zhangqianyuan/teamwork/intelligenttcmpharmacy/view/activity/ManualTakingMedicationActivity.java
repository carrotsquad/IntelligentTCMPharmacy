package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.activity;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.R;

import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 手动取药界面
 */

public class ManualTakingMedicationActivity extends AppCompatActivity {
    @BindView(R.id.self_tack_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.self_take_med_search)
    SearchView  search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_taking_medication);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        initView();
//        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
    }

    public void initView(){
        //设置图标和 box 同时显示
        search.setIconifiedByDefault(false);
        //设置是否有确认搜索键
        search.setSubmitButtonEnabled(true);
        //设置背景
        search.setBackgroundColor(getResources().getColor(android.R.color.white));
        //设置是否默认展开
        search.onActionViewExpanded();
    }




}

