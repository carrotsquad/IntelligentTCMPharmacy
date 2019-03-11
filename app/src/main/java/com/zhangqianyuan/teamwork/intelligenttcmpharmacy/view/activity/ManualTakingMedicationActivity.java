package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.R;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.DrugAndWeight;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.contract.GetPrescriptionSelfContract;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.presenter.GetPrescriptionSelfPresenter;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.presenter.MedicineSearchPresenter;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.util.system.DrugAndWeightItem;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.fragment.PersonFragment;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 手动取药界面
 */

public class ManualTakingMedicationActivity extends AppCompatActivity implements GetPrescriptionSelfContract.GetPrescriptionSelfView {
    @BindView(R.id.self_tack_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.self_take_med_more)
    ImageView more;

    @BindView(R.id.self_take_floatbutton)
    FloatingActionButton ok;

    @BindView(R.id.disease_input)
    EditText disease;

    public static final String T = "ManualTakingMedication";
    private int no = 0;
    private GetPrescriptionSelfPresenter presenter;
    final List<DrugAndWeightItem> items = new ArrayList<>();
    final List<String> name = new ArrayList<>();
    final List<String> weight = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_taking_medication);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        presenter = new GetPrescriptionSelfPresenter();
        presenter.attachActivty(this);
        LinearLayout mainLinerLayout = (LinearLayout) this.findViewById(R.id.MyTable);
        headInfor();
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int n = ++no;
                final DrugAndWeightItem drugAndWeightItem = new DrugAndWeightItem(ManualTakingMedicationActivity.this);
                drugAndWeightItem.setNo(String.valueOf(n));
                items.add(drugAndWeightItem);
                Log.d(T,"hahha"+items.toString());
                mainLinerLayout.addView(drugAndWeightItem);
            }
        });
    }

    //获得一个drugAndWeight列表
    public List<DrugAndWeight> getDrugAndWeightList(List<String> n, List<String> w) {
        List<DrugAndWeight> prescription = new ArrayList<>();
       for (int i=0;i<n.size();i++){
            DrugAndWeight drugAndWeight = new DrugAndWeight();
            drugAndWeight.setMedicineName(n.get(i));
            drugAndWeight.setWeight(Integer.parseInt(w.get(i)));
            prescription.add(drugAndWeight);
        }

        return prescription;
    }


    //写好信息后点击勾勾 传给服务器
    public void headInfor() {
        ok.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {

//                for (int i=0;i<items.size();i++) {
//                    name.add(items.get(i).getName().getText().toString());
//                    weight.add(items.get(i).getWeight().getText().toString());
//                }
                items.forEach(e->{
                    name.add(e.getName().getText().toString());
                    weight.add(e.getWeight().getText().toString());
                });
                //先检查 两个数组里头是否有空值
                if(!isEmpty(name,weight)){
                    Toast.makeText(ManualTakingMedicationActivity.this,"药名或质量有值未输入",Toast.LENGTH_SHORT)
                            .show();
                }else if(disease.getText().toString().equals("")){
                Toast.makeText(ManualTakingMedicationActivity.this, "病症输入为空", Toast.LENGTH_SHORT)
                        .show(); }
                else{
                presenter.GetPrescriptionSelf(disease.getText().toString(), true, getDrugAndWeightList(name,weight));
            }
        }
  });
    }



    @Override
    public void isRightAndReturnInfo(boolean result, String reason, int prescriptionId, double price, String Infor) {
        if (result) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dettachActivity();
    }

    //判断两个数组是否为空
    public boolean isEmpty(List<String> n,List<String> w) {
    boolean isNotEmpty = true;
    for (String name:n) {
         if (name.equals(""))
             isNotEmpty = false;
    }

    for (String weight:w){
        if (weight.equals(""))
            isNotEmpty = false;
    }
    return isNotEmpty;
}

}

