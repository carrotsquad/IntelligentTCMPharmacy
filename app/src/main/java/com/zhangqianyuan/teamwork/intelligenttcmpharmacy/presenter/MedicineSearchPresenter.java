package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.presenter;

import android.content.Context;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.model.MedecineModel;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.model.listener.IBaseListener;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.interfaces.ISearchFragment;


// TODO: 2018/10/27 需要完善
/**
 * Description:
 * Created at: 2018/10/27 17:52
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
public class MedicineSearchPresenter implements IBasePresenter {

    private ISearchFragment iSearchFragment;
    private MedecineModel medecineModel;

    public MedicineSearchPresenter(ISearchFragment iSearchFragment){
        this.iSearchFragment = iSearchFragment;
    }

    @Override
    public void checkInfo(Object object) {
        medecineModel.getInfo(object, iSearchFragment.getActivity(), new IBaseListener() {
            @Override
            public void onSucceed(Object object) {
                
            }

            @Override
            public void onFailed(Object object) {

            }
        });
    }
}
