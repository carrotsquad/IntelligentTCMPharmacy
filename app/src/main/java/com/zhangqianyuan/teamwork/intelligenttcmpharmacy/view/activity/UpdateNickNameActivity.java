package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.R;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.UpdateNickNameorPassWordBean;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.contract.UpdateNickNameContract;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.network.Api;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.presenter.UpdateNickNamePresenter;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.util.system.ActivityManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Callback;

/**
 * 修改昵称界面
 */
public class UpdateNickNameActivity extends AppCompatActivity implements UpdateNickNameContract.updateNickNameView {
    public static final String T = "UpdateNickNameActivity";
    @BindView(R.id.nick_name_bar)
    Toolbar    mToolbar;

    @BindView(R.id.old_nickname)
    TextView  oldNickName;

    @BindView(R.id.finish_bt_nick)
    Button  finishBt;

    @BindView(R.id.cancel_nick)
    Button    cancelBt;

    @BindView(R.id.new_nickname_input)
    EditText  newNickName;

    private SharedPreferences shar ;
    private SharedPreferences.Editor  edit;
    private UpdateNickNamePresenter mUpdateNickNamePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_nick_name);
        ButterKnife.bind(this);
        ActivityManager.getActivityManager().add(this);
        initView();
        mUpdateNickNamePresenter = new UpdateNickNamePresenter();
        mUpdateNickNamePresenter.attachActivty(this);
    }

    //初始化界面
    public void initView(){
        setSupportActionBar(mToolbar);
        //设置光标不显示
        newNickName.setCursorVisible(false);
        //得到旧昵称
        shar = getSharedPreferences("user",MODE_PRIVATE);
        edit = shar.edit();
        String oldName = shar.getString("username",null);
        Log.d(T,""+oldName);
        oldNickName.setText(oldName);
    }

    @OnClick({R.id.finish_bt_nick,R.id.cancel_nick})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.finish_bt_nick:
                if (TextUtils.isEmpty(newNickName.getText())){
                    Toast.makeText(this,"昵称不能为空",Toast.LENGTH_SHORT).show();
                }else{
                    String tell = shar.getString("userphone",null);
                    Log.d(T,"userphone="+tell);
                    mUpdateNickNamePresenter.updateNickName(tell,newNickName.getText().toString());
                    edit.putString("username",newNickName.getText().toString());
                    edit.commit();
                }
                break;
            case R.id.cancel_nick:
                finish();
        }
    }

    @Override
    public void isRight(boolean result, String reason) {
        if (result){
            Toast.makeText(this,"操作成功",Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(this,"操作失败\n"+reason,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUpdateNickNamePresenter.dettachActivity();
    }


}
