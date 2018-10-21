package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.R;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private String titles[] = new String[]{"主页", "药材百科", "个人中心"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);
        ButterKnife.bind(this);
    }
}
