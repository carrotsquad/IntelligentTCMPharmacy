package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.viewholder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.R;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.Prescription;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.RecordOfTakingMadecine;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.presenter.base.BasePresenter;

/**
 * Description 取药记录第一层item ->time
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class RecordOfPrescriptionViewHolder extends BaseViewHolder<String> {
    public RecordOfPrescriptionViewHolder(ViewGroup viewGroup){
        super(viewGroup, R.layout.recordprescription_item);
    }

    @Override
    public void setData(String data) {
        TextView time = itemView.findViewById(R.id.record_item_time);
        time.setText(data);
    }
}
