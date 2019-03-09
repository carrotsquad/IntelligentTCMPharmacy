package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.adapter;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.Medicine;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.viewholder.SearchItemViewHolder;

public class SearchItemAdapter extends RecyclerArrayAdapter<Medicine> {



    public SearchItemAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("SearchItemAdapter","OnCreateViewHolder");
        return new SearchItemViewHolder(parent);
    }
}
