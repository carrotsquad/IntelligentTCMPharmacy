package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.R;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.adapter.GetPrescriptionAutoAdapter;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.DrugAndWeight;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.DrugAndWeightAndCount;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.contract.GetPrescriptionAUTOContract;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.contract.GetPrescriptionSureContract;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.presenter.GetPrescriptionAUTOPrensenter;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.presenter.GetPrescriptionSurePresenter;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.util.system.ActivityManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GetPrescriptionAUTOActivity extends AppCompatActivity implements GetPrescriptionAUTOContract.GetPrescriptionAUTOiew, GetPrescriptionSureContract.GetPrescriptionSureView {

    @BindView(R.id.getprescritionauto_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.getprescritionauto_searchview)
    SearchView mSearchView;

    @BindView(R.id.getprescritionauto_easyrecycle)
    EasyRecyclerView rec;

    @BindView(R.id.getprescritionauto_price)
    TextView priceView;

    @BindView(R.id.getprescritionauto_float)
    FloatingActionButton ok;

    private PopupWindow p ;
    private String pri;
    private static final String T = "GetPrescriptionAUTO";

    private GetPrescriptionAUTOPrensenter presenter;
    private GetPrescriptionSurePresenter  presenterSure;
    private GetPrescriptionAutoAdapter adapter;
    //返回的药方编号 暂时
    private int pId;
    //recycleviewitem 数据集合
    private ArrayList<DrugAndWeightAndCount> d = new ArrayList<>();
    private SharedPreferences shar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_prescription_auto);
        ButterKnife.bind(this);
        ActivityManager.getActivityManager().add(this);
        setSupportActionBar(mToolbar);
        adapter = new GetPrescriptionAutoAdapter(this);
        rec.setLayoutManager(new LinearLayoutManager(this));
        rec.setAdapter(adapter);

        shar = getSharedPreferences("user",MODE_PRIVATE);
        presenter = new GetPrescriptionAUTOPrensenter();
        presenterSure = new GetPrescriptionSurePresenter();
        presenterSure.attachActivty(this);
        presenter.attachActivty(this);
        //设置点开搜索后出现提交按钮
        mSearchView.setSubmitButtonEnabled(true);
        //添加监听
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!query.equals("")) {
                    presenter.getPrescriptionAUTO(query);
                }
                if (mSearchView != null) {
                    InputMethodManager methodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (methodManager != null) {
                        //让软键盘在所有情况下被隐藏
                        //输入法如果是显示状态，那么就隐藏输入法
                        methodManager.hideSoftInputFromInputMethod(mSearchView.getWindowToken(), 0);
                    }
                    // 不获取焦点
                    mSearchView.clearFocus();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pri!=null){
                    showPup(pri);
                }else{
                    Toast.makeText(GetPrescriptionAUTOActivity.this,"没有药方信息",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void isRightAndGetInfo(boolean result, List<DrugAndWeight> list, int prescriptionId, double price) {
        if (result) {
            if (list != null) {
                d.clear();
                list.forEach(e -> {
                    DrugAndWeightAndCount haha = new DrugAndWeightAndCount();
                    haha.setMedicineName(e.getMedicineName());
                    haha.setWeight(e.getWeight());
                    haha.setCount(list.indexOf(e) + 1);
                    d.add(haha);
                });
                Log.d(T, " " + d.get(0).getWeight());
                String p = "价格：" + String.valueOf(price) + " 元";
                pri = p;
                if (d != null) {
                    adapter.clear();
                    adapter.addAll(d);
                    adapter.notifyDataSetChanged();
                }
            }
            if (prescriptionId != 0) {
                pId = prescriptionId;
            } else {
                adapter.clear();
                adapter.notifyDataSetChanged();
            }
        }
    }


    //点击勾勾后，展示购买界面
    public void showPup(String pri){
        View  contentView = LayoutInflater.from(this).inflate(R.layout.prescription_detail,null);
        //得到window对象
        WindowManager windowManager = this.getWindowManager();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int width =  displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        p  = new PopupWindow(contentView,width,height,true);
        p.setContentView(contentView);
        //设置数据
        EasyRecyclerView recyclerView = contentView.findViewById(R.id.prescription_pup_easy);
        TextView back = contentView.findViewById(R.id.prescription_pup_back);
        TextView price  = contentView.findViewById(R.id.prescription_pup_price);
        Button   ok = contentView.findViewById(R.id.prescription_pup_ok);
        Button   cancle = contentView.findViewById(R.id.prescription_pup_cancle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               p.dismiss();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //发送给服务器
                presenterSure.getPrescriptionSure(shar.getString("userphone",null),1,pId);

            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               p.dismiss();
            }
        });
         price.setText(pri);
        View rootview = LayoutInflater.from(this).inflate(R.layout.activity_get_prescription_auto, null);
        //获得焦点
        p.setFocusable(true);
        //动画效果
        p.setAnimationStyle(R.style.PopUpWindowShow);
        //可接触
        p.setTouchable(true);
        //可从点击外面关掉window
        p.setOutsideTouchable(false);
        //相对于父布局的位置
        p.showAtLocation(rootview, Gravity.CENTER, 0, 0);

    }

    @Override
    public void isRight(boolean result, String reason) {
        if (result){
            Toast.makeText(GetPrescriptionAUTOActivity.this,"购买成功",Toast.LENGTH_SHORT).show();
            p.dismiss();
        }else
            Toast.makeText(GetPrescriptionAUTOActivity.this,"购买失败，"+reason,Toast.LENGTH_SHORT).show();
    }
}

