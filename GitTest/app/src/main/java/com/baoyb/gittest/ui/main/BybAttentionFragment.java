package com.baoyb.gittest.ui.main;

import android.os.Bundle;
import android.view.View;

import com.baoyb.gittest.R;
import com.baoyb.gittest.model.BybAttentionModel;
import com.baoyb.gittest.ui.attention.BybMobileSearchFragment;
import com.baoyb.gittest.ui.attention.BybWeatherSearchFragment;
import com.baoyb.gittest.ui.attention.adapter.BybAttentionAdapter;
import com.baoyb.gittest.ui.base.BaseListFragment;
import com.baoyb.gittest.ui.base.CommomActivity;
import com.baoyb.gittest.ui.base.LaunchBody;
import com.baoyb.gittest.ui.nba.BybNbaListFragment;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by baoyb on 2017/3/10.
 */

public class BybAttentionFragment extends BaseListFragment {

    private BybAttentionAdapter adapter;
    private List<BybAttentionModel> attentionList;

    @Override
    public int getLayoutId() {
        return R.layout.byb_fra_attention;
    }

    @Override
    protected void onCreateListView(Bundle savedInstanceState) {
        attentionList = new ArrayList<BybAttentionModel>();

        adapter = new BybAttentionAdapter(new ArrayList<BybAttentionModel>());
        setAdapter(adapter);
        adapter.setOnRecyclerViewItemClickListener(
                new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                LaunchBody.Builder builder = new LaunchBody.Builder(BybAttentionFragment.this,
                        attentionList.get(i).getClazz());
                builder.launchType(LaunchBody.LaunchType.SINGLE_TOP);
                CommomActivity.launch(builder);
            }
        });
        test();
        getSwipeRefreshLayout().setEnabled(false);
    }

    @Override
    protected void onPullDownRefreshListener() {
    }

    @Override
    protected void onLoadMoreListener() {

    }

    private void test() {
        BybAttentionModel model1 =
                new BybAttentionModel("NBA", "让我们一起关注nba赛事吧", "", BybNbaListFragment.class);
        attentionList.add(model1);
        BybAttentionModel model2 =
                new BybAttentionModel("手机号查询", "让我看看这个手机号是哪里的", "", BybMobileSearchFragment.class);
        attentionList.add(model2);
        BybAttentionModel model3 =
                new BybAttentionModel("天气查询", "提前知天气，出门有准备", "", BybWeatherSearchFragment.class);
        attentionList.add(model3);

        adapter.setNewData(attentionList);
    }
}
