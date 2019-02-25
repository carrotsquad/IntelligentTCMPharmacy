package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.presenter;

import android.util.Log;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.UpdateNickNameorPassWordBean;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.contract.UpdatePassWordContract;
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
public class UpdatePassWordPresenter extends BasePresenter<UpdatePassWordContract.updatePassWordView> implements UpdatePassWordContract.updatePassWordPresenter {
    private UpdatePassWordContract.updatePassWordModel model;
    public static final String T = "updatePassWordPresenter";

    public UpdatePassWordPresenter(){
        model = (String tell,String newPassword,Callback<UpdateNickNameorPassWordBean > callback) ->{
            Api api = new BaseModel().getApi();
            api.UpdateNickName(tell, newPassword).enqueue(callback);
        };
    }


    @Override
    public void updatePassWord(String tell, String newPassWord) {
        model.updatePassWord(tell, newPassWord, new Callback<UpdateNickNameorPassWordBean>() {
            @Override
            public void onResponse(Call<UpdateNickNameorPassWordBean> call, Response<UpdateNickNameorPassWordBean> response) {
                if (response.body()!=null)
                    v.isRight(response.body().getResult(),response.body().getReason());
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
