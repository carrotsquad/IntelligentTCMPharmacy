package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.DrugAndWeight;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.viewholder.DrugAndWeightItemViewHolder;

/**
 * Description 自动取药adapter
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class GetPrescriptionAutoAdapter extends RecyclerArrayAdapter<DrugAndWeight> {

    public GetPrescriptionAutoAdapter(Context context){
        super(context);
    }
    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new DrugAndWeightItemViewHolder(parent);
    }
}
