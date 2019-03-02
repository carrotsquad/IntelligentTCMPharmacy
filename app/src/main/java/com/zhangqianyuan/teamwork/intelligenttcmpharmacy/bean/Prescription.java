package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean;

import java.util.List;

/**
 * Description
 * 药方
 * @author zhoudada
 * @version $Rev$
 * @des
 * @updateAuthor $Author$
 * @updateDes
 */
public class Prescription {
    /**
     * prescription  药+质量 集合
     * price     价格
     * time      抓这个药方的时间时间格式为“yyyy-MM-dd HH:mm:ss”
     */

    //药+质量 序列
    private List<DrugAndWeight> prescription ;
    private String price;
    private String time;

    public List<DrugAndWeight> getPrescription() {
        return prescription;
    }

    public void setPrescription(List<DrugAndWeight> prescription) {
        this.prescription = prescription;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

