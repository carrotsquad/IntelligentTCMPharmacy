package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.presenter;

import android.util.Log;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.UpdateNickNameorPassWordBean;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.contract.UpdateNickNameContract;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.model.BaseModel;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.network.Api;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.presenter.base.BasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author zhoudada
 * @version $Rev$
 * @des
 * @updateAuthor $Author$
 * @updateDes
 */
public class UpdateNickNamePresenter extends BasePresenter<UpdateNickNameContract.updateNickNameView> implements UpdateNickNameContract.updateNickNamePresenter{
    private UpdateNickNameContract.updateNickNameModel  model;
    public static final String T = "updateNickNamePresenter";

     public UpdateNickNamePresenter(){
        model = (String tell,String newName,Callback<UpdateNickNameorPassWordBean> callback) ->{
            Api api = new BaseModel().getApi();
             api.UpdateNickName(tell,newName).enqueue(callback);
        };
    }

    @Override
    public void updateNickName(String tell, String newName) {
        if (isAttachActivity()){
          model.updateNickName(tell,newName, new Callback<UpdateNickNameorPassWordBean>() {
                @Override
                public void onResponse(Call<UpdateNickNameorPassWordBean> call, Response<UpdateNickNameorPassWordBean> response) {
                    if (response.body()!=null){
                    v.isRight(response.body().getResult(),response.body().getReason());
                    Log.d(T,"成功了"+response.body());}
                    else
                        Log.d(T,"response is null");
                }

                @Override
                public void onFailure(Call<UpdateNickNameorPassWordBean> call, Throwable t) {
                    Log.d(T,"connect failure"+t.toString());
                }
            });
        }
    }


}
