package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean;

import java.util.List;

/**
 * Description 抓药记录
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class RecordOfTakingMadecine {
    /**
     * result
     * reason
     * prescriptionList   药方集合
     */

    private boolean result;
    private String reason;
    private List<Prescription> prescriptionList;

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

    public List<Prescription> getPrescriptionList() {
        return prescriptionList;
    }

    public void setPrescriptionList(List<Prescription> prescriptionList) {
        this.prescriptionList = prescriptionList;
    }
}
