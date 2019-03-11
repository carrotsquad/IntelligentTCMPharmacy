package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.MutableContextWrapper;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.youth.banner.Banner;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.R;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.util.image.LocalImageLoader;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.util.system.ToActivityUtil;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.activity.GetPrescriptionAUTOActivity;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.activity.ManualTakingMedicationActivity;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectFragment extends Fragment {

    public static final int REQUEST_CODE = 1;


    @BindView(R.id.prescribe_bt)
    ImageView selfTake;
    @BindView(R.id.treat_bt)
    ImageView intelTake;
    @BindView(R.id.banner)
    Banner mBanner;

    private View view;
    private Context context;

    public static Fragment newInstance() {
        SelectFragment fragment = new SelectFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_select, null);
        ButterKnife.bind(this, view);
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

    private void initView() {

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == 1) {
                if (null != data) {
                    Bundle bundle = data.getExtras();
                    if (bundle == null) {
                        return;
                    }
                    if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                        String result = bundle.getString(CodeUtils.RESULT_STRING);
                    } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                        Toast.makeText(context, "没有找到相关信息哦", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    @OnClick({R.id.prescribe_bt, R.id.treat_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.prescribe_bt:
                ToActivityUtil.toNextActivity(getActivity(),ManualTakingMedicationActivity.class);
                break;
            case R.id.treat_bt:
                ToActivityUtil.toNextActivity(getActivity(), GetPrescriptionAUTOActivity.class);
                break;
            default: {
                break;
            }

        }
    }

    //缓存中药百科数据
    public void  cacheDrugInfo(){

    }
}
