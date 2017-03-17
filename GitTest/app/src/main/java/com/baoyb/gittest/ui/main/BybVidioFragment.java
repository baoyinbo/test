package com.baoyb.gittest.ui.main;

import android.os.Bundle;
import android.view.View;

import com.baoyb.gittest.R;
import com.baoyb.gittest.model.BybHomeNewModel;
import com.baoyb.gittest.ui.base.BaseFragment;
import com.baoyb.gittest.ui.base.BaseListFragment;
import com.baoyb.gittest.ui.news.adapter.BybHomeNewsAdapter;
import com.baoyb.gittest.util.StatusBarUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by baoyb on 2017/3/10.
 */

public class BybVidioFragment extends BaseListFragment {

    private BybHomeNewsAdapter newsAdapter;
    private List<BybHomeNewModel> homeNewModelList;

    @Override
    protected void onCreateListView(Bundle savedInstanceState) {
        initSystemBarTint(false, R.color.background_red);
        newsAdapter = new BybHomeNewsAdapter(new ArrayList<BybHomeNewModel>());
        newsAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {

            }
        });
        setAdapter(newsAdapter);
        homeNewModelList = new ArrayList<BybHomeNewModel>();
        test();
    }

    private void test() {
        for (int i = 0; i < 30; i ++) {
            BybHomeNewModel model = new BybHomeNewModel();
            model.setTitle("今日头条新闻 -- " + i);
            model.setSrc("网易新闻 -- " + i);
            model.setPdate(i + "分钟前");
            homeNewModelList.add(model);
        }
        newsAdapter.setNewData(homeNewModelList);
    }

    @Override
    protected void onPullDownRefreshListener() {

    }

    @Override
    protected void onLoadMoreListener() {

    }
}
