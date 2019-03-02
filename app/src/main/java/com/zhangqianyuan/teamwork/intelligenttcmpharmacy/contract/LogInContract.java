package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.contract;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.RegisterOrLogInFeedbackBean;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.interfaces.BaseView;

import io.reactivex.Observer;

/**
 * @Description: 登陆Contract
 * Created at: 2019/2/11 12:10
 * @author: zhangqianyuan
 * @Email: zhang.qianyuan@foxmail.com
 * @version:
 * @updateAuthor:
 * @updateDes:
 */

public interface LogInContract {
    @FunctionalInterface
    interface LogInView extends BaseView {
        void onResult(Boolean isright, String name,String info);
    }

    @FunctionalInterface
    interface LogInPresenter{
        void getLogIn(String tele, String pwd);
    }

    @FunctionalInterface
    interface LogInModel{
        void getLogIn(String tele, String pwd,Observer<RegisterOrLogInFeedbackBean> observer);
    }
}
