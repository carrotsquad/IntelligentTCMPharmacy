package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean;

public class Medicine {
    private String medicineName;
    private String medicinePic;
    private String intro;
    private String notice;

    public String getIntro() {
        return intro;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public String getMedicinePic() {
        return medicinePic;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public void setMedicinePic(String medicinePic) {
        this.medicinePic = medicinePic;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getNotice() {
        return notice;
    }
}
