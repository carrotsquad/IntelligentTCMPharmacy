package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean;

/**
 * Description 为了自动取药的easyrecycle创建的bean
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class DrugAndWeightAndCount {
    //药名
    private String medicineName;
    //质量
    private int  weight;
    // 编号
    private int  count;

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
