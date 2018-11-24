package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.presenter;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.RegisterOrLogInFeedbackBean;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.model.IRegisterModel;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.model.RegisterModel;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.interfaces.RegisterView;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Description: 注册的presenter
 * Created at: 2018/12/22 20:09
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
public class RegisterPresenter extends BasePresenter<RegisterView>{

    private RegisterView registerView;
    private IRegisterModel registerModel;

    public RegisterPresenter(){
        registerModel = new RegisterModel();
    }

    public void verifyPhonenumber(String phonenumber) {
        if(isAttachActivity()){
            registerModel.getVerify(phonenumber, new Observer<RegisterOrLogInFeedbackBean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(RegisterOrLogInFeedbackBean registerOrLogInFeedbackBean) {
                    if(registerOrLogInFeedbackBean==null){
                        registerView.verify(false,"网络错误");
                    }else if(!registerOrLogInFeedbackBean.getResult()){
                        registerView.verify(false,registerOrLogInFeedbackBean.getReason());
                    }else {
                        registerView.verify(true,null);
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

    public void register(String phonenumber, String password, String code, String name) {
        if(isAttachActivity()){
            registerModel.getSign(phonenumber, code, password, name, new Observer<RegisterOrLogInFeedbackBean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(RegisterOrLogInFeedbackBean registerOrLogInFeedbackBean) {
                    if(registerOrLogInFeedbackBean==null){
                        registerView.register(false,"网络错误");
                    }else if(!registerOrLogInFeedbackBean.getResult()){
                        registerView.register(false,registerOrLogInFeedbackBean.getReason());
                    }else {
                        registerView.register(true,null);
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
