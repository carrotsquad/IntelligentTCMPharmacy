package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.model;

import android.content.Context;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.model.listener.IBaseListener;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.util.network.Api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.zhangqianyuan.teamwork.intelligenttcmpharmacy.util.network.AllUri.BaseUrl;

/**
 * Description: 基础Model每个Model都须继承,有需要的话iBaseListener可以向下转型
 * Created at: 2018/10/27 18:40
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
public abstract class BaseModel implements IBaseModel{

    private Retrofit retrofit;
    protected Api api;
    private OkHttpClient okHttpClient;

    @Override
    public void getInfo(Object object, Context context, IBaseListener iBaseListener) {
        okHttpClient = new OkHttpClient.Builder().build();
        retrofit  = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BaseUrl)
                .build();
        api = retrofit.create(Api.class);
    }
}
