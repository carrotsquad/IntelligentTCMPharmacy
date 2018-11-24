package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.model;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.RegisterOrLogInFeedbackBean;

import io.reactivex.Observer;

public interface IRegisterModel {
    void getVerify(String phonenumber, Observer<RegisterOrLogInFeedbackBean> observer);
    void getSign(String phonenumber,String code,String password,String name, Observer<RegisterOrLogInFeedbackBean> observer);
}
