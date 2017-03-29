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

    }


    @Override
    protected void onPullDownRefreshListener() {

    }

    @Override
    protected void onLoadMoreListener() {

    }
}
