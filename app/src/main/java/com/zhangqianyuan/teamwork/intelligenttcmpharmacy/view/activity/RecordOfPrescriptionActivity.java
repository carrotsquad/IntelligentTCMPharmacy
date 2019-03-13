package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.activity;

import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.R;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.adapter.GetPrescriptionAutoAdapter;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.adapter.RecordOfPrescriptionFirstAdapter;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.DrugAndWeight;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.DrugAndWeightAndCount;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.Prescription;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.contract.RecordOfPrescription;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.presenter.RecordOfPrescriptionPresenter;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.util.system.ActivityManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecordOfPrescriptionActivity extends AppCompatActivity implements RecordOfPrescription.RecordOfPrescriptionView {

    @BindView(R.id.record_toolbar)
    Toolbar mToolbar ;

    @BindView(R.id.record_easy)
    EasyRecyclerView rec;

    private PopupWindow pop;
   // 第一层recycle  时间列表
    private List<String>   timeList = new ArrayList<>();
    //第一层  prescription列表
    private List<Prescription> preList = new ArrayList<>();
    //药方列表  第二层列表
    private List<DrugAndWeightAndCount>  list = new ArrayList<>();
    private RecordOfPrescriptionPresenter presenter;
    private RecordOfPrescriptionFirstAdapter adapter;
    //第二层adapter
    private GetPrescriptionAutoAdapter    adapterAu;
    private SharedPreferences shar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_of_prescription);
        ButterKnife.bind(this);
        ActivityManager.getActivityManager().add(this);
        shar = getSharedPreferences("user",MODE_PRIVATE);
        presenter = new RecordOfPrescriptionPresenter();
        presenter.attachActivty(this);
        presenter.getRecordOfPrescription(shar.getString("userphone",null));
        adapterAu = new GetPrescriptionAutoAdapter(this);
        adapter = new RecordOfPrescriptionFirstAdapter(this);
        rec.setLayoutManager(new LinearLayoutManager(this));
        rec.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemClick(int position) {
                list.clear();
                adapterAu.clear();
                preList.get(position).getPrescription().forEach(e->{
                    int i  = preList.get(position).getPrescription().indexOf(e);
                    DrugAndWeightAndCount count = new DrugAndWeightAndCount();
                    count.setWeight(e.getWeight());
                    count.setMedicineName(e.getMedicineName());
                    count.setCount(i);
                    list.add(count);
                });
                adapterAu.addAll(list);
                adapterAu.notifyDataSetChanged();
                showPop(String .valueOf(preList.get(position).getPrice()));
             }
            }
        );
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void isRightAndGetInfo(boolean result, String reason, List<Prescription> prescriptionList) {
        if (result){
            if (prescriptionList!=null){
                timeList.clear();
                prescriptionList.forEach(e->{
                    preList.add(e);
                   String t =e.getTime().substring(5,7)+"月"+" "+e.getTime().substring(8,10)+"日"+" "+e.getTime().substring(11,13)+"时";
                   timeList.add(t);
                });
                adapter.clear();
                adapter.addAll(timeList);
                adapter.notifyDataSetChanged();
            }
        }else
            Toast.makeText(RecordOfPrescriptionActivity.this,reason,Toast.LENGTH_SHORT).show();
    }

    //展示第一层每个item的popup
    public void showPop(String price){
        //pop 的布局
        View contentView = LayoutInflater.from(this).inflate(R.layout.record_second_item,null);
        //得到window管理对象
        WindowManager manager = this.getWindowManager();
        //得到显示屏矩形对象
        DisplayMetrics displayMetrics = new DisplayMetrics();
        //让manager 管理显示屏
        manager.getDefaultDisplay().getMetrics(displayMetrics);
        //获取长宽
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        //创建pop对象
        pop = new PopupWindow(contentView,width,height,true);
        pop.setContentView(contentView);
        //绑定控件
        TextView back = contentView.findViewById(R.id.prescription_record_pup_back);
        TextView p = contentView.findViewById(R.id.prescription_record_pup_price);
        EasyRecyclerView rec  = contentView.findViewById(R.id.prescription_record_pup_easy);
        //设定数据
        rec.setAdapter(adapterAu);
        rec.setLayoutManager(new LinearLayoutManager(this));
        String pri = "总价格："+price+" 元";
        p.setText(pri);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pop.dismiss();
            }
        });

        View root = LayoutInflater.from(this).inflate(R.layout.activity_record_of_prescription,null);
        pop.setAnimationStyle(R.style.PopUpWindowShow);
        pop.setFocusable(true);
        pop.setTouchable(true);
        pop.setOutsideTouchable(false);
        pop.showAtLocation(root, Gravity.CENTER,0,0);
    }}

