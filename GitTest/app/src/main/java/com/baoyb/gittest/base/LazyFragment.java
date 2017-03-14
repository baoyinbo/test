package com.baoyb.gittest.base;

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
 */

public abstract class LazyFragment extends Fragment {

    /**
     * 标志位，标志已经初始化完成
     */
    private boolean isPrepared;

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
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(savedInstanceState);
        isPrepared = true;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        // 此方法只是在FragmentPagerAdapter或者FragmentStatePagerAdapter中显示或者不显示才会调用的方法
        if (isPrepared) {
            if (getUserVisibleHint()) {
                onVisible();
            } else {
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
                onInvisible();
            } else {
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
