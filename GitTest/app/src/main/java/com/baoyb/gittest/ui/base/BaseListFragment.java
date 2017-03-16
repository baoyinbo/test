package com.baoyb.gittest.ui.base;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.baoyb.gittest.R;
import com.chad.library.adapter.base.BaseQuickAdapter;

/**
 * 列表功能的fragment
 * Created by baoyb on 2017/3/16.
 */

public abstract class BaseListFragment extends BaseFragment{
    private RecyclerView recyclerView;
    private BaseQuickAdapter adapter;
    @Override
    public int getLayoutId() {
        return R.layout.byb_fra_base_list;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
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

    /**
     * 获取recyclerView
     * @return
     */
    protected RecyclerView getRecyclerView() {
        return recyclerView;
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
        recyclerView.setAdapter(this.adapter);
    }

    /**
     * 创建列表,程序从这里开始
     * @param savedInstanceState
     */
    protected abstract void onCreateListView(Bundle savedInstanceState);

    /***
     * 下拉刷新回调方法
     */
    protected abstract void onPullDownRefreshListener();

    /***
     * 加载更多回调方法
     */
    protected abstract void onLoadMoreListener();
}