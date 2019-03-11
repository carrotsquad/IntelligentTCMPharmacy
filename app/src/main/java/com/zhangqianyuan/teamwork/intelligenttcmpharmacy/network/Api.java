package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.network;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.DrugAndWeight;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.DrugSearchBean;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.GetPrescriptionAUTOBackBean;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.GetUserPictureBean;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.RegisterOrLogInFeedbackBean;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.SelfTakeMedBean;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.UpdateNickNameorPassWordBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

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

    @FormUrlEncoded
    @POST("/autoMedicine/changeName")
    Call<UpdateNickNameorPassWordBean> UpdateNickName(@Field("tele") String phonenumber,@Field("newName") String newName );

    @FormUrlEncoded
    @POST("/autoMedicine/changePassword")
    Call<UpdateNickNameorPassWordBean> UpdatePassWord(@Field("tele") String phonenumber,@Field("newPassword") String newPassWord);

    @GET("/autoMedicine/getMedicines")
    Observable<DrugSearchBean> getSearchResult();

    @POST("/autoMedicine/getUserPic")
    @FormUrlEncoded
    Call<GetUserPictureBean>   getUserPic(@Field("tele") String phonenumber);

    @POST("/autoMedicine/getPrescriptionSelf")
    @FormUrlEncoded
    Call<SelfTakeMedBean>  getPrescriptionSelf(@Field("label") String label, @Field("flag") boolean flag, @Field("prescription") List<DrugAndWeight> list);

    @POST("/autoMedicine/getPrescriptionAUTO")
    @FormUrlEncoded
    Call<GetPrescriptionAUTOBackBean> getPrescriptionAuto(@Field("disease") String disease);

}
