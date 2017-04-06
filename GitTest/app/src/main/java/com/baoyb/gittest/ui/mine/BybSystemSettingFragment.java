package com.baoyb.gittest.ui.mine;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.baoyb.gittest.R;
import com.baoyb.gittest.model.BybTestModel;
import com.baoyb.gittest.model.BybVidioModel;
import com.baoyb.gittest.model.BybVidiosModel;
import com.baoyb.gittest.net.BybApiManager;
import com.baoyb.gittest.net.BybRequestCallback;
import com.baoyb.gittest.ui.base.BaseActivityFragment;
import com.baoyb.gittest.ui.mine.adapter.BybTestAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

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
        getActionBar().hide();
//        setTitle("系统设置");
//        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
//        testList = new ArrayList<BybTestModel>();
//        adapter = new BybTestAdapter(new ArrayList<BybTestModel>());
//        adapter.openLoadMore(20, true);
//        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
//                page ++;
//                loadMore();
//            }
//        });
//
//        recyclerView.setAdapter(adapter);
//        addOnLoadView();
//        loadMore();


        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view,"FAB",Snackbar.LENGTH_LONG)
                        .setAction("cancel", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //这里的单击事件代表点击消除Action后的响应事件

                            }
                        })
                        .show();
            }
        });

    }
    private void loadMore() {
        List<BybTestModel> t = new ArrayList<BybTestModel>();
        for (int i = 0; i < 20; i ++) {
            BybTestModel model = new BybTestModel();
            model.setText("test: " + i + 10*page);
            t.add(model);
        }
        testList = t;
        if (page == 1) {
            adapter.setNewData(testList);
            adapter.openLoadMore(20, true);
        } else {
            Handler handler = new Handler();
            Runnable r = new Runnable() {
                public void run() {
                    adapter.notifyDataChangedAfterLoadMore(testList, true);
                }
            };
            handler.post(r);

        }

    }

    private void addOnLoadView() {
        View loadView =View.inflate(getContext(), R.layout.byb_comm_list_load_more, null);
        adapter.setLoadingView(loadView);
    }
}
