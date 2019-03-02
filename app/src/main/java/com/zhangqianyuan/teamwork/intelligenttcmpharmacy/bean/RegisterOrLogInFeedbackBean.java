package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean;

public class RegisterOrLogInFeedbackBean {
    private Boolean result;
    private String reason;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Boolean getResult() {
        return result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

}
