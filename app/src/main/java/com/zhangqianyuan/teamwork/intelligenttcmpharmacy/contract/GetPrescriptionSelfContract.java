package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.contract;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.DrugAndWeight;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.SelfTakeMedBean;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.interfaces.BaseView;

import java.util.List;
import retrofit2.Callback;

/**
 * Description 自主抓药
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public interface GetPrescriptionSelfContract {
    @FunctionalInterface
    interface GetPrescriptionSelfView extends BaseView{
        void isRightAndReturnInfo(boolean result, String reason, int prescriptionId, double price, String Infor);
    }


    @FunctionalInterface
    interface  GetPrescriptionSelfPresenter {
        void GetPrescriptionSelf(String lable, boolean flag, List<DrugAndWeight> list);
    }

    @FunctionalInterface
    interface  GetPrescriptionSelfModel{
        void GetPrescriptionSelf(String lable, boolean flag, List<DrugAndWeight> list, Callback<SelfTakeMedBean> callback);
    }
}

