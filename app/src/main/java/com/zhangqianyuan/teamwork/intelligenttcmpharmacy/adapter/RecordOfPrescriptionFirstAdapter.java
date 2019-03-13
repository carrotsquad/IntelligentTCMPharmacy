package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.Prescription;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.viewholder.RecordOfPrescriptionViewHolder;

/**
 * Description 取药记录的第一层recycle ->time
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class RecordOfPrescriptionFirstAdapter extends RecyclerArrayAdapter<String> {

    public RecordOfPrescriptionFirstAdapter(Context context){
        super(context);
    }
    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return  new RecordOfPrescriptionViewHolder(parent);
    }
}
