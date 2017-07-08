package com.baoyb.gittest.ui.base;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnticipateInterpolator;

import com.baoyb.defaultloadinglibrary.DefaultLoadingView;
import com.baoyb.gittest.R;
import com.chad.library.adapter.base.BaseQuickAdapter;

import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;

/**
 * 列表功能的fragment
 * Created by baoyb on 2017/3/16.
 */

public abstract class BaseListFragment extends BaseFragment{
    private RecyclerView recyclerView;
    private BaseQuickAdapter adapter;
    private PtrFrameLayout ptrFrameLayout;
    private DefaultLoadingView loadingView;
    @Override
    public int getLayoutId() {
        return R.layout.byb_fra_base_list;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        loadingView = (DefaultLoadingView) findViewById(R.id.loadingView);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        ptrFrameLayout = (PtrFrameLayout) findViewById(R.id.refreshView);
        initPtrFrameLayout();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //设置分隔线
//        recyclerView.addItemDecoration(new DividerGridItemDecoration(getActivity()));
        //设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        onCreateListView(savedInstanceState);
    }


    private void initPtrFrameLayout() {
        ptrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                onPullDownRefreshListener();
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
        PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(BaseListFragment.this.getContext());
        ptrFrameLayout.setHeaderView(header);
        ptrFrameLayout.addPtrUIHandler(header);
    }

    protected DefaultLoadingView getLoadingView() {
        return loadingView;
    }

    /**
     * 获取recyclerView
     * @return
     */
    protected RecyclerView getRecyclerView() {
        return recyclerView;
    }

    /**
     * 获取下拉刷新控件
     *
     * @return
     */
    public PtrFrameLayout getSwipeRefreshLayout() {
        return ptrFrameLayout;
    }
    /**
     * 设置适配器列表
     * @param adapter
     */
    protected void setAdapter(BaseQuickAdapter adapter) {
        this.adapter = adapter;
        this.adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                onLoadMoreListener();
            }
        });
        this.adapter.openLoadMore(true);
//        recyclerView.setAdapter(this.adapter);

        SlideInBottomAnimationAdapter slideAdapter = new SlideInBottomAnimationAdapter(this.adapter);
        slideAdapter.setFirstOnly(true);
        slideAdapter.setDuration(500);
        slideAdapter.setInterpolator(new AnticipateInterpolator(1f));
        recyclerView.setAdapter(slideAdapter);
    }

    /**
     * 创建列表,程序从这里开始
     * @param savedInstanceState
     */
    protected abstract void onCreateListView(Bundle savedInstanceState);

    /**
     * 结束下拉刷新
     */
    protected void completePullDownRefresh() {
        ptrFrameLayout.refreshComplete();
    }

    /***
     * 下拉刷新回调方法
     */
    protected abstract void onPullDownRefreshListener();

    /***
     * 加载更多回调方法
     */
    protected abstract void onLoadMoreListener();
}
