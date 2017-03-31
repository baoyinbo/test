package com.baoyb.gittest.ui.mine;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.baoyb.gittest.R;
import com.baoyb.gittest.model.BybTestModel;
import com.baoyb.gittest.ui.base.BaseActivityFragment;
import com.baoyb.gittest.ui.mine.adapter.BybTestAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baoyb on 2017/3/24.
 */

public class BybSystemSettingFragment extends BaseActivityFragment {
    private RecyclerView recyclerView;
    private BybTestAdapter adapter;
    private List<BybTestModel> testList;
    private int page = 1;
    @Override
    public int getLayoutId() {
        return R.layout.byb_fra_system_setting;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setTitle("系统设置");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        testList = new ArrayList<BybTestModel>();
        adapter = new BybTestAdapter(new ArrayList<BybTestModel>());
        adapter.openLoadMore(20, true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        });

        recyclerView.setAdapter(adapter);
        loadMore();
    }


    private void loadMore() {

        for (int i = 0; i < 20; i ++) {
            BybTestModel model = new BybTestModel();
            model.setText("test: " + i + 10*page);
            testList.add(model);
        }
        if (page == 1) {
            adapter.setNewData(testList);
            adapter.openLoadMore(20, true);
        } else {
            adapter.notifyDataChangedAfterLoadMore(testList, true);
        }

    }
}
