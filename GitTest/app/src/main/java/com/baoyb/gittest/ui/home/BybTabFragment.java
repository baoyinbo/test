package com.baoyb.gittest.ui.home;
import android.os.Bundle;
import android.view.View;

import com.baoyb.gittest.R;
import com.baoyb.gittest.model.BybHomeNewModel;
import com.baoyb.gittest.model.BybHomeNewsModel;
import com.baoyb.gittest.net.BybApiManager;
import com.baoyb.gittest.net.BybRequestCallback;
import com.baoyb.gittest.net.BybUrlDefines;
import com.baoyb.gittest.ui.base.BaseListFragment;
import com.baoyb.gittest.ui.base.CommomActivity;
import com.baoyb.gittest.ui.base.LaunchBody;
import com.baoyb.gittest.ui.common.BybDefaultLoadingView;
import com.baoyb.gittest.ui.common.BybWebViewFragment;
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
    private int page = 1;
    private BybDefaultLoadingView loadingView;
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
        loadingView = getLoadingView();
        newsAdapter = new BybHomeNewsAdapter(BybTabFragment.this.getContext(), new ArrayList<BybHomeNewsModel>());
        newsAdapter.setOnRecyclerViewItemClickListener(
                new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                Bundle bundle = new Bundle();
                bundle.putString("url", BybUrlDefines.URL_BASE_NEWS + homeNewModelList.get(i).getUrl());
                LaunchBody.Builder builder = new LaunchBody.Builder(BybTabFragment.this,
                        BybWebViewFragment.class);
                builder.bundle(bundle);
                builder.launchType(LaunchBody.LaunchType.SINGLE_TOP);
                CommomActivity.launch(builder);
            }
        });
        newsAdapter.openLoadMore(8, true);
        addOnLoadView();
        setAdapter(newsAdapter);
        homeNewModelList = new ArrayList<BybHomeNewsModel>();
        loadingView.loading();
        refreshView();

    }

    private void addOnLoadView() {
        View loadView =View.inflate(getContext(), R.layout.byb_comm_list_load_more, null);
        newsAdapter.setLoadingView(loadView);
    }

    @Override
    protected void onPullDownRefreshListener() {
        page = 1;
        refreshView();
    }

    @Override
    protected void onLoadMoreListener() {
        page ++;
        refreshView();
    }

    private void refreshView() {
        BybApiManager.getInstance().getNewsRecommend(cate_id, page + "",
                new BybRequestCallback(BybHomeNewModel.class) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        completePullDownRefresh();
                    }

                    @Override
                    public void onResponse(Object response, int id) {
                        loadingView.content();
                        completePullDownRefresh();
                        BybHomeNewModel model = (BybHomeNewModel)response;
                        homeNewModelList = model.getData();
                        if (homeNewModelList != null) {
                            if (page == 1) {
                                newsAdapter.setNewData(homeNewModelList);
                                newsAdapter.openLoadMore(8, true);
                            } else {
                                newsAdapter.notifyDataChangedAfterLoadMore(homeNewModelList, true);
                            }
                        }
                    }
                });
    }

    private void getData() {
        if (getArguments() != null) {
            cate_id = getArguments().getString("channel");
        }
    }

}
