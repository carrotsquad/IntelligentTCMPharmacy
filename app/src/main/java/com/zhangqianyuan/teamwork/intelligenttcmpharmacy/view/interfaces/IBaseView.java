package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.interfaces;

        import android.content.Context;

/**
 * Description: 基础IBaseView,Object向下转型成各种类型可节省代码
 * Created at: 2018/10/27 19:06
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
public interface IBaseView {
    Context getActivity();
    void showInfo(Object object, Boolean issucced);
}
