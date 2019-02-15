package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean;

/**
 * Description 获取用户头像
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class GetUserPictureBean {
    /**
     * @param  result
     * @param  reason
     * @param  userPicUrl 用户头像的url地址，如果失败就为空
     */

    private boolean result;
    private String   reason;
    private String   userPicUrl;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getUserPicUrl() {
        return userPicUrl;
    }

    public void setUserPicUrl(String userPicUrl) {
        this.userPicUrl = userPicUrl;
    }
}
