package com.baoyb.gittest.ui.main;

import android.os.Bundle;
import android.view.View;

import com.baoyb.gittest.R;
import com.baoyb.gittest.model.BybVidioModel;
import com.baoyb.gittest.model.BybVidiosModel;
import com.baoyb.gittest.net.BybApiManager;
import com.baoyb.gittest.net.BybRequestCallback;
import com.baoyb.gittest.net.BybUrlDefines;
import com.baoyb.gittest.ui.base.BaseListFragment;
import com.baoyb.gittest.ui.base.CommomActivity;
import com.baoyb.gittest.ui.base.LaunchBody;
import com.baoyb.gittest.ui.common.BybWebViewFragment;
import com.baoyb.gittest.ui.home.*;
import com.baoyb.gittest.ui.vidio.adapter.BybVidiosAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;


/**
 * Created by baoyb on 2017/3/10.
 */

public class BybVidioFragment extends BaseListFragment {

    private BybVidiosAdapter vidiosAdapter;
    private List<BybVidiosModel> vidiosModelList;
    private int page = 1;

    @Override
    public int getLayoutId() {
        return R.layout.byb_fra_vidio;
    }

    @Override
    protected void onCreateListView(Bundle savedInstanceState) {

        vidiosAdapter = new BybVidiosAdapter(BybVidioFragment.this.getContext(), new ArrayList<BybVidiosModel>());
        vidiosAdapter.openLoadMore(8, true);
        setAdapter(vidiosAdapter);
        addOnLoadView();
        refreshView();

        vidiosAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                Bundle bundle = new Bundle();
                bundle.putString("url", "http://172.16.5.53:8020/Helloworld/index.html");
                LaunchBody.Builder builder = new LaunchBody.Builder(BybVidioFragment.this,
                        BybWebViewFragment.class);
                builder.bundle(bundle);
                builder.launchType(LaunchBody.LaunchType.SINGLE_TOP);
                CommomActivity.launch(builder);
            }
        });
    }

    private void refreshView() {
        BybApiManager.getInstance().getVidioList(page + "",
                new BybRequestCallback(BybVidioModel.class) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        completePullDownRefresh();
                    }

                    @Override
                    public void onResponse(Object response, int id) {
                        completePullDownRefresh();
                        BybVidioModel model = (BybVidioModel)response;
                        vidiosModelList = model.getResults();
                        if (vidiosModelList != null) {
                            if (page == 1) {
                                vidiosAdapter.setNewData(vidiosModelList);
                                vidiosAdapter.openLoadMore(8, true);
                            } else {
                                vidiosAdapter.notifyDataChangedAfterLoadMore(vidiosModelList, true);
                            }
                        }
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        initSystemBarTint(false, R.color.white);
    }
    private void addOnLoadView() {
        View loadView =View.inflate(getContext(), R.layout.byb_comm_list_load_more, null);
        vidiosAdapter.setLoadingView(loadView);
    }
    @Override
    protected void onPullDownRefreshListener() {
        page = 1;
        refreshView();
    }

    @Override
    protected void onLoadMoreListener() {
        page++;
        refreshView();
    }
}
