package com.baoyb.gittest.ui.main;

import android.os.Bundle;
import android.view.View;

<<<<<<< HEAD
import com.baoyb.gittest.commen.BybDefines;
=======
import com.baoyb.gittest.R;
>>>>>>> origin/master
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

<<<<<<< HEAD
public class BybHomeFragment extends BaseListFragment {
    private int pageNum = 1;    //当前页数
    private BybHomeNewsAdapter newsAdapter;
    private List<BybHomeNewModel> homeNewModelList;

    @Override
    protected void onCreateListView(Bundle savedInstanceState) {
        newsAdapter = new BybHomeNewsAdapter(new ArrayList<BybHomeNewModel>());
        newsAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {

            }
        });
        newsAdapter.openLoadMore(BybDefines.pageSize, true);
        setAdapter(newsAdapter);
        test(pageNum);
    }

    private void test(int pageNum) {
        homeNewModelList = new ArrayList<>();
        for (int i = pageNum * 10; i < pageNum * 10 + 10; i ++) {
            BybHomeNewModel model = new BybHomeNewModel();
            model.setTitle("今日头条新闻 -- " + i);
            model.setSrc("网易新闻 -- " + i);
            model.setPdate(i + "分钟前");
            homeNewModelList.add(model);
        }
        if (pageNum > 1) {
            newsAdapter.addData(homeNewModelList);
            newsAdapter.notifyDataChangedAfterLoadMore(true);
        } else {
            newsAdapter.setNewData(homeNewModelList);
        }
    }

    @Override
    protected void onPullDownRefreshListener() {
        pageNum = 1;
        test(pageNum);
    }

    @Override
    protected void onLoadMoreListener() {
        test(++ pageNum);
    }
=======
public class BybHomeFragment extends BaseFragment {

    @Override
    public int getLayoutId() {
        return R.layout.byb_fra_home;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        initSystemBarTint(false, R.color.background_red);
    }

>>>>>>> origin/master
}
