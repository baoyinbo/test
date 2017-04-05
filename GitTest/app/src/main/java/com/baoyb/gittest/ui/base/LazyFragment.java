package com.baoyb.gittest.ui.base;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.baoyb.gittest.R;
import com.baoyb.gittest.util.StatusBarUtil;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Fragment基类
 * Created by baoyb on 2017/3/9.
 *
 * 若把初始化内容放到initData实现,就是采用Lazy方式加载的Fragment
 * 若不需要Lazy加载则initData方法内留空,初始化内容放到initViews即可
 * -
 * -注1: 如果是与ViewPager一起使用，调用的是setUserVisibleHint。
 * ------可以调用mViewPager.setOffscreenPageLimit(size),若设置了该属性 则viewpager会缓存指定数量的Fragment
 * -注2: 如果是通过FragmentTransaction的show和hide的方法来控制显示，调用的是onHiddenChanged.
 * -注3: 针对初始就show的Fragment 为了触发onHiddenChanged事件 达到lazy效果 需要先hide再show
 */


public abstract class LazyFragment extends Fragment {


    protected String fragmentTitle;             //fragment标题
    private boolean isVisible;                  //是否可见状态
    private boolean isPrepared;                 //标志位，View已经初始化完成。
    private boolean isFirstLoad;                //是否第一次加载
    protected LayoutInflater inflater;
    private View rootView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.statusBarLightMode(this.getActivity());
        isFirstLoad = true;
        isPrepared = true;
        isVisible = true;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        if(rootView==null){
            rootView=inflater.inflate(getLayoutId(), container, false);
        }
        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
//        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lazyLoad(savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        // 此方法只是在FragmentPagerAdapter或者FragmentStatePagerAdapter中显示或者不显示才会调用的方法
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }

    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        // 此方法在是在调用FragmentTransaction.hide或者FragmentTransaction.show的时候才会调用的方法
        if (hidden) {
            isVisible = false;
            onInvisible();
        } else {
            isVisible = true;
            onVisible();
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public LazyAppCompatActivity getCurrContext() {
        return (LazyAppCompatActivity)getActivity();
    }


    /** 子类可以重写改变状态栏颜色 */
    protected int setStatusBarColor() {
        return getColorPrimary();
    }


    /** 获取主题色 */
    public int getColorPrimary() {
        TypedValue typedValue = new TypedValue();
        this.getActivity().getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }

    /** 设置状态栏颜色 */
    public void initSystemBarTint(boolean isTranslucent, int colorId) {
        Window window = this.getActivity().getWindow();
        ViewGroup mContentView = (ViewGroup) getActivity().findViewById(Window.ID_ANDROID_CONTENT);
        SystemBarTintManager tintManager = new SystemBarTintManager(this.getActivity());
        SystemBarTintManager.SystemBarConfig config = tintManager.getConfig();

        //首先使 ChildView 不预留空间
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            ViewCompat.setFitsSystemWindows(mChildView, false);
        }

        int statusBarHeight = config.getStatusBarHeight();
        if (mChildView != null && mChildView.getLayoutParams() != null
                && mChildView.getLayoutParams().height == statusBarHeight) {
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
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                this.getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
            StatusBarUtil.statusBarLightMode(this.getActivity());
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
            this.getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintColor(getResources().getColor(colorId));
        }
    }

    /**
     * 根据id查找view
     *
     * @param viewId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public View findViewById(int viewId) {
        if (getView() != null) {
            return getView().findViewById(viewId);
        }
        return null;
    }

    /**
     * view初始化后时可见的情况下调用(不管是否重新加载view都会调用)
     *
     * @see [类、类#方法、类#成员]
     */
    public void onVisible() {

    }

    ;

    /**
     * view初始化后时不可见的情况下调用(不管是否重新加载view都会调用)
     *
     * @see [类、类#方法、类#成员]
     */
    public void onInvisible() {

    }

    /**
     * 设置layout
     * @return
     */
    public abstract int getLayoutId();

    protected void lazyLoad(Bundle savedInstanceState) {
        if (!isPrepared || !isVisible || !isFirstLoad) {
            return;
        }
        isFirstLoad = false;
        initView(savedInstanceState);
    }

    /**
     * 在viewpager中第一次初始化view后时才会调用
     *
     * @param savedInstanceState activity被意外回收所保存的数据
     * @see [类、类#方法、类#成员]
     */
    public abstract void initView(Bundle savedInstanceState);

    /***
     * 动画结束执行方法
     */
    public void onAnimationEnd() {

    }
}
