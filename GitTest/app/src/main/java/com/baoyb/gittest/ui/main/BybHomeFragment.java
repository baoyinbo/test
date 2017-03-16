package com.baoyb.gittest.ui.main;

import android.os.Bundle;
import com.baoyb.gittest.R;
import com.baoyb.gittest.model.BybHomeNewModel;
import com.baoyb.gittest.ui.base.BaseListFragment;
import com.baoyb.gittest.ui.news.adapter.BybHomeNewsAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by baoyb on 2017/3/10.
 */

public class BybHomeFragment extends BaseListFragment {
    private BybHomeNewsAdapter newsAdapter;
    private List<BybHomeNewModel> homeNewModelList;
    @Override
    public int getLayoutId() {
        return R.layout.byb_fra_home;
    }

    @Override
    protected void onCreateListView(Bundle savedInstanceState) {
        newsAdapter = new BybHomeNewsAdapter(new ArrayList<BybHomeNewModel>());
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
