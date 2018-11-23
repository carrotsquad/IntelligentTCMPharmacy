package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.util.network;


import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.Data;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.DrugInfo;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Description:
 * Created at: 2018/10/27 18:03
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
// TODO: 2018/10/27 api都写到这里
public interface Api {

    @POST("drug/info")
    @FormUrlEncoded
    Observable<Data<DrugInfo>> getDrugInfo(@Field("chineseName") String drugName);
}
