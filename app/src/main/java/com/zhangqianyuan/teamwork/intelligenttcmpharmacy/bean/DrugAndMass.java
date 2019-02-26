package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean;

/**
 * Description
 * 单个药方
 * @author zhoudada
 * @version $Rev$
 * @des
 * @updateAuthor $Author$
 * @updateDes
 */
public class DrugAndMass {
    //药名
    private String name;
    //质量
    private float  mass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }
}
