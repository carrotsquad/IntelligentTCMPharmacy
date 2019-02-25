package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.R;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.adapter.MainViewAdapter;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.fragment.PersonFragment;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.fragment.SearchFragment;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.fragment.SelectFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.schedulers.Schedulers;


/**
 * Description: 这是主activity
 * Created at: 2018/10/21 10:54
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
// TODO: 2018/10/21 主页很多要做,比如更新功能,热修复功能
public class MainActivity extends AppCompatActivity {


    @BindView(R.id.main_title_tv)
    TextView mTitleTv;
    @BindView(R.id.main_view_pager)
    ViewPager mViewPager;

    /* 这个里面包含了图标和文字 */
    @BindView(R.id.navigation)
    BottomNavigationView mBottomNav;

    private String[] titles = new String[]{"主页", "药材百科", "个人中心"};

    /**
     * 几个常量
     */
    private final int SELECT_FRAGMENT = 0;
    private final int SEARCH_FRAGMENT = 1;
    private final int PERSONAGE_FRAGMENT = 2;

    /**
     * 上次点击返回键的时间
     */
    private long lastBackPressed;

    /**
     * 两次点击的间隔时间
     */
    private static final int QUIT_INTERVAL = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);
        ButterKnife.bind(this);
        initView();
        initPermission();
    }


    //申请权限
    private void initPermission() {
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.INTERNET)!= PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.INTERNET, Manifest.permission.CAMERA},1);
        }
    }

    //权限申请
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                } else {
                    FancyToast.makeText(MainActivity.this,"你拒绝了权限", FancyToast.ERROR,Toast.LENGTH_SHORT,false).show();
                }
                break;
            default:
                break;
        }
    }

    /**
     * Description: 初始化界面
     */
    private void initView(){

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(SelectFragment.newInstance());
        fragments.add(SearchFragment.newInstance());
        fragments.add(new PersonFragment());

        MainViewAdapter mainViewAdapter = new MainViewAdapter(getSupportFragmentManager());

        mainViewAdapter.setFragments(fragments);

        mViewPager.setAdapter(mainViewAdapter);

        //划页监听器
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            private MenuItem prevMenuItem;
            @Override
            public void onPageSelected(int position) {
                mTitleTv.setText(titles[position]);
                mBottomNav.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //加入底部导航监听
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.select_ui:{
                        mViewPager.setCurrentItem(SELECT_FRAGMENT);
                        return true;
                    }
                    case R.id.search_ui:{
                        mViewPager.setCurrentItem(SEARCH_FRAGMENT);
                        return true;
                    }
                    case R.id.personage_ui:{
                        mViewPager.setCurrentItem(PERSONAGE_FRAGMENT);
                        return true;
                    }
                    default:{
                        return true;
                    }
                }
            }
        });}



    /**
     * 重写返回键响应函数，按两次才退出
     */
    @Override
    public void onBackPressed() {
        long backPressed = System.currentTimeMillis();
        if (backPressed - lastBackPressed > QUIT_INTERVAL) {
            lastBackPressed = backPressed;
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_LONG).show();
        } else {
            finish();
            System.exit(0);
        }
    }

}

