package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.R;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.View.BaseView;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.interfaces.IBaseView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description: 这个是就诊记录和问诊记录复用
 * Created at: 2018/10/28 20:27
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
public class InquiryActivity extends AppCompatActivity implements IBaseView {

    /**
     * view
     */
    @BindView(R.id.inquiry_easy_recycler_view)
    EasyRecyclerView mEasyRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquiry);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initView(){

    }

    @Override
    public Context getActivity() {
        return InquiryActivity.this;
    }

    @Override
    public void showInfo(Object object, Boolean issucced) {

    }
}
