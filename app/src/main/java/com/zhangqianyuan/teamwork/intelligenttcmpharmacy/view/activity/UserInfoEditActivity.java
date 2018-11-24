package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.github.florent37.expansionpanel.ExpansionLayout;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.squareup.picasso.Picasso;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.R;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.util.image.GlideImageLoader;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.widget.CircleImageView;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.BuildConfig;
import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.galleryfinal.widget.GFImageView;

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

    @BindView(R.id.nick_ID)
    EditText nick_id;

    @BindView(R.id.nick_phonenumber)
    EditText  phonenumber;

    @BindView(R.id.nick_email)
    EditText email;

    @BindView(R.id.finish_bt)
    Button  finish;


    @BindView(R.id.expansionLayout)
    ExpansionLayout expansionLayout;

    @BindView(R.id.log_off)
    Button logOff;


    private boolean hasChanged = false;
    private SharedPreferences shar;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo_edit);
        ButterKnife.bind(this);
        shar = getSharedPreferences("users",MODE_PRIVATE);
        editor  = shar.edit();
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("nickid",nick_id.getText().toString());
                editor.putString("nickname",mUserName.getText().toString());
                editor.putString("phonenumber",phonenumber.getText().toString());
                editor.commit();
                finish();
            }
        });
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
        mUserName.setText(shar.getString("nickname",null));
        nick_id.setText(shar.getString("phone",null));
        phonenumber.setText(shar.getString("phone",null));
        email.setText("点击设置");
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
            case R.id.head_relative: {
                initGallery();
                break;
            }
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
                startActivity(new Intent(UserInfoEditActivity.this,LoginActivity.class));
                finish();
                //同时进行一些数据清除，如数据库的清理
                //同时进行一些数据清除，如数据库的清理
                break;
                default:{
                    break;
                }
        }
    }

    private void initGallery(){
        //设置主题
        //ThemeConfig.CYAN
        ThemeConfig theme = new ThemeConfig.Builder().build();
        //配置功能
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setEnablePreview(true)
                .build();

        //配置imageloader
        GlideImageLoader imageloader = new GlideImageLoader();
        CoreConfig coreConfig = new CoreConfig.Builder(UserInfoEditActivity.this, imageloader, theme)
                .setDebug(BuildConfig.DEBUG)
                .setFunctionConfig(functionConfig).build();
        GalleryFinal.init(coreConfig);

        GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, mOnHandlerResultCallback);
    }

    private GalleryFinal.OnHanlderResultCallback mOnHandlerResultCallback = new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
            //进行图片上传与置换
            //置换
            String photoPath = resultList.get(0).getPhotoPath();
            mUserImage.setImageBitmap(BitmapFactory.decodeFile(photoPath));
            FancyToast.makeText(UserInfoEditActivity.this,"取得照片",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();
            //上传
            //上传时记得压缩
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            Log.e("editinfo",errorMsg);
            FancyToast.makeText(UserInfoEditActivity.this,errorMsg,FancyToast.LENGTH_SHORT,FancyToast.ERROR,true).show();
        }
    };
}
