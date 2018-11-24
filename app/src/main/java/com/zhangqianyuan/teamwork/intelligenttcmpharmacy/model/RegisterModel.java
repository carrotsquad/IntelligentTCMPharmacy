package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.model;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.RegisterOrLogInFeedbackBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RegisterModel extends BaseModel implements IRegisterModel {

    public RegisterModel(){
        super();
    }


    @Override
    public void getVerify(String phonenumber, Observer<RegisterOrLogInFeedbackBean> observer) {
        api.getVerification(phonenumber)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void getSign(String phonenumber, String code, String password, String name, Observer<RegisterOrLogInFeedbackBean> observer) {
        api.getSign(phonenumber,password,code,name)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
