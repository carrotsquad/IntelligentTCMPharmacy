package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean;

/**
 * Description:  药材百科中，Medicine的具体成员变量
 * Created at: 2018/10/27 18:02
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
public class DrugInfo {
    /**
     * medicineName  药材名称
     * medicinePic 药材图片的url地址
     * intro   药材的简介
     *  notice   注意事项或者，禁忌
     */
    private String medicineName;
    private String medicinePic;
    private String  intro;
    private String   notice;

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicinePic() {
        return medicinePic;
    }

    public void setMedicinePic(String medicinePic) {
        this.medicinePic = medicinePic;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
}
