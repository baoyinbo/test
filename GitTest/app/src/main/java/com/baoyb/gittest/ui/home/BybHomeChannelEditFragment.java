package com.baoyb.gittest.ui.home;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.baoyb.gittest.ui.attention.BybWeatherSearchFragment;
import com.baoyb.gittest.ui.base.BaseListActivityFragment;
import com.baoyb.gittest.ui.home.adapter.BybChannelEditAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baoyb on 2017/4/20.
 */

public class BybHomeChannelEditFragment extends BaseListActivityFragment {
    private List<String> myChannels;
    private List<String> recommendChannels;

    private RecyclerView recyclerView;
    private BybChannelEditAdapter adapter;

    @Override
    protected void onCreateListView(Bundle savedInstanceState) {
        initDate();
        recyclerView = getRecyclerView();
        GridLayoutManager mgr=new GridLayoutManager(BybHomeChannelEditFragment.this.getContext(), 4);
        recyclerView.setLayoutManager(mgr);
        RecyclerView.ItemDecoration rt = new RecyclerView.ItemDecoration() {
        };
        recyclerView.addItemDecoration(rt);
        adapter = new BybChannelEditAdapter(new ArrayList<String>());
        recyclerView.setAdapter(adapter);

        adapter.setNewData(myChannels);
    }

    private void initDate() {
        myChannels = new ArrayList<String>();
        myChannels.add("推荐");
        myChannels.add("杭州");
        myChannels.add("国际");
        myChannels.add("图片");
        myChannels.add("科技");
        myChannels.add("汽车");
        myChannels.add("笑话");
        myChannels.add("旅游");
        myChannels.add("社会");

        recommendChannels = new ArrayList<String>();
        recommendChannels.add("历史");
        recommendChannels.add("娱乐");
        recommendChannels.add("时尚");
        recommendChannels.add("手机");
        recommendChannels.add("电影");
        recommendChannels.add("教育");
        recommendChannels.add("健康");
        recommendChannels.add("时尚");
        recommendChannels.add("育儿");
        recommendChannels.add("文化");
        recommendChannels.add("家居");

    }

    @Override
    protected void onPullDownRefreshListener() {

    }

    @Override
    protected void onLoadMoreListener() {

    }
}
