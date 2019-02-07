package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 修改昵称界面
 */
public class UpdateNickName extends AppCompatActivity {

    @BindView(R.id.nick_name_bar)
    Toolbar    mToolbar;

    @BindView(R.id.old_nickname)
    TextView  oldNickName;

    @BindView(R.id.finish_bt_nick)
    Button  finishBt;

    @BindView(R.id.cancel_nick)
    Button    cancelBt;

    @BindView(R.id.new_nickname_text)
    EditText  newNickName;

    private SharedPreferences shar ;
    private SharedPreferences.Editor  edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_nick_name);
        ButterKnife.bind(this);
        initView();
    }

    //初始化界面
    public void initView(){
        setSupportActionBar(mToolbar);
        //设置光标不显示
        newNickName.setCursorVisible(false);
        //得到旧昵称
        shar = getSharedPreferences("users",MODE_PRIVATE);
        edit = shar.edit();
        String oldName = shar.getString("nickname",null);
        oldNickName.setText(oldName);
    }

    @OnClick({R.id.finish_bt_nick,R.id.cancel_nick})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.finish_bt_nick:
                if (TextUtils.isEmpty(newNickName.getText())){
                    Toast.makeText(this,"昵称不能为空",Toast.LENGTH_SHORT).show();
                }else{
                    edit.putString("nickname",newNickName.getText().toString());
                    edit.commit();
                    finish();
                }
                break;
            case R.id.cancel_nick:
                finish();
        }
    }

}
