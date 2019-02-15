package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean;

/**
 * Description
 * 单个药方 /
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class DrugAndWeight {
    //药名
    private String medicineName;
    //质量
    private int  weight;

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
