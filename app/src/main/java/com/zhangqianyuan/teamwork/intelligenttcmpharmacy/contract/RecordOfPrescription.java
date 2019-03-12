package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.contract;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.Prescription;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.RecordOfTakingMadecine;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.interfaces.BaseView;

import java.util.List;
import retrofit2.Callback;

/**
 * Description 取药记录
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public interface RecordOfPrescription {
    @FunctionalInterface
    interface  RecordOfPrescriptionView extends BaseView{
        void isRightAndGetInfo(boolean result, String reason, List<Prescription> prescriptionList);
    }

    @FunctionalInterface
    interface RecordOfPrescriptionPresenter {
        void getRecordOfPrescription(String tele);
    }

    @FunctionalInterface
    interface RecordOfPrescriptionModel{
        void getRecordOfPrescription(String tele, Callback<RecordOfTakingMadecine> callback);
    }
}
