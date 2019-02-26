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
    //用户自己输入的症状/我们提供的症状
    private String symptom ;
    //药+质量 序列
    private List<DrugAndMass> drugList;

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public List<DrugAndMass> getDrugList() {
        return drugList;
    }

    public void setDrugList(List<DrugAndMass> drugList) {
        this.drugList = drugList;
    }
}
