package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.contract;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.GetUserPictureBean;
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
public interface GetUserPictureContract {
    @FunctionalInterface
    interface GetUserPicView extends BaseView{
        void isRight (boolean result,String reason,String picUrl);
    }

    @FunctionalInterface
    interface GetUserPicPresenter {
        void getUserPic(String tell);
    }

    @FunctionalInterface
    interface GetUserPicModel{
        void getUserPic(String tell, Callback<GetUserPictureBean> callback);
    }
}
