package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.contract;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.DrugAndWeight;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.GetPrescriptionAUTOBackBean;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.interfaces.BaseView;

import java.util.List;
import retrofit2.Callback;

/**
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public @interface GetPrescriptionAUTOContract {
    @FunctionalInterface
    interface GetPrescriptionAUTOiew extends BaseView{
        void isRightAndGetInfo(boolean result, List<DrugAndWeight> list,int prescriptionId,double price);
    }

    @FunctionalInterface
    interface GetPrescriptionAUTOPresenter {
        void getPrescriptionAUTO(String disease);
    }

    @FunctionalInterface
    interface GetPrescriptionAUTOModel {
        void getPrescriptionAUTO(String disease, Callback<GetPrescriptionAUTOBackBean> callback);
    }
}
