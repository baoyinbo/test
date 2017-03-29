package com.baoyb.gittest.ui.home;

import android.os.Bundle;
import android.view.View;
import com.baoyb.gittest.model.BybHomeNewModel;
import com.baoyb.gittest.model.BybHomeNewsModel;
import com.baoyb.gittest.net.BybApiManager;
import com.baoyb.gittest.net.BybRequestCallback;
import com.baoyb.gittest.ui.base.BaseListFragment;
import com.baoyb.gittest.ui.news.adapter.BybHomeNewsAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by baoyinbo on 2017/3/17.
 */

public class BybTabFragment extends BaseListFragment {
    private BybHomeNewsAdapter newsAdapter;
    private List<BybHomeNewsModel> homeNewModelList;
    private static String CHANNEL;
    private static String cate_id;
    private int page = 0;

    public static BybTabFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString("channel", title);
        BybTabFragment fragment = new BybTabFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void onCreateListView(Bundle savedInstanceState) {
        getData();
        newsAdapter = new BybHomeNewsAdapter(BybTabFragment.this.getContext(), new ArrayList<BybHomeNewsModel>());
        newsAdapter.setOnRecyclerViewItemClickListener(
                new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {

            }
        });
        setAdapter(newsAdapter);
        homeNewModelList = new ArrayList<BybHomeNewsModel>();

        BybApiManager.getInstance().getNewsRecommend(cate_id, page + "",
                new BybRequestCallback(BybHomeNewModel.class) {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(Object response, int id) {
                        BybHomeNewModel model = (BybHomeNewModel)response;
                        homeNewModelList = model.getData();
                        newsAdapter.setNewData(homeNewModelList);
                    }
                });
    }

    @Override
    protected void onPullDownRefreshListener() {

    }

    @Override
    protected void onLoadMoreListener() {

    }

    private void getData() {
        if (getArguments() != null) {
            cate_id = getArguments().getString("channel");
        }
    }

}
