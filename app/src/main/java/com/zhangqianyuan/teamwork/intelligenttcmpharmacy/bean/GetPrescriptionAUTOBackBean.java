package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean;

import java.util.List;

/**
 * Description 自动取药bean
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class GetPrescriptionAUTOBackBean {
    private boolean result;
    private List<DrugAndWeight> prescription;
    private int prescriptionId;
    private double price;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public List<DrugAndWeight> getPrescription() {
        return prescription;
    }

    public void setPrescription(List<DrugAndWeight> prescription) {
        this.prescription = prescription;
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
}
