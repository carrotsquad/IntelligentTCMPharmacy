package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.contract;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.UpdateNickNameorPassWordBean;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.model.BaseModel;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.network.Api;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.interfaces.BaseView;

import retrofit2.Callback;

/**
 * @author zhoudada
 * @version $Rev$
 * @des
 * @updateAuthor $Author$
 * @updateDes
 */
public interface UpdateNickNameContract {
    @FunctionalInterface
    interface updateNickNameView extends BaseView{
        void  isRight(boolean result,String reason);
    }

    @FunctionalInterface
    interface updateNickNamePresenter{
        void  updateNickName(String tell, String newName);
    }

    @FunctionalInterface
    interface  updateNickNameModel{
        void  updateNickName(String tell, String newName, Callback<UpdateNickNameorPassWordBean> callback);
    }
}
