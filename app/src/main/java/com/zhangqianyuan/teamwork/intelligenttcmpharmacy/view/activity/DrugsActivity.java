package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 点击就诊后 的界面
 */
public class DrugsActivity extends AppCompatActivity {


    @BindView(R.id.trate_list)
    RecyclerView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drugs);
        ButterKnife.bind(this);
    }
}
