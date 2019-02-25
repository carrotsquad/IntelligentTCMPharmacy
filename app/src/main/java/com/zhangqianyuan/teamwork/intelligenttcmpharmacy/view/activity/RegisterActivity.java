package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.R;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.contract.RegisterContract;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.presenter.RegisterPresenter;

import java.util.Observer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Description: 注册界面
 * Created at: 2018/12/22 21:20
 *
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
public class RegisterActivity extends AppCompatActivity implements RegisterContract.RegisterView {

    @BindView(R.id.register_login_bt)
    TextView toLogin;

    @BindView(R.id.register_acount_input)
    EditText phoneNumber;       //账号

    @BindView(R.id.register_password_input)
    EditText passwords;      //密码

    @BindView(R.id.identifying_number_input)
    EditText identifyNumber;     //验证码输入框

    @BindView(R.id.identifying_number_send)
    TextView sendIdentifyNumber;  //发送验证码

    @BindView(R.id.name_input)
    EditText nameInput;          //用户昵称

    @BindView(R.id.confirm_bt)
    Button confirm;                //确认注册按钮

    private Boolean isVerifyRight = false;
    private RegisterPresenter presenter;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences("users", MODE_PRIVATE);
        mEditor = sharedPreferences.edit();
        presenter = new RegisterPresenter();
        presenter.attachActivty(this);
    }

    @OnClick({R.id.identifying_number_send, R.id.confirm_bt, R.id.register_login_bt})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.identifying_number_send: {
                //这里通过presenter进行 请求验证码操作
                if (!"".equals(phoneNumber.getText().toString())) {
                    presenter.verifyPhonenumber(phoneNumber.getText().toString());

                }
            }
            break;
            case R.id.confirm_bt: {
                if (isEveryThingRight()) {
                    //这里通过presenter进行 验证码 验证操作全都ok后 回到登陆界面登陆
                    presenter.register(phoneNumber.getText().toString(),
                            passwords.getText().toString(),
                            identifyNumber.getText().toString(),
                            nameInput.getText().toString());
                }
                break;
            }
            case R.id.register_login_bt:{
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
            default:
                break;

        }
    }


    public boolean isEveryThingRight() {
        if ("".equals(phoneNumber.getText().toString())) {
            Toast.makeText(RegisterActivity.this, "请输入电话号码", Toast.LENGTH_SHORT).show();
            return false;
        } else if ("".equals(passwords.getText().toString())) {
            Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return false;
        } else if ("".equals(nameInput.getText().toString())) {
            Toast.makeText(RegisterActivity.this, "请输入昵称", Toast.LENGTH_SHORT).show();
            return false;
        } else if ("".equals(identifyNumber.getText().toString())) {
            Toast.makeText(RegisterActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void verify(Boolean isright, String info) {
        if (isright) {
            //发送成功后开启定时60s任务:60s后才能重新发送
            Toast.makeText(this, "验证码发送成功", Toast.LENGTH_SHORT);
            //0-59间隔一秒计数
            Observable.interval(1, 1, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe((Long aLong) -> {
                        Log.e("计数", aLong.toString());
                        if (aLong <= 59) {
                            sendIdentifyNumber.setText(String.valueOf(59 - aLong) + "秒后可重发验证码");
                        } else {
                            sendIdentifyNumber.setText("获取验证码");
                        }
                    });

        } else {
            Toast.makeText(this, info, Toast.LENGTH_SHORT);
        }
    }

    @Override
    public void register(Boolean isright, String info) {
        if (isright) {
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
//            mEditor.putString("phone", phoneNumber.getText().toString());
//            mEditor.putString("password", passwords.getText().toString());
//            mEditor.putString("nickname", nameInput.getText().toString());
//            mEditor.commit();
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        } else {
            Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
        }
    }
}
