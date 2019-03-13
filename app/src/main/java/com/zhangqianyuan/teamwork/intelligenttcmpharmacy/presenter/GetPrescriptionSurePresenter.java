package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.presenter;

import android.util.Log;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.GetPrescriptionSureFeedBackBean;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.contract.GetPrescriptionSureContract;
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
public class GetPrescriptionSurePresenter extends BasePresenter<GetPrescriptionSureContract.GetPrescriptionSureView> implements GetPrescriptionSureContract.GetPrescriptionSurePresenter {
 private GetPrescriptionSureContract.GetPrescriptionSureModel model;
 private static final String T = "GetPrescriptionSure";
 public GetPrescriptionSurePresenter(){
     model = (String tele, int id, int pId, Callback<GetPrescriptionSureFeedBackBean > callback)->{
         Api a  = new BaseModel().getApi();
         a.getPrescriptionSure(tele,id,pId).enqueue(callback);
     };
 }

    @Override
    public void getPrescriptionSure(String tele, int id, int pId) {
        if (isAttachActivity()){
            model.getPrescriptionSure(tele, id, pId, new Callback<GetPrescriptionSureFeedBackBean>() {
                @Override
                public void onResponse(Call<GetPrescriptionSureFeedBackBean> call, Response<GetPrescriptionSureFeedBackBean> response) {
                    if (response.body()!=null){
                        v.isRight(response.body().isResult(),response.body().getReason());
                        Log.d(T,"成功了"+response.body().toString());
                    }else
                        Log.d(T,"失败了");
                }

                @Override
                public void onFailure(Call<GetPrescriptionSureFeedBackBean> call, Throwable t) {
                       Log.d(T,"连接错误"+t.toString());
                }
            });
        }
    }
}
