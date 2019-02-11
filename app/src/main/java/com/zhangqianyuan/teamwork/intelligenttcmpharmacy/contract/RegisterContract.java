package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.contract;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.RegisterOrLogInFeedbackBean;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.interfaces.BaseView;

import io.reactivex.Observer;

/**
 * @Description: 注册Contract
 * Created at: 2019/2/11 13:53
 * @author: zhangqianyuan
 * @Email: zhang.qianyuan@foxmail.com
 * @version:
 * @updateAuthor:
 * @updateDes:
 */
public interface RegisterContract {

    interface RegisterView extends BaseView {

        /**
         * 验证手机号
         * @param isright
         * @param info
         */
        void verify(Boolean isright,String info);

        /**
         * 注册
         * @param isright
         * @param info
         */
        void register(Boolean isright,String info);
    }


    interface RegisterPresenter{
        void verifyPhonenumber(String phonenumber);
        void register(String phonenumber, String password, String code, String name);
    }

    interface RegisterModel {
        void getVerify(String phonenumber, Observer<RegisterOrLogInFeedbackBean> observer);
        void getSign(String phonenumber,String code,String password,String name, Observer<RegisterOrLogInFeedbackBean> observer);
    }
}
