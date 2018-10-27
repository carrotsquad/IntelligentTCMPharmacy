package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean;


public class Data<T> {
    private int code;
    private String message;
    private T body;
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return body;
    }

    public void setData(T data) {
        this.body = data;
    }


}
