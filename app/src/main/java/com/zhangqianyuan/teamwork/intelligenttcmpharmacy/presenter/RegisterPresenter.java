package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.presenter;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.RegisterOrLogInFeedbackBean;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.contract.RegisterContract;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.model.BaseModel;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.network.Api;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.presenter.base.BasePresenter;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Description: 注册的presenter
 * Created at: 2018/12/22 20:09
 *
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
public class RegisterPresenter extends BasePresenter<RegisterContract.RegisterView> implements RegisterContract.RegisterPresenter{
    private RegisterContract.RegisterModel registerModel;

    public RegisterPresenter() {
        registerModel = new RegisterContract.RegisterModel() {
            Api api = new BaseModel().getApi();

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
                api.getSign(phonenumber, password, code, name)
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(observer);
            }
        };
    }

    @Override
    public void verifyPhonenumber(String phonenumber) {
        if (isAttachActivity()) {
            registerModel.getVerify(phonenumber, new Observer<RegisterOrLogInFeedbackBean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(RegisterOrLogInFeedbackBean registerOrLogInFeedbackBean) {
                    if (registerOrLogInFeedbackBean == null) {
                        v.verify(false, "网络错误");
                    } else if (!registerOrLogInFeedbackBean.getResult()) {
                        v.verify(false, registerOrLogInFeedbackBean.getReason());
                    } else {
                        v.verify(true, "");
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

    @Override
    public void register(String phonenumber, String password, String code, String name) {
        if (isAttachActivity()) {
            registerModel.getSign(phonenumber, code, password, name, new Observer<RegisterOrLogInFeedbackBean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(RegisterOrLogInFeedbackBean registerOrLogInFeedbackBean) {
                    if (registerOrLogInFeedbackBean == null) {
                        v.register(false, "网络错误");
                    } else if (!registerOrLogInFeedbackBean.getResult()) {
                        v.register(false, registerOrLogInFeedbackBean.getReason());
                    } else {
                        v.register(true, "");
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
}
