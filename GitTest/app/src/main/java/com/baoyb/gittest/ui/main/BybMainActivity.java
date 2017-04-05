package com.baoyb.gittest.ui.main;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.baoyb.gittest.R;
import com.baoyb.gittest.ui.base.BaseActivity;
import com.baoyb.gittest.util.ToastShowUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by baoyb on 2017/3/9.
 */

public class BybMainActivity extends BaseActivity {
    private FragmentTabHost fragmentTabHost;
    public static final String TAB_HOME = "TAB_HOME";   //首页
    public static final String TAB_VIDIO = "TAB_VIDIO"; //视频
    public static final String TAB_ATTENTION = "TAB_ATTENTION"; //关注
    public static final String TAB_MINE = "TAB_MINE";   //我的

    private static final String[] tabs = {
            TAB_HOME, TAB_VIDIO, TAB_ATTENTION, TAB_MINE
    };
    private static final String[] tabText = {"首页", "视频", "关注", "我的"};
    private static final int[] tabImage = {
      R.drawable.byb_tab_home_selector, R.drawable.byb_tab_vidio_selector,
            R.drawable.byb_tab_attention_selector, R.drawable.byb_tab_mine_selector
    };
    private static final Class[] fragments = {
      BybHomeFragment.class, BybVidioFragment.class, BybAttentionFragment.class,
            BybMineFragment.class
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void doCreate(Bundle savedInstanceState) {
        setSwipeBackEnable(false);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {

        fragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        fragmentTabHost.setup(this, getSupportFragmentManager(), R.id.maincontent);

        for (int i = 0; i < tabs.length; i ++) {
            TabHost.TabSpec spec = fragmentTabHost.newTabSpec(tabs[i]).
                    setIndicator(getIndicatorView(i));
            fragmentTabHost.addTab(spec, fragments[i], null);
        }
    }


    private View getIndicatorView(int i) {
        View view = View.inflate(this, R.layout.byb_tab_item, null);
        ImageView ivTabItem = (ImageView) view.findViewById(R.id.ivTabItem);
        TextView tvTabItem = (TextView) view.findViewById(R.id.tvTabItem);
        ivTabItem.setImageResource(tabImage[i]);
        tvTabItem.setText(tabText[i]);
        return view;
    }

    private int isFirstBack = 1;
    @Override
    public void onBackPressed() {
        if (isFirstBack == 1) {
            ToastShowUtils.showTextToast("再按一次退出");
            isFirstBack = 3;
            //开启一个异步线程，当用户超过两秒没有再次点击返回键，则取消退出状态
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isFirstBack = 1; // 取消退出
                }
            }, 1000);
        } else if (isFirstBack == 3) {//单用户连续点击两次的时候，退出程序
            this.finish();
            System.exit(0);
        }
    }
}
