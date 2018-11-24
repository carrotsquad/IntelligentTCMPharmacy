package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.model;

import android.util.Log;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.network.Api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Description: 基础Model每个Model都须继承
 * Created at: 2018/10/27 18:40
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
public abstract class BaseModel {

    private Retrofit retrofit;
    Api api;
    private OkHttpClient okHttpClient;
    private String baseUrl = "https://www.jintianlixia.cn";

    BaseModel() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override public void log(String message) {
                //打印retrofit日志
                Log.i("RetrofitLog","retrofitBack = "+message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                            .addInterceptor(loggingInterceptor)
                            .build();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
        api = retrofit.create(Api.class);
    }
}
