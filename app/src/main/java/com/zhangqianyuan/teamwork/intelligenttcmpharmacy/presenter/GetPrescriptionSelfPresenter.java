package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.presenter;

import android.graphics.ColorSpace;
import android.util.Log;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.DrugAndWeight;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.SelfTakeMedBean;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.contract.GetPrescriptionSelfContract;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.model.BaseModel;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.network.Api;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.presenter.base.BasePresenter;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Description 自主抓药presenter
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class GetPrescriptionSelfPresenter extends BasePresenter<GetPrescriptionSelfContract.GetPrescriptionSelfView> implements GetPrescriptionSelfContract.GetPrescriptionSelfPresenter {
   private  GetPrescriptionSelfContract.GetPrescriptionSelfModel model;
   public static final String T = "GetselfPresenter";

    public GetPrescriptionSelfPresenter(){
        model = (String lable, boolean flag, List<DrugAndWeight> list, Callback<SelfTakeMedBean> callback)->{
            Api api = new BaseModel().getApi();
            api.getPrescriptionSelf(lable,flag,list).enqueue(callback);
        };
    }


    @Override
    public void GetPrescriptionSelf(String lable, boolean flag, List<DrugAndWeight>   list) {
        if (isAttachActivity()){
            model.GetPrescriptionSelf(lable, flag, list, new Callback<SelfTakeMedBean>() {
                @Override
                public void onResponse(Call<SelfTakeMedBean> call, Response<SelfTakeMedBean> response) {
                    if (response.body()!=null){
                        v.isRightAndReturnInfo(response.body().isResult(),response.body().getReason(),response.body().getPrescriptionId(),
                                response.body().getPrice(),response.body().getInfor());
                        Log.d(T,"成功了"+response.body());
                    }else
                        Log.d(T,"失败了"+response.body());
                }

                @Override
                public void onFailure(Call<SelfTakeMedBean> call, Throwable t) {
                    Log.d(T,"连接错误"+t.toString());
                }
            });
        }
    }
}
