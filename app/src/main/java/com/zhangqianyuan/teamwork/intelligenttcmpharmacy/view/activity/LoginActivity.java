package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.R;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.contract.LogInContract;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.presenter.LogInPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description: 登录页面
 * Created at: 2019/2/11 14:13
 * @author: zhangqianyuan
 * @Email: zhang.qianyuan@foxmail.com
 * @version:
 * @updateAuthor:
 * @updateDes:
 */
public class LoginActivity extends AppCompatActivity implements LogInContract.LogInView {
    @BindView(R.id.account_input)
    EditText userName;  //账号输入框

    @BindView(R.id.password_input)
    EditText passwords; //密码输入框

    @BindView(R.id.login_bt)
    Button login;     //登录按钮

    @BindView(R.id.register_bt)
    Button register;    //注册按钮

    public static final String USER_PHONE = "userphone";
    public static final String USER_PASSWORD = "userpassword";
    public static final String USER_NAME = "username";

    private LogInPresenter logInPresenter;
    private String pwd = "";
    private String tele = "";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        logInPresenter = new LogInPresenter();
        logInPresenter.attachActivty(this);
        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        if ("".equals(sharedPreferences.getString(USER_PHONE, "")) || "".equals(sharedPreferences.getString(USER_PASSWORD, ""))) {

        } else {
            passwords.setText(sharedPreferences.getString(USER_PASSWORD,""));
            userName.setText(sharedPreferences.getString(USER_PHONE,""));

            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        logInPresenter.dettachActivity();
        super.onDestroy();
    }

    @OnClick({R.id.login_bt, R.id.register_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_bt: {
                //在服务器进行信息比对决定是否登陆
                if (isFormCompleted()) {
                    logInPresenter.getLogIn(tele, pwd);
                } else {
                    Toast.makeText(this, "请填写完整", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.register_bt: {
                //进入注册界面
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
                break;
            }
            default: {
                break;
            }
        }
    }

    //检查表单是否填写完整
    private boolean isFormCompleted() {
        pwd = passwords.getText().toString();
        tele = userName.getText().toString();
        return ((!"".equals(pwd)) || (!"".equals(tele)));
    }

    @Override
    public void onResult(Boolean isright, String name, String info) {
        if (isright) {
            pwd = passwords.getText().toString();
            tele = userName.getText().toString();
            editor = sharedPreferences.edit();
            editor.putString(USER_NAME, name);
            editor.putString(USER_PASSWORD, pwd);
            editor.putString(USER_PHONE, tele);
            editor.commit();
            Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        } else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);
            dialog.setTitle("");
            dialog.setMessage("账号不存在，请先注册账号");
            dialog.setCancelable(true);
            dialog.setPositiveButton("好的", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            dialog.show();
        }
    }
}
