package com.baoyb.gittest.ui.main;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.baoyb.gittest.R;
import com.baoyb.gittest.ui.base.BaseFragment;
import com.baoyb.gittest.ui.base.CommomActivity;
import com.baoyb.gittest.ui.base.LaunchBody;
import com.baoyb.gittest.ui.common.BybWebViewFragment;
import com.baoyb.gittest.ui.home.BybHomeChannelEditFragment;
import com.baoyb.gittest.ui.home.BybTabFragment;
import com.baoyb.gittest.ui.home.adapter.BybTabFragmentAdapter;
import com.baoyb.gittest.ui.nba.BybNbaListFragment;
import com.baoyb.gittest.util.ResourceUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baoyb on 2017/3/10.
 */
public class BybHomeFragment extends BaseFragment implements View.OnClickListener {
    private String[] channels = new String[]{
            "推荐", "热点", "杭州", "社会","订阅", "娱乐", "科技", "汽车","体育", "财经", "美女"};
    private ViewPager viewPager;
    private BybTabFragmentAdapter tabFragmentAdapter;
    private TabLayout tabChannel;

    private ImageView ivEditChannel;
    @Override
    public int getLayoutId() {
        return R.layout.byb_fra_home;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ivEditChannel = (ImageView) findViewById(R.id.ivEditChannel);
        ivEditChannel.setOnClickListener(this);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabChannel = (TabLayout) findViewById(R.id.tabChannel);
        tabFragmentAdapter = new BybTabFragmentAdapter(channels, getActivity().getSupportFragmentManager());
        viewPager.setAdapter(tabFragmentAdapter);
        tabChannel.setupWithViewPager(viewPager);
        tabChannel.setTabTextColors(ResourceUtils.getColor(this.getContext(), R.color.text_black),
                ResourceUtils.getColor(this.getContext(), R.color.text_red));
        tabChannel.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        initSystemBarTint(false, R.color.background_red);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivEditChannel:
                LaunchBody.Builder builder = new LaunchBody.Builder(BybHomeFragment.this,
                        BybHomeChannelEditFragment.class);
                builder.launchType(LaunchBody.LaunchType.SINGLE_TOP);
                CommomActivity.launch(builder);
                break;
        }
    }
}
