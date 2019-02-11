package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 修改密码界面
 */
public class UpdatePassWords extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.finish_password_bt)
    Button  finishPassword;

    @BindView(R.id.old_password_input)
    EditText  oldPsInput;

    @BindView(R.id.new_password_input)
    EditText  newPsInput;

    @BindView(R.id.confirm_password_input)
    EditText  confirmPsInput;

    private  SharedPreferences shar;
    private  SharedPreferences.Editor mEditor;
    private  String oldPsWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pass_words);
        ButterKnife.bind(this);
        initView();
    }

    //初始化界面
    public void initView(){
        setSupportActionBar(mToolbar);
        shar = getSharedPreferences("users",MODE_PRIVATE);
        mEditor = shar.edit();
        oldPsWord = shar.getString("password",null);
    }

    //检查密码格式/正确度
    public boolean isEverythingRight(){
        String old = oldPsInput.getText().toString();
        String newPs = newPsInput.getText().toString();
        String comfirmPs = confirmPsInput.getText().toString();
        //旧密码为空
        if (TextUtils.isEmpty(old)){
            Toast.makeText(this,"旧密码输入为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        //旧密码输入错误
        else if (!old.equals(oldPsWord)){
            Toast.makeText(this,"输入旧密码错误",Toast.LENGTH_SHORT).show();
            return false;
        }
        //新密码为空
        else if (TextUtils.isEmpty(newPs)){
            Toast.makeText(this,"新密码输入为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        //两次输入密码不一致
        else if (!comfirmPs.equals(newPs)){
            Toast.makeText(this,"两次输入密码不一致",Toast.LENGTH_SHORT).show();
            return false;
        }
        //确认输入密码为空
        else if (TextUtils.isEmpty(comfirmPs)) {
            Toast.makeText(this, "确认密码输入为空", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }

    @OnClick(R.id.finish_password_bt)
    public void onClick(View view){
        if (isEverythingRight()){
            //上传新密码到后端，同时将新密码存到shar
            mEditor.putString("password",newPsInput.getText().toString());
            mEditor.commit();
        }
    }
}
