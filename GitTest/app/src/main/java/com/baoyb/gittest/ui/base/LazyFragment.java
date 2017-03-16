package com.baoyb.gittest.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    private boolean isFirstLoad = true;         //是否第一次加载
    protected LayoutInflater inflater;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        isFirstLoad = true;
        isPrepared = true;
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lazyLoad(savedInstanceState);
        isPrepared = true;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        // 此方法只是在FragmentPagerAdapter或者FragmentStatePagerAdapter中显示或者不显示才会调用的方法
        if (isPrepared) {
            if (getUserVisibleHint()) {
                isVisible = true;
                onVisible();
            } else {
                isVisible = false;
                onInvisible();
            }
        }
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        // 此方法在是在调用FragmentTransaction.hide或者FragmentTransaction.show的时候才会调用的方法
        if (isPrepared) {
            if (hidden) {
                isVisible = false;
                onInvisible();
            } else {
                isVisible = true;
                onVisible();
            }
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public LazyAppCompatActivity getCurrContext() {
        return (LazyAppCompatActivity)getActivity();
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
