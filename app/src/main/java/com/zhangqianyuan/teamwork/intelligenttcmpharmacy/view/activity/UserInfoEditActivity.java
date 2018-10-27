package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.R;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.widget.CircleImageView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description:
 * Created at: 2018/10/27 17:52
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
public class UserInfoEditActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_GALLERY = 1;
    @BindView(R.id.circleImageView)
    CircleImageView mUserImage;
    @BindView(R.id.nick_name)
    EditText mUserName;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private boolean hasChanged = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo_edit);
        ButterKnife.bind(this);
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void initViews() {
        setSupportActionBar(mToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbar.setTitle(R.string.setting);
        mToolbar.setTitleTextColor(Color.WHITE);
        //最开始设置姓名编辑光标不现实
        mUserName.setCursorVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
                default:{
                    break;
                }
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.head_relative, R.id.name_relative, R.id.log_off, R.id.password_relative,R.id.medical_date_setting_relative, R.id.finish_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.head_relative:

                break;
            case R.id.name_relative:
                mUserName.setCursorVisible(true);
                mUserName.requestFocus();
                break;
            case R.id.password_relative:
//                mUserName.setCursorVisible(false);
//                ToActivityUtil.toNextActivity(this, PasswordEditActivity.class);
                break;
            case R.id.finish_bt:
//                mUserName.setCursorVisible(false);
//                //进行头像和姓名修改等上传
//                String changName = mUserName.getText().toString();
//                String newName = EncodeUtil.doEncrypt(changName, ResponseCons.KEY_NAME);
//                if (changName == null || changName.equals("")){
//                    ToastUtils.showToast(this,"暂未进行修改呢");
//                    return;
//                }
//                mPresenter.changUserName(IdUtil.getIdString(),newName);
                break;
            case R.id.medical_date_setting_relative:
//                ToActivityUtil.toNextActivity(this,MedicalDateSettingActivity.class);
                break;
            case R.id.log_off:
//                mUserName.setCursorVisible(false);
//                DbUtil.getDaosession().getUserDao().deleteAll();
//                DbUtil.getDaosession().getMedicalDateInfoDao().deleteAll();
//                DbUtil.getDaosession().getDateDao().deleteAll();
//                DbUtil.getDaosession().getMedicalListDao().deleteAll();
//                ToActivityUtil.toNextActivity(this, LoginActivity.class);
//                ActivityStack.getScreenManager().clearAllActivity();
//                SharedPreferences preferences =getSharedPreferences("isConnect",MODE_PRIVATE);
//                SharedPreferences.Editor editor = preferences.edit();
//                editor.clear();
//                editor.commit();
                //同时进行一些数据清除，如数据库的清理
                break;
        }
    }
}
