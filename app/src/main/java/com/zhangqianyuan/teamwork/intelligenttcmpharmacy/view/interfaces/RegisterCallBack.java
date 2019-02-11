package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.View;

/**
 * Description 注册界面接口
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public interface RegisterCallBack {
    /**
     * 注册成功后 返回 手机号，密码
     * @param phoneNumber
     * @param passwords
     */
  void  onSuccess(String phoneNumber,String passwords);

}
