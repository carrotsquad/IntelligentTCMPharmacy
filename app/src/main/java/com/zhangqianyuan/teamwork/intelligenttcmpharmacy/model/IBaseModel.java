package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.model;

import android.content.Context;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.model.listener.IBaseListener;

public interface IBaseModel {
    void getInfo(Object object, Context context, IBaseListener iBaseListener);
}
