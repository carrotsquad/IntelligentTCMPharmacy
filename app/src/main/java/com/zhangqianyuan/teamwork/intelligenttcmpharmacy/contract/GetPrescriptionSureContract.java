package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.contract;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.GetPrescriptionSureFeedBackBean;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.interfaces.BaseView;

import retrofit2.Callback;

/**
 * Description 确认取药
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public interface GetPrescriptionSureContract {
    @FunctionalInterface
    interface  GetPrescriptionSureView extends BaseView{
        void isRight(boolean result,String reason);
    }

    @FunctionalInterface
    interface GetPrescriptionSurePresenter {
        void getPrescriptionSure(String tele,int id,int pId);
    }

    @FunctionalInterface
    interface GetPrescriptionSureModel {
        void getPrescriptionSure(String tele, int id, int pId, Callback<GetPrescriptionSureFeedBackBean> callback);
    }
}
