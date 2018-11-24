package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.R;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.interfaces.LoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description
 * 登录页面
 */
// TODO: 2018/11/24 需要完善
public class LoginActivity extends AppCompatActivity implements LoginView {
    @BindView(R.id.account_input)
    EditText userName;  //账号输入框

    @BindView(R.id.password_input)
    EditText passwords; //密码输入框

    @BindView(R.id.login_bt)
    Button   login;     //登录按钮

    @BindView(R.id.register_bt)
    Button   register;    //注册按钮
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

   @OnClick({R.id.login_bt,R.id.register_bt})
   public void onClick(View view){
        switch (view.getId()){
            case R.id.login_bt:
                //在服务器进行信息比对决定是否登陆
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
                break;
            case  R.id.register_bt:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
                //进入注册界面
                break;
        }
   }

    @Override
    public void login(){
        Toast.makeText(LoginActivity.this,"登陆成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAlertDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);
        dialog.setTitle("");
        dialog.setMessage("账号不存在，请先注册账号");
        dialog.setCancelable(true);
        dialog.setPositiveButton("好的", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {}
        });
        dialog.show();
    }
}
