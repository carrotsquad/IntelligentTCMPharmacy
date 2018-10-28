package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Description: AboutUsActivity
 * Created at: 2018/10/28 16:44
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
public class AboutUsActivity extends AppCompatActivity {

    @BindView(R.id.about_us_web_view)
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);
        initViews();
    }

    public void initViews() {

        WebSettings webSettings =  mWebView.getSettings();
        //支持JS
        webSettings.setJavaScriptEnabled(true);
        //支持屏幕缩放
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        //不显示webview缩放按钮
        webSettings.setDisplayZoomControls(false);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl("http://nmid.cqupt.edu.cn/");
    }

}
