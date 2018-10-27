package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.View;

/**
 * Description 登录界面接口
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public interface LoginView {
    /**
     * 登录成功进入首页
     * 无账号 提示AlertDialog 是否注册账号
     */
     void login();

    /**
     * 展示AlertDialog
     */
    void showAlertDialog();
}
