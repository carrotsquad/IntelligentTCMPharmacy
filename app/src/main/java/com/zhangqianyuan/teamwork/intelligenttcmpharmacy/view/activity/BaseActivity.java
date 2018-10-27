package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.R;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    public abstract  void initMVP();
    public abstract  void initView();
}
