package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.net.netInterface;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.GetMsgBean;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.SendMsgBean;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Description 用于请求网络的类
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public interface RetrofitServers {
    //发送短信验证码
    @POST()
    Call<GetMsgBean> msgServer(@Body SendMsgBean bean );
}
