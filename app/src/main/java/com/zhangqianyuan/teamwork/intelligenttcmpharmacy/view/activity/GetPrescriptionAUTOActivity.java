package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.activity;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.R;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.adapter.GetPrescriptionAutoAdapter;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.DrugAndWeight;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.DrugAndWeightAndCount;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.contract.GetPrescriptionAUTOContract;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.presenter.GetPrescriptionAUTOPrensenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GetPrescriptionAUTOActivity extends AppCompatActivity implements  GetPrescriptionAUTOContract.GetPrescriptionAUTOiew {

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
    private static final String T = "GetPrescriptionAUTO";

    private GetPrescriptionAUTOPrensenter presenter;
    private GetPrescriptionAutoAdapter adapter;
    //recycleviewitem 数据集合
    private ArrayList<DrugAndWeightAndCount> d = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_prescription_auto);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        adapter = new GetPrescriptionAutoAdapter(this);
        rec.setLayoutManager(new LinearLayoutManager(this));
        rec.setAdapter(adapter);

        presenter = new GetPrescriptionAUTOPrensenter();
        presenter.attachActivty(this);
        //设置点开搜索后出现提交按钮
        mSearchView.setSubmitButtonEnabled(true);
        //添加监听
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!query.equals("")){
                    presenter.getPrescriptionAUTO(query);
                }
                if (mSearchView!=null){
                    InputMethodManager methodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (methodManager!=null){
                        //让软键盘在所有情况下被隐藏
                        //输入法如果是显示状态，那么就隐藏输入法
                        methodManager.hideSoftInputFromInputMethod(mSearchView.getWindowToken(),0);
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

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void isRightAndGetInfo(boolean result, List<DrugAndWeight> list, int prescriptionId, double price) {
        if (result){
            if (list!=null){
                d.clear();
                list.forEach(e->{
                    DrugAndWeightAndCount haha = new DrugAndWeightAndCount();
                    haha.setMedicineName(e.getMedicineName());
                    haha.setWeight(e.getWeight());
                    haha.setCount(list.indexOf(e)+1);
                    d.add(haha);
                });
                Log.d(T," "+d.get(0).getWeight());
                String p = "价格："+String.valueOf(price)+" 元";
                priceView.setText(p);
                if (d!=null){
                    adapter.clear();
                    adapter.addAll(d);
                    adapter.notifyDataSetChanged();
                }}}
          else{
              adapter.clear();
              adapter.notifyDataSetChanged();
                }
        }
    }

