//package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.model;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//
//import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.model.listener.IBaseListener;
//
//import io.reactivex.schedulers.Schedulers;
//
//    public class MedecineModel extends BaseModel {
//        @SuppressLint("CheckResult")
//        @Override
//        public void getInfo(Object object, Context context, IBaseListener iBaseListener) {
//            super.getInfo(object, context, iBaseListener);
//            Api.getDrugInfo((String) object)
//                    .subscribeOn(Schedulers.io())
//                    .unsubscribeOn(Schedulers.io())
//                    .observeOn(Schedulers.io())
//                    .subscribe(drugInfoData -> {
//                        if (drugInfoData!=null){
//                            iBaseListener.onSucceed(drugInfoData);
//                        }else {
//                            iBaseListener.onFailed("失败");
//                        }
//                    });
//        }
//    }
