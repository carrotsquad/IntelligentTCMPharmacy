package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.R;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.contract.GetUserPictureContract;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.presenter.GetUserPicPresenter;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.util.system.ToActivityUtil;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.activity.AboutUsActivity;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.activity.UserInfoEditActivity;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.widget.CircleImageView;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.widget.MaskableImageView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Description: 个人fragment
 * Created at: 2018/10/21 11:53
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
// TODO: 2018/10/21 很多很多要做
// TODO: 2019/2/15   so much to do
public class PersonFragment extends Fragment implements View.OnClickListener,GetUserPictureContract.GetUserPicView {


    /* 问诊记录 */
    private MaskableImageView mPresribeBt;
    /* 服药提醒 */
    private MaskableImageView mCautionBt;
    /* 关于我们 */
    private MaskableImageView mAboutUsBt;
    /* 就诊记录 */
    private MaskableImageView mTreatRecordBt;
    /* 用户头像 */
    private CircleImageView userImage;
    /* 用户名 */
    private TextView userName;

    public static final String T = "PersonFragment";
    private Context context;
    private View view;
    private GetUserPicPresenter  mPresenter;
    private SharedPreferences.Editor  mEditor;
    private SharedPreferences shar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_personage, container, false);
        initView();
        shar = getActivity().getSharedPreferences("user",Context.MODE_PRIVATE);
        mEditor = shar.edit();
        userName.setText(shar.getString("username",null));
        context = getContext();
        mPresenter = new GetUserPicPresenter();
        mPresenter.attachActivty(this);
        mPresenter.getUserPic(shar.getString("userphone",null));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();
    }

    private void initView(){
        mPresribeBt = view.findViewById(R.id.prescribe_bt);
        mCautionBt = view.findViewById(R.id.caution_bt);
        mAboutUsBt = view.findViewById(R.id.about_us_bt);
        mTreatRecordBt = view.findViewById(R.id.treat_record_bt);
        userImage = view.findViewById(R.id.user_image);
        userName = view.findViewById(R.id.user_name);
        mCautionBt.setOnClickListener(this);
        mCautionBt.setOnClickListener(this);
        mAboutUsBt.setOnClickListener(this);
        mTreatRecordBt.setOnClickListener(this);
        userImage.setOnClickListener(this);
        userName.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_image:{
                ToActivityUtil.toNextActivity(context,UserInfoEditActivity.class);
                break;
            }
            /* 取药记录 */
            case R.id.presribe_bt:

                break;
            /* 关于我们 */
            case R.id.about_us_bt:{
                ToActivityUtil.toNextActivity(context, AboutUsActivity.class);
                break;
            }
            /* 服药提醒 */
            case R.id.caution_bt:{
                //跳转到系统的闹钟
                Intent alarm = new Intent(AlarmClock.ACTION_SHOW_ALARMS);
                startActivity(alarm);
                break;
            }
            /* 个人资料 */
            case R.id.treat_record_bt:
                ToActivityUtil.toNextActivity(this.getContext(),UserInfoEditActivity.class);
                break;
            /* 产生二维码 */
            case R.id.generate_qb:
                //可能有bug

                break;
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        userName.setText(shar.getString("nickname",null));
        super.onResume();
    }

    @Override
    public void isRight(boolean result, String reason, String picUrl) throws IOException {
        if (result){
            mEditor.putString("userpic",picUrl);
            mEditor.commit();
            Log.d("fuck","方法执行了");
            getBitmapFromUrl(picUrl,userImage,getActivity());
        }else{
            Log.d(T,reason);
        }
    }

    public static void  getBitmapFromUrl(String url,CircleImageView imageView,Activity activity) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url1 = new URL(url);
                    InputStream in = url1.openStream();
                    Bitmap bm = BitmapFactory.decodeStream(in);
                    if (bm == null)
                        Log.d("fuck", "2方法执行了");
                    setImageFromBitMap(activity,imageView,bm);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void setImageFromBitMap(Activity activity,CircleImageView view,Bitmap bitmap){
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                view.setImageBitmap(bitmap);
            }
        });
    }
}
