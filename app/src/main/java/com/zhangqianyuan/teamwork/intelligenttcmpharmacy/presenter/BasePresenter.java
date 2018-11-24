package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.presenter;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.interfaces.BaseView;

/**
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class BasePresenter<V extends BaseView> {
    V v;
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
