package com.baoyb.gittest.ui.attention;

import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.baoyb.gittest.R;
import com.baoyb.gittest.model.BybVidioModel;
import com.baoyb.gittest.model.BybVidiosModel;
import com.baoyb.gittest.net.BybApiManager;
import com.baoyb.gittest.net.BybRequestCallback;
import com.baoyb.gittest.ui.base.BaseActivityFragment;
import com.baoyb.gittest.ui.vidio.adapter.BybVidiosAdapter;
import com.jude.easyrecyclerview.EasyRecyclerView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 瀑布流 EasyRecyclerView
 * Created by baoyb on 2017/4/11.
 */

public class BybStaggeredGridLayoutFragment extends BaseActivityFragment {
    private EasyRecyclerView recyclerView;
    private BybVidiosAdapter vidiosAdapter;
    private List<BybVidiosModel> vidiosModelList;

    private int page = 1;
    @Override
    public int getLayoutId() {
        return R.layout.byb_fra_staggered_grid_layout;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        recyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);
        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        vidiosAdapter = new BybVidiosAdapter(this.getContext(), new ArrayList<BybVidiosModel>());
        recyclerView.setAdapterWithProgress(vidiosAdapter);
        refreshView();
    }

    private void refreshView() {
        BybApiManager.getInstance().getVidioList(page + "",
                new BybRequestCallback(BybVidioModel.class) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
//                        completePullDownRefresh();
                    }

                    @Override
                    public void onResponse(Object response, int id) {
//                        completePullDownRefresh();
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
}
