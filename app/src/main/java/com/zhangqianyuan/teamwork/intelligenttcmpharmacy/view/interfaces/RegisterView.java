package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.interfaces;

public interface RegisterView extends BaseView{

    /**
     * 验证手机号
     * @param isright
     * @param info
     */
    void verify(Boolean isright,String info);

    /**
     * 注册
     * @param isright
     * @param info
     */
    void register(Boolean isright,String info);
}
