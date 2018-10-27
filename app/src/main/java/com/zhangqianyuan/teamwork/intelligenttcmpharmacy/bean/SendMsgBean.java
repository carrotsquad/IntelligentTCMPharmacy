package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean;

/**
 * Description 短信验证码请求体类
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class SendMsgBean {
    private  String account ;//用户在云通讯平台上申请的API账号(接口账号 非登录账号)
    private  String password;//用户在云通讯平台上申请的API账号对应的API密钥
    private  String msg     ; //短信内容
    private  String phone   ; //手机号码
    private  String sendtime ;//定时发送短信时间。格式为yyyyMMddHHmm，值小于或等于当前时间则立即发送，不填则默认为立即发送（选填参数）
    private  String report   ; //"true"是否需要状态报告（默认为false）（选填参数
    private  String extend ;  //用户自定义扩展码，纯数字，建议1-3位（选填参数）
    private  String uid    ;   //自助通系统内使用UID判断短信使用的场景类型，可重复使用，可自定义场景名称，示例如 VerificationCode（选填参数）

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSendtime() {
        return sendtime;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
