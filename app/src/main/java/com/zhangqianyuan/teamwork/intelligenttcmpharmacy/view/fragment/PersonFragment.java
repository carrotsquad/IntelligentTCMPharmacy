package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.util.system.ToActivityUtil;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.activity.AboutUsActivity;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.activity.UserInfoEditActivity;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.widget.CircleImageView;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.widget.MaskableImageView;



/**
 * Description: 个人fragment
 * Created at: 2018/10/21 11:53
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
// TODO: 2018/10/21 很多很多要做
public class PersonFragment extends Fragment implements View.OnClickListener {


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

    private Context context;
    private View view;
    private SharedPreferences shar;
    public static Fragment newInstance(){
        PersonFragment personFragment = new PersonFragment();
        return personFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_personage, container, false);
        initView();
//        Log.d("fuck",getActivity().getSharedPreferences("users",Context.MODE_PRIVATE).getString("nickname",null));
        shar = getActivity().getSharedPreferences("users",Context.MODE_PRIVATE);
        userName.setText(shar.getString("nickname",null));
        context = getContext();
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
                //进行弹窗显示
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(R.string.remind)
                        .setMessage(R.string.need_to_go_to_user_setting)
                        .setCancelable(true)
                        .setPositiveButton("确定",
                                (dialog, what) -> {
                                    ToActivityUtil.toNextActivity(context, UserInfoEditActivity.class);
                                    dialog.dismiss();
                                })
                        .setNegativeButton("取消",
                                (dialog, what) -> dialog.dismiss()
                        )
                        .show();
                break;
            }
            /* 问诊记录 */
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
            /* 就诊记录 */
            case R.id.treat_record_bt:

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
}
