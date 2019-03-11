package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.activity;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.R;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.adapter.GetPrescriptionAutoAdapter;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.DrugAndWeight;
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
    private static final String T = "GetPrescriptionAUTO";

    private GetPrescriptionAUTOPrensenter presenter;
    private GetPrescriptionAutoAdapter adapter;
    //recycleviewitem 数据集合
    private List<DrugAndWeight> Druglist = new ArrayList<>();
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
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void isRightAndGetInfo(boolean result, List<DrugAndWeight> list, int prescriptionId, double price) {
        if (result){
            if (list!=null){
            Druglist.addAll(list);
                Log.d(T,"hahah"+Druglist.get(0).toString());
            adapter.addAll(Druglist);
            adapter.notifyDataSetChanged();
        }
    }}
}
