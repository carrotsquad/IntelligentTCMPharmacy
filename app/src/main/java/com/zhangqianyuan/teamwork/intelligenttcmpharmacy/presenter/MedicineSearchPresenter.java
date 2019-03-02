package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.presenter;

import android.content.Context;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.DrugSearchBean;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.contract.SearchContract;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.model.BaseModel;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.presenter.base.BasePresenter;


import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
/**
 * Description:
 * Created at: 2018/10/27 17:52
 *
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
public class MedicineSearchPresenter extends BasePresenter<SearchContract.SearchView> implements SearchContract.SearchPresenter {
    private SearchContract.SearchModel searchModel;

    public MedicineSearchPresenter() {
        searchModel = observer -> {
            new BaseModel()
                    .getApi()
                    .getSearchResult()
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribe(observer);
        };
    }

    @Override
    public void getSearchResult() {
        searchModel.getSearchResult(new Observer<DrugSearchBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(DrugSearchBean searchBean) {
                if (isAttachActivity()) {
                    if (searchBean != null) {
                        v.acquireSearchResult(searchBean);
                    }else {
                        DrugSearchBean bean=new DrugSearchBean();
                        bean.setResult(false);
                        v.acquireSearchResult(bean);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
