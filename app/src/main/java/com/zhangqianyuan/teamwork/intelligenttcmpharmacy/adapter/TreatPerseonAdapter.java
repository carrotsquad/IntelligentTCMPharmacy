package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.R;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.TreatPersonBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class TreatPerseonAdapter extends RecyclerView.Adapter<TreatPerseonAdapter.ViewHolder> {
    private Context mContext;
    private List<TreatPersonBean> mTreatPersonBeans ;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.treat_list_item,null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         TreatPersonBean bean = mTreatPersonBeans.get(position);
         holder.name.setText(bean.getName());
         holder.phone.setText(bean.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return mTreatPersonBeans.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.treat_name)
        TextView name;
        @BindView(R.id.treat_phone)
        TextView phone;

        public ViewHolder(View view){
            super(view);
            ButterKnife.bind(view);
        }
    }


    public TreatPerseonAdapter(List<TreatPersonBean> list){
        this.mTreatPersonBeans = list;
    }



}
