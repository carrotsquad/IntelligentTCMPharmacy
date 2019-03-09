package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean;

/**
 * Description 手动抓药bean
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class SelfTakeMedBean {
    private boolean result;
    private String reason;
    // 药方的编号  一个药方唯一有一个编号
    private int     prescriptionId;
    private double price;
    //成功 用药提醒，失败 原因
    private String Infor;
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

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getInfor() {
        return Infor;
    }

    public void setInfor(String infor) {
        Infor = infor;
    }
}
