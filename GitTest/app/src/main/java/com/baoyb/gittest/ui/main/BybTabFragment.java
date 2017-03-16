package com.baoyb.gittest.ui.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.baoyb.gittest.R;
import com.baoyb.gittest.ui.base.BaseFragment;

/**
 * Created by baoyb on 2017/3/9.
 */

public class BybTabFragment extends BaseFragment implements View.OnClickListener, TabHost.OnTabChangeListener {
    public static final String TAB_HOME = "TAB_HOME";   //首页
    public static final String TAB_VIDIO = "TAB_VIDIO"; //视频
    public static final String TAB_ATTENTION = "TAB_ATTENTION"; //关注
    public static final String TAB_MINE = "TAB_MINE";   //我的

    private static final String[] tabs = {
            TAB_HOME, TAB_VIDIO, TAB_ATTENTION, TAB_MINE
    };
    private TabHost tabHost;
    private Fragment[] fragments = new Fragment[4];
    private int currentTab; //当前tab

    @Override
    public int getLayoutId() {
        return R.layout.byb_fra_tabs;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            currentTab = savedInstanceState.getInt("currentTab");
            FragmentManager fm = getFragmentManager();
            if (fm != null) {
                for (int i = 0; i < tabs.length; i ++) {
                    fragments[i] = fm.findFragmentByTag(tabs[i]);
                }
            }
        }

        tabHost = (TabHost) getView();
        setupTabs();
        tabHost.setOnTabChangedListener(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("currentTab", tabHost.getCurrentTab());
        super.onSaveInstanceState(outState);
    }

    private void setupTabs() {
        tabHost.setup();
        tabHost.addTab(newTab(tabs[0], R.string.tab_home, R.drawable.byb_tab_home_selector));
        tabHost.addTab(newTab(tabs[1], R.string.tab_vidio, R.drawable.byb_tab_vidio_selector));
        tabHost.addTab(newTab(tabs[2], R.string.tab_attention, R.drawable.byb_tab_attention_selector));
        tabHost.addTab(newTab(tabs[3], R.string.tab_mine, R.drawable.byb_tab_mine_selector));
        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            tabHost.getTabWidget().getChildAt(i).setOnClickListener(this);
        }
    }

    private TabHost.TabSpec newTab(String tag, int labelId, int drawableId) {
        View indicator = LayoutInflater.from(getActivity()).inflate(
                R.layout.byb_fra_tabs, (ViewGroup) findViewById(android.R.id.tabs), false
        );
        indicator.setTag(tag);
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int tab_width = dm.widthPixels / 4;
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) indicator.getLayoutParams();
        params.width = tab_width;
        indicator.setLayoutParams(params);
        ((TextView)indicator.findViewById(R.id.tvTabItem)).setText(labelId);
        ((ImageView)indicator.findViewById(R.id.ivTabItem)).setImageResource(drawableId);

        TabHost.TabSpec tabSpec = tabHost.newTabSpec(tag);
        tabSpec.setIndicator(indicator);
        tabSpec.setContent(R.id.layout_table);
        return tabSpec;
    }

    @Override
    public void onClick(View v) {
        int toTab = 0;
        String tag = (String) v.getTag();
        if (tag.equals(tabs[0])) {
            toTab = 0;
        } else if (tag.equals(tabs[1])) {
            toTab = 1;
        } else if (tag.equals(tabs[2])) {
            toTab = 2;
        } else if (tag.equals(tabs[3])) {
            toTab = 3;
        }
        if (toTab == tabHost.getCurrentTab()) {
            return;
        }
        tabHost.setCurrentTab(toTab);
    }

    @Override
    public void onTabChanged(String tabId) {
        upDataTab(tabId);
    }

    private void upDataTab(String tabId) {
        FragmentManager fm = getFragmentManager();
        if (fm == null) {
            return;
        }
        FragmentTransaction transaction = fm.beginTransaction();
        Fragment fragment = fm.findFragmentByTag(tabId);
        if (fragment == null) {
            int index = tabHost.getCurrentTab();
            switch (index) {
                case 0:
                    fragment = new BybHomeFragment();
                    break;
                case 1:
                    fragment = new BybVidioFragment();
                    break;
                case 2:
                    fragment = new BybAttentionFragment();
                    break;
                case 3:
                    fragment = new BybMineFragment();
                    break;
            }
            fragments[index] = fragment;
            transaction.add(android.R.id.tabcontent, fragment, tabId);
            for (Fragment mFragment : fragments) {
                if (mFragment == null) {
                    continue;
                }
                if (mFragment != fragment && !mFragment.isHidden()) {
                    transaction.hide(mFragment);
                }
            }
            transaction.commitAllowingStateLoss();
        } else {
            for (Fragment mFragment : fragments) {
                if (mFragment == null) {
                    continue;
                }
                if (mFragment == fragment) {
                    transaction.show(mFragment);
                } else {
                    transaction.hide(mFragment);
                }
            }
            transaction.commitAllowingStateLoss();
        }
    }
}
