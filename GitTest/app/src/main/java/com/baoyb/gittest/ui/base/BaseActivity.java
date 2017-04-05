package com.baoyb.gittest.ui.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.baoyb.gittest.R;
import com.baoyb.gittest.util.StatusBarUtil;
import com.baoyb.gittest.util.ToastShowUtils;
import com.jude.swipbackhelper.SwipeBackHelper;
import com.jude.swipbackhelper.SwipeBackPage;
import com.jude.swipbackhelper.SwipeListener;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.Timer;
import java.util.TimerTask;


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

    /** 子类可以重写改变状态栏颜色 */
    protected int setStatusBarColor() {
        return getColorPrimary();
    }


    /** 获取主题色 */
    public int getColorPrimary() {
        TypedValue typedValue = new TypedValue();
        this.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }

    /** 设置状态栏颜色 */
    public void initSystemBarTint(boolean isTranslucent, int colorId) {
        Window window = this.getWindow();
        ViewGroup mContentView = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        SystemBarTintManager.SystemBarConfig config = tintManager.getConfig();

        //首先使 ChildView 不预留空间
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            ViewCompat.setFitsSystemWindows(mChildView, false);
        }

        int statusBarHeight = config.getStatusBarHeight();
        if (mChildView != null && mChildView.getLayoutParams() != null && mChildView.getLayoutParams().height == statusBarHeight) {
            //移除假的 View.
            mContentView.removeView(mChildView);
            mChildView = mContentView.getChildAt(0);
        }
        if (mChildView != null) {
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mChildView.getLayoutParams();
            //清除 ChildView 的 marginTop 属性
            if (lp != null) {
                lp.topMargin = statusBarHeight;
                mChildView.setLayoutParams(lp);
            }
        }
        if (isTranslucent) {
            // 设置状态栏全透明
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
            StatusBarUtil.statusBarLightMode(this);
            return;
        }
        // 沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0以上使用原生方法
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(colorId));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //4.4-5.0使用三方工具类，有些4.4的手机有问题，这里为演示方便，不使用沉浸式
            this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintColor(getResources().getColor(colorId));
        }
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
