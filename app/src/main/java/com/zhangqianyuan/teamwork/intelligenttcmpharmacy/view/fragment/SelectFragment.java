package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.youth.banner.Banner;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.R;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.util.image.GlideImageLoader;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.util.image.LocalImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectFragment extends Fragment {


    @BindView(R.id.prescribe_bt)
    ImageView mPrescribeBt;
    @BindView(R.id.treat_bt)
    ImageView mTreatBt;
    @BindView(R.id.banner)
    Banner mBanner;

    private View view;
    private Context context;

    public static Fragment newInstance(){
        SelectFragment fragment = new SelectFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_select, null);
        ButterKnife.bind(this,view);
        context = getContext();
        initView();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void initView(){

        //广告轮播图
        mBanner.setImageLoader(new LocalImageLoader());
        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.scroll_image_1);
        images.add(R.drawable.scroll_image_2);
        images.add(R.drawable.scroll_image_3);
        images.add(R.drawable.scroll_image_4);
        images.add(R.drawable.scroll_image_5);

        mBanner.setImages(images);
        mBanner.start();
    }
}
