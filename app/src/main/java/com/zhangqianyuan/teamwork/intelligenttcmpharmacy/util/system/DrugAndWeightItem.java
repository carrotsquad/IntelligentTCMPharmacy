package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.util.system;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.R;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.DrugAndWeight;

/**
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class DrugAndWeightItem extends RelativeLayout {
    private TextView no;
    private EditText name;
    private EditText weight;

    public DrugAndWeightItem(Context context){
        super(context);
        LayoutInflater.from(context).inflate(R.layout.drugandweight_item,this);
        no = findViewById(R.id.self_take_med_no);
        name = findViewById(R.id.self_take_drugname);
        weight = findViewById(R.id.self_take_drugweight);
    }

    //为no设置值
    public void setNo(String number){
        no.setText(number);
    }

    //获取name 和weight的值并组成一个drugAndWeight 对象
    public static  DrugAndWeight getNameAndWeightToDAW(String n,String w){
        DrugAndWeight drugAndWeight = new DrugAndWeight();
        if (!n.equals("")&&!w.equals("")){
            drugAndWeight.setMedicineName(n);
            drugAndWeight.setWeight(Integer.parseInt(w));
            return drugAndWeight;
        }else
            return null;
    }

    public EditText getName(){
        return name;
    }

    public EditText getWeight(){
        return weight;
    }

}
