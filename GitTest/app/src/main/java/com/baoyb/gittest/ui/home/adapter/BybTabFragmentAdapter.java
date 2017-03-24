package com.baoyb.gittest.ui.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.baoyb.gittest.ui.home.BybTabFragment;


/**
 * Created by baoyinbo on 2017/3/17.
 */

public class BybTabFragmentAdapter extends FragmentPagerAdapter {
    private String[] channels;
    public BybTabFragmentAdapter(String[] channels, FragmentManager fm) {
        super(fm);
        this.channels = channels;
    }

    @Override
    public Fragment getItem(int position) {
        return BybTabFragment.newInstance(channels[position]);
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
