package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean;

/**
 * Description  改密码个昵称
 * @author zhoudada
 * @version $Rev$
 * @des
 * @updateAuthor $Author$
 * @updateDes
 */
public class UpdateNickNameorPassWordBean {
    private Boolean result;
    private String  reason;

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
