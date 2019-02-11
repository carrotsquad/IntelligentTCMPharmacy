package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.network;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.RegisterOrLogInFeedbackBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Description: 网络api
 * Created at: 2018/12/22 20:10
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
public interface Api {

    @FormUrlEncoded
    @POST("/autoMedicine/verify")
    Observable<RegisterOrLogInFeedbackBean> getVerification(@Field("tele") String phonenumber);

    @FormUrlEncoded
    @POST("/autoMedicine/sign")
    Observable<RegisterOrLogInFeedbackBean> getSign(@Field("tele") String phonenumber,
                                                    @Field("password") String password,
                                                    @Field("code") String code,
                                                    @Field("name") String name);

    @FormUrlEncoded
    @POST("/autoMedicine/login")
    Observable<RegisterOrLogInFeedbackBean> getLogIn(@Field("tele") String phonenumber,@Field("password") String pwd);


}
