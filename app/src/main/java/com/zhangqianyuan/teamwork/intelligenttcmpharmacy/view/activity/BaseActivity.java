package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.activity;

import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.R;

public  class BaseActivity extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ZXingLibrary.initDisplayOpinion(this);
    }
}
