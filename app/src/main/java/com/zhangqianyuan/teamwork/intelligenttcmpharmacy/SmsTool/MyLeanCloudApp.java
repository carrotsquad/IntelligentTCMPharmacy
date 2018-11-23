package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.SmsTool;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

/**
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class MyLeanCloudApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this,"MXMByBuEnIsnSoiVIOyyJbzw-gzGzoHsz","LJFg4NxVpdxJzfyewxYPaKsm");
        AVOSCloud.setDebugLogEnabled(true);
    }

}
