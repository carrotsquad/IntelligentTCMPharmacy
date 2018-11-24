package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.R;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.presenter.RegisterPresenter;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.interfaces.RegisterView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description: 注册界面
 * Created at: 2018/12/22 21:20
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
// TODO: 2018/11/24 需完善
public class RegisterActivity extends AppCompatActivity implements RegisterView {

    @BindView(R.id.acount_input)
    EditText phoneNumber;       //账号

    @BindView(R.id.password_input)
    EditText passwords;      //密码

    @BindView(R.id.identifying_number_input)
    EditText  identifyNumber;     //验证码输入框

    @BindView(R.id.identifying_number_send)
    TextView  sendIdentifyNumber;  //发送验证码

    @BindView(R.id.name_input)
    EditText   nameInput;          //用户昵称

    @BindView(R.id.select_identify)
    RadioGroup  selectIdentify;     //选择身份

    @BindView(R.id.confirm_bt)
    Button confirm;                //确认注册按钮

    String identify;               //用户身份

    private Boolean isVerifyRight = false;
    private RegisterPresenter presenter;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor mEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences("users",MODE_PRIVATE);
        mEditor = sharedPreferences.edit();
        presenter = new RegisterPresenter();
        presenter.attachActivty(this);
        selectIdentify.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int id = radioGroup.getCheckedRadioButtonId();
                RadioButton button = RegisterActivity.this.findViewById(id);
                identify = button.getText().toString();
            }
        });
    }

   @OnClick({R.id.identifying_number_send,R.id.confirm_bt})
   public void onClick(View v){
        switch (v.getId()){
            case R.id.identifying_number_send: {
                //这里通过presenter进行 请求验证码操作
                if(!phoneNumber.getText().toString().equals("")){
                    presenter.verifyPhonenumber(phoneNumber.getText().toString());
                }
            }
            break;
            case R.id.confirm_bt: {
//                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
//                finish();
                if (isEveryThingRight()){
                    /*这里通过presenter进行 验证码 验证操作
                     全都ok后 回到登陆界面登陆
                     */
                    presenter.register(phoneNumber.getText().toString(),
                            passwords.getText().toString(),
                            identifyNumber.getText().toString(),
                            nameInput.getText().toString());
                }
                break;
            }
            default:
                break;

        }
   }



    public boolean isEveryThingRight(){
        if (phoneNumber.getText().toString().equals("")){
            Toast.makeText(RegisterActivity.this,"请输入电话号码",Toast.LENGTH_SHORT).show();
            return false;
        }else if (passwords.getText().toString().equals("")){
            Toast.makeText(RegisterActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }else if (nameInput.getText().toString().equals("")){
            Toast.makeText(RegisterActivity.this,"请输入昵称",Toast.LENGTH_SHORT).show();
            return false;
        }else if (identifyNumber.getText().toString().equals("")){
            Toast.makeText(RegisterActivity.this,"请输入验证码",Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void verify(Boolean isright, String info) {
        if(isright) {
            Toast.makeText(this, "验证码发送成功", Toast.LENGTH_SHORT);
        }else {
            Toast.makeText(this, info, Toast.LENGTH_SHORT);
        }
    }

    @Override
    public void register(Boolean isright, String info) {
        if(isright){
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT);
            mEditor.putString("phone", phoneNumber.getText().toString());
            mEditor.putString("password", passwords.getText().toString());
            mEditor.putString("nickname", nameInput.getText().toString());
            mEditor.commit();
        }else {
            Toast.makeText(this, info, Toast.LENGTH_SHORT);
        }
    }
}
