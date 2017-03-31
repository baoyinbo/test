package com.baoyb.gittest.ui.nba;

import android.os.Bundle;
import com.baoyb.gittest.model.BybNBAModel;
import com.baoyb.gittest.model.BybNBATrModel;
import com.baoyb.gittest.net.BybApiManager;
import com.baoyb.gittest.net.BybRequestCallback;
import com.baoyb.gittest.ui.base.BaseListActivityFragment;
import com.baoyb.gittest.ui.base.CommomActivity;
import com.baoyb.gittest.ui.base.LaunchBody;
import com.baoyb.gittest.ui.common.BybWebViewFragment;
import com.baoyb.gittest.ui.nba.adapter.BybNbaListAdapter;
import com.baoyb.gittest.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by baoyb on 2017/3/31.
 */

public class BybNbaListFragment extends BaseListActivityFragment {

    private BybNbaListAdapter adapter;
    private List<BybNBATrModel> nbaTrModelList;

    @Override
    protected void onCreateListView(Bundle savedInstanceState) {
        setTitle("NBA");
        nbaTrModelList = new ArrayList<BybNBATrModel>();
        adapter = new BybNbaListAdapter(new ArrayList<BybNBATrModel>());
        setAdapter(adapter);
        adapter.setItemClickListener(new BybNbaListAdapter.ItemClickListener() {
            @Override
            public void itemClick(BybNBATrModel item) {
                Bundle bundle = new Bundle();
                bundle.putString("url", item.getLink2url());    //技术统计
                LaunchBody.Builder builder = new LaunchBody.Builder(BybNbaListFragment.this,
                        BybWebViewFragment.class);
                builder.bundle(bundle);
                builder.launchType(LaunchBody.LaunchType.SINGLE_TOP);
                CommomActivity.launch(builder);
            }
        });
        getLoadingView().loading();
        refreshView();
    }

    @Override
    protected void onPullDownRefreshListener() {
        refreshView();
    }

    @Override
    protected void onLoadMoreListener() {

    }

    private void refreshView() {
        BybApiManager.getInstance().getNba(
                new BybRequestCallback(BybNBAModel.class) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        getLoadingView().content();
                        completePullDownRefresh();
                        LogUtils.e(e.toString());
                    }

                    @Override
                    public void onResponse(Object response, int id) {
                        getLoadingView().content();
                        completePullDownRefresh();
                        BybNBAModel model = (BybNBAModel)response;
                        if (model != null && model.getResult() != null) {
                            for (int i = 0; i < model.getResult().getList().size(); i ++) {
                                BybNBATrModel header = new BybNBATrModel(true, model.getResult().getList().get(i).getTitle());
                                nbaTrModelList.add(header);
                                nbaTrModelList.addAll(model.getResult().getList().get(i).getTr());
                            }
                        }
                        adapter.setNewData(nbaTrModelList);
                    }
                });
    }
}
