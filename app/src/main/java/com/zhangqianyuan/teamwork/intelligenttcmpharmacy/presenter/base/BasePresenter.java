package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.presenter.base;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.interfaces.BaseView;

/**
 * @author zhoudada
 * @version $Rev$
 * @des
 * @updateAuthor $Author$
 * @updateDes
 */
public class BasePresenter<V extends BaseView> {
    protected V v;
    public void attachActivty(V view){
        this.v=view;
    }
    public void dettachActivity(){
        this.v=null;
    }
    public boolean isAttachActivity(){
        return this.v!=null;
    }
}
