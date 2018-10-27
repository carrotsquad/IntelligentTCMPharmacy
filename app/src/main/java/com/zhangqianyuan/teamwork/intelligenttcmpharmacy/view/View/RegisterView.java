package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.View;

/**
 * Description 注册界面接口
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public interface RegisterView {
    /**
     *Description presenter传回的验证码是否与用户输入相同
     * @param  identifyCode
     * @return
     */
    boolean isIdentifyCodeRight(String identifyCode);
}
