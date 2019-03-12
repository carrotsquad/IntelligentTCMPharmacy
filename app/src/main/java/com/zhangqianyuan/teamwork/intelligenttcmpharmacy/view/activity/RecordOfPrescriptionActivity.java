package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.activity;

import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.R;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.adapter.RecordOfPrescriptionAdapter;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.DrugAndWeight;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.Prescription;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.contract.RecordOfPrescription;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.presenter.RecordOfPrescriptionPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecordOfPrescriptionActivity extends AppCompatActivity implements RecordOfPrescription.RecordOfPrescriptionView {

    @BindView(R.id.record_toolbar)
    Toolbar mToolbar ;

    @BindView(R.id.record_easy)
    EasyRecyclerView rec;

   // 第一层recycle  时间列表
    private List<String>   timeList = new ArrayList<>();
    //药方列表  第二层列表
    private List<DrugAndWeight>  list = new ArrayList<>();
    private RecordOfPrescriptionPresenter presenter;
    private RecordOfPrescriptionAdapter   adapter;
    private SharedPreferences shar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_of_prescription);
        ButterKnife.bind(this);
        shar = getSharedPreferences("user",MODE_PRIVATE);
        presenter = new RecordOfPrescriptionPresenter();
        presenter.attachActivty(this);
        presenter.getRecordOfPrescription(shar.getString("userphone",null));
        adapter = new RecordOfPrescriptionAdapter(this);
        rec.setLayoutManager(new LinearLayoutManager(this));
        rec.setAdapter(adapter);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void isRightAndGetInfo(boolean result, String reason, List<Prescription> prescriptionList) {
        if (result){
            if (prescriptionList!=null){
                timeList.clear();
                prescriptionList.forEach(e->{
                   String t =e.getTime().substring(5,7)+"月"+" "+e.getTime().substring(8,10)+"时";
                   timeList.add(t);
                });
                adapter.clear();
                adapter.addAll(timeList);
                adapter.notifyDataSetChanged();
            }
        }else
            Toast.makeText(RecordOfPrescriptionActivity.this,reason,Toast.LENGTH_SHORT).show();
    }
}
