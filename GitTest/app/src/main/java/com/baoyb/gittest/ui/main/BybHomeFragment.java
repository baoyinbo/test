package com.baoyb.gittest.ui.main;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.baoyb.gittest.R;
import com.baoyb.gittest.ui.base.BaseFragment;
import com.baoyb.gittest.ui.home.BybTabFragment;
import com.baoyb.gittest.ui.home.adapter.BybTabFragmentAdapter;
import com.baoyb.gittest.util.ResourceUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baoyb on 2017/3/10.
 */
public class BybHomeFragment extends BaseFragment {
    private String[] channels = new String[]{
            "推荐", "热点", "杭州", "社会","订阅", "娱乐", "科技", "汽车","体育", "财经", "美女"};
    private List<Fragment> fragments;
    private ViewPager viewPager;
    private BybTabFragmentAdapter tabFragmentAdapter;
    private TabLayout tabChannel;
    @Override
    public int getLayoutId() {
        return R.layout.byb_fra_home;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        initSystemBarTint(false, R.color.background_red);
        fragments = new ArrayList<>();
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabChannel = (TabLayout) findViewById(R.id.tabChannel);
        test();
        tabFragmentAdapter = new BybTabFragmentAdapter(this.getContext(), channels, fragments,
                getActivity().getSupportFragmentManager());
        viewPager.setAdapter(tabFragmentAdapter);
        tabChannel.setupWithViewPager(viewPager);
        tabChannel.setTabTextColors(ResourceUtils.getColor(this.getContext(), R.color.text_black),
                ResourceUtils.getColor(this.getContext(), R.color.text_red));
        tabChannel.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void test() {
        for (int i = 0; i < channels.length; i ++) {
            Fragment fragment = new BybTabFragment();
            Bundle bundle = new Bundle();
            bundle.putString("channel",channels[i]);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
    }
}
