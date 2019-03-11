package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.presenter;

import android.util.Log;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.GetPrescriptionAUTOBackBean;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.contract.GetPrescriptionAUTOContract;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.model.BaseModel;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.network.Api;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.presenter.base.BasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Description 自动取药presenter
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class GetPrescriptionAUTOPrensenter  extends BasePresenter<GetPrescriptionAUTOContract.GetPrescriptionAUTOiew> implements GetPrescriptionAUTOContract.GetPrescriptionAUTOPresenter {
    private GetPrescriptionAUTOContract.GetPrescriptionAUTOModel model;
    public static final String T = "GetPrescriptionAUTO";

    public GetPrescriptionAUTOPrensenter(){
        model  =(String disease, Callback<GetPrescriptionAUTOBackBean> callback)->{
            Api api = new BaseModel().getApi();
            api.getPrescriptionAuto(disease).enqueue(callback);
        };
    }


    @Override
    public void getPrescriptionAUTO(String disease) {
        if (isAttachActivity()){
            model.getPrescriptionAUTO(disease, new Callback<GetPrescriptionAUTOBackBean>() {
                @Override
                public void onResponse(Call<GetPrescriptionAUTOBackBean> call, Response<GetPrescriptionAUTOBackBean> response) {
                    if (response.body()!=null){
                        v.isRightAndGetInfo(response.body().isResult(),response.body().getPrescription(),response.body().getPrescriptionId(),response.body().getPrice());
                    }else{
                        Log.d(T,"失败了"+response.body());
                    }
                }

                @Override
                public void onFailure(Call<GetPrescriptionAUTOBackBean> call, Throwable t) {
                    Log.d(T,"连接错误"+t.toString());
                }
            });
        }
    }
}
