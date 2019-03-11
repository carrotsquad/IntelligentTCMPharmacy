package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.viewholder;

import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.R;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.DrugAndWeight;

/**
 * Description 单个药和重量的item  组成了药方
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class DrugAndWeightItemViewHolder extends BaseViewHolder<DrugAndWeight> {
    public DrugAndWeightItemViewHolder(ViewGroup parent){
        super(parent, R.layout.drugeandweight_item);
    }

    @Override
    public void setData(DrugAndWeight data) {
        EditText name = itemView.findViewById(R.id.item_name_input);
        EditText weight = itemView.findViewById(R.id.item_weight_input);
        TextView no  = itemView.findViewById(R.id.item_no);
        name.setText(data.getMedicineName());
        weight.setText(data.getWeight());
    }
}
