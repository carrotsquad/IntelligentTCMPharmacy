package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.contract;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.UpdateNickNameorPassWordBean;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.interfaces.BaseView;

import retrofit2.Call;
import retrofit2.Callback;
/**
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public interface UpdatePassWordContract {
    @FunctionalInterface
    interface updatePassWordView extends BaseView {
        void isRight(boolean result,String reason);
    }

    @FunctionalInterface
    interface updatePassWordPresenter {
        void updatePassWord(String tell,String newPassWord);
    }

    @FunctionalInterface
    interface updatePassWordModel{
        void updatePassWord(String tell, String newPassWord, Callback<UpdateNickNameorPassWordBean> callback);
    }
}
