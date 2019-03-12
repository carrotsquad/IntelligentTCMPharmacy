package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.presenter;

import android.util.Log;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.RecordOfTakingMadecine;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.contract.RecordOfPrescription;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.model.BaseModel;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.network.Api;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.presenter.base.BasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class RecordOfPrescriptionPresenter extends BasePresenter<RecordOfPrescription.RecordOfPrescriptionView> implements RecordOfPrescription.RecordOfPrescriptionPresenter {
    private RecordOfPrescription.RecordOfPrescriptionModel model;
    private static final String T = "RecordOfPrescription";

    public RecordOfPrescriptionPresenter(){
        model = (String tele, Callback< RecordOfTakingMadecine > callback) ->{
            Api a = new BaseModel().getApi();
            a.getRecordOfPrescription(tele).enqueue(callback);
        };
    }


    @Override
    public void getRecordOfPrescription(String tele) {
        if (isAttachActivity()){
            model.getRecordOfPrescription(tele, new Callback<RecordOfTakingMadecine>() {
                @Override
                public void onResponse(Call<RecordOfTakingMadecine> call, Response<RecordOfTakingMadecine> response) {
                    if (response.body()!=null){
                        v.isRightAndGetInfo(response.body().isResult(),response.body().getReason(),response.body().getPrescriptionList());
                        Log.d(T,"成功了"+response.body().toString());
                    }else
                        Log.d(T,"response为空");
                }

                @Override
                public void onFailure(Call<RecordOfTakingMadecine> call, Throwable t) {
                    Log.d(T,"连接错误"+t.toString());
                }
            });
        }
    }
}
