package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.presenter;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.RegisterOrLogInFeedbackBean;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.contract.LogInContract;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.model.BaseModel;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.network.Api;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.presenter.base.BasePresenter;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LogInPresenter extends BasePresenter<LogInContract.LogInView> implements LogInContract.LogInPresenter {

    private LogInContract.LogInModel logInModel;

    public LogInPresenter() {
        logInModel = (String tele, String pwd, Observer<RegisterOrLogInFeedbackBean> observer) ->{
                Api api = new BaseModel().getApi();
                api.getLogIn(tele, pwd)
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(observer);
        };
    }


    @Override
    public void getLogIn(String tele, String pwd) {
        logInModel.getLogIn(tele, pwd, new Observer<RegisterOrLogInFeedbackBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(RegisterOrLogInFeedbackBean registerOrLogInFeedbackBean) {
                if(isAttachActivity()){
                    if(registerOrLogInFeedbackBean==null){
                        v.onResult(false,"","发生了一点意外");
                    }else {
                        v.onResult(registerOrLogInFeedbackBean.getResult(),registerOrLogInFeedbackBean.getName(),registerOrLogInFeedbackBean.getReason());
                    }
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
