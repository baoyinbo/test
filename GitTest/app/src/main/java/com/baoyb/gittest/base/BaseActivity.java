package com.baoyb.gittest.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.baoyb.gittest.util.StatusBarUtil;
import com.jude.swipbackhelper.SwipeBackHelper;
import com.jude.swipbackhelper.SwipeBackPage;
import com.jude.swipbackhelper.SwipeListener;


/**
 * activity基类
 * Created by baoyb on 2017/3/8.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private SwipeBackPage swipeBackPage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        StatusBarUtil.statusBarLightMode(this);
        SwipeBackHelper.onCreate(this);
        swipeBackPage = initSwipeBackHelper();
        super.onCreate(savedInstanceState);
        doCreate(savedInstanceState);
        initView();
    }

    /***
     * 初始化SwipeBackHelper
     *
     * @return
     */
    private SwipeBackPage initSwipeBackHelper() {
        return SwipeBackHelper.getCurrentPage(this)//获取当前页面
                .setSwipeBackEnable(true)//设置是否可滑动
//                .setSwipeEdge(200)//可滑动的范围。px。200表示为左边200px的屏幕
//                .setSwipeEdgePercent(0.2f)//可滑动的范围。百分比。0.2表示为左边20%的屏幕
                .setSwipeSensitivity(0.5f)//对横向滑动手势的敏感程度。0为迟钝 1为敏感
//                .setScrimColor(0x7F000000)//底层阴影颜色
//                .setClosePercent(0.5f)//触发关闭Activity百分比
//                .setSwipeRelateEnable(true)//是否与下一级activity联动(微信效果)。默认关
                .setSwipeRelateOffset(300)//activity联动时的偏移量。默认500px。
                /*.setDisallowInterceptTouchEvent(true)*/;//不抢占事件，默认关（事件将先由子View处理再由滑动关闭处理）;
    }

    /**
     * 添加滑动监听器
     *
     * @param listener
     */
    protected void addSwipeListener(SwipeListener listener) {
        getCurrentPage().addListener(listener);
    }

    /***
     * 设置是否能够滑动退出activity
     *
     * @param enable
     */
    protected void setSwipeBackEnable(boolean enable) {
        getCurrentPage().setSwipeBackEnable(enable);
    }

    /**
     * 获取当前SwipeBack页面
     *
     * @return
     */
    protected SwipeBackPage getCurrentPage() {
        return swipeBackPage;
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        SwipeBackHelper.onPostCreate(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        swipeBackPage = null;
        SwipeBackHelper.onDestroy(this);
    }

    /**
     * 创建activity
     *
     * @param savedInstanceState
     * @see [类、类#方法、类#成员]
     */
    public abstract void doCreate(Bundle savedInstanceState);

    /**
     * 初始化
     *
     * @see [类、类#方法、类#成员]
     */
    public abstract void initView();
}
