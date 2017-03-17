package com.baoyb.gittest.ui.home.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by baoyinbo on 2017/3/17.
 */

public class BybTabFragmentAdapter extends FragmentPagerAdapter {
    private String[] channels;
    private Context context;
    private List<Fragment> fragments;
    public BybTabFragmentAdapter(Context context, String[] channels,
           List<Fragment> fragments, FragmentManager fm) {
        super(fm);
        this.context = context;
        this.channels = channels;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return channels.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return channels[position];
    }
}
