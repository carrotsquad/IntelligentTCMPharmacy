package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.activity;

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
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.View.RegisterView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description
 * 注册界面
 */
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
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
            case R.id.identifying_number_send:
                //这里通过presenter进行 请求验证码操作
                break;
            case R.id.confirm_bt:
                if (isEveryThingRight()){
                    /*这里通过presenter进行 验证码 验证操作
                     全都ok后 回到登陆界面登陆
                     */
                }
                break;

        }
   }

    @Override
    public boolean isIdentifyCodeRight(String identifyCode){
        if (identifyCode.equals(identifyNumber.getText().toString())){
            return  true;
        }else
            return  false;
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
        }else if (identify.equals("")){
            Toast.makeText(RegisterActivity.this,"请选择身份",Toast.LENGTH_SHORT).show();
            return false;
        }else
            return true;
    }
}
