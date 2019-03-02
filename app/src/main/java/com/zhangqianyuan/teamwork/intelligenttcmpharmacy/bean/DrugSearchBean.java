package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean;

import java.util.ArrayList;

public class DrugSearchBean {
    private Boolean result;
    private ArrayList<Medicine> medicineList;

    public ArrayList<Medicine> getMedicineList() {
        return medicineList;
    }

    public Boolean getResult() {
        return result;
    }

    public void setMedicineList(ArrayList<Medicine> medicineList) {
        this.medicineList = medicineList;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
