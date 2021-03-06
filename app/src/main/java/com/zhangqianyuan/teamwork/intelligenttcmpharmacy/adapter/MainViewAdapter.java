package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainViewAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments = new ArrayList<>();

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    public MainViewAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
