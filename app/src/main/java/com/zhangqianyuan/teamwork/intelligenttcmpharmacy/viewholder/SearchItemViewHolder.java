package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.viewholder;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.R;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.Medicine;

public class SearchItemViewHolder extends BaseViewHolder<Medicine> {
    public SearchItemViewHolder(ViewGroup parent) {
        super(parent, R.layout.search_recyclerview_item);
}

    @Override
    public void setData(Medicine data) {
        Log.e("SearchFragment", data.getIntro());
        TextView textView=itemView.findViewById(R.id.search_item_tv);
        textView.setText(data.getIntro().substring(0, 70) + "……");
        ImageView circleImageView=itemView.findViewById(R.id.img);
        Glide.with(getContext()).load(data.getMedicinePic()).into(circleImageView);
    }
}
