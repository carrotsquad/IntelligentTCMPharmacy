package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.presenter;

import android.util.Log;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.GetUserPictureBean;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.contract.GetUserPictureContract;
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
public class GetUserPicPresenter extends BasePresenter<GetUserPictureContract.GetUserPicView> implements GetUserPictureContract.GetUserPicPresenter {
    private GetUserPictureContract.GetUserPicModel  model;
    public  static final String  T = "GetUserPicPresenter";

    public GetUserPicPresenter(){
        model = (String tell,Callback<GetUserPictureBean> callback) ->{
            Api api = new BaseModel().getApi();
            api.getUserPic(tell).enqueue(callback);
        };
    }


    @Override
    public void getUserPic(String tell) {
        if (isAttachActivity()){
            model.getUserPic(tell, new Callback<GetUserPictureBean>() {
                @Override
                public void onResponse(Call<GetUserPictureBean> call, Response<GetUserPictureBean> response) {
                    if (response.body()!=null)
                        v.isRight(response.body().isResult(),response.body().getReason(),response.body().getUserPicUrl());
                    else
                        Log.d(T,"response is null");
                }

                @Override
                public void onFailure(Call<GetUserPictureBean> call, Throwable t) {
                    Log.d(T,"connect failure"+t.toString());
                }
            });
        }
    }
}
