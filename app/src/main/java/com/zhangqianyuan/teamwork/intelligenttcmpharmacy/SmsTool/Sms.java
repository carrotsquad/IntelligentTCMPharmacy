package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.SmsTool;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVMobilePhoneVerifyCallback;
import com.avos.avoscloud.AVSMS;
import com.avos.avoscloud.AVSMSOption;
import com.avos.avoscloud.RequestMobileCodeCallback;

/**
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class Sms {
    /**
     * 发送验证码
     * @param phoneNumber
     */
    public static  void sendSms(String phoneNumber){
        AVSMSOption option = new AVSMSOption();
        option.setTtl(10);                     // 验证码有效时间为 10 分钟
        option.setApplicationName("智能中药房");
        option.setOperation("注册验证");
        AVSMS.requestSMSCodeInBackground(phoneNumber, option, new RequestMobileCodeCallback() {
            @Override
            public void done(AVException e) {
                if (null == e) {
                    Log.d("Sms","请求正确");
                } else {
                    Log.d("Sms","请求失败");
                }
            }
        });
    }


    public  static  void verifySms(String phoneNumber,String identifyCode){
        AVSMS.verifySMSCodeInBackground(identifyCode, phoneNumber, new AVMobilePhoneVerifyCallback() {
            @Override
            public void done(AVException e) {
                if (null == e) {
                    Log.d("Sms","验证正确");
                } else {
                    Log.d("Sms","验证失败");
                }
            }
        });
    }
}
