package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean;

/**
 * Description 短信验证码返回体类
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class GetMsgBean {
    private String code;//状态码
    private String msgId;//消息Id
    private String errorMsg ; //失败状态码说明（成功返回空）
    private String time;     //响应时间

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
