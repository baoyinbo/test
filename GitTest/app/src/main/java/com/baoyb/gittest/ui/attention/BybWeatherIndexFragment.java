package com.baoyb.gittest.ui.attention;

import android.os.Bundle;
import com.baoyb.gittest.R;
import com.baoyb.gittest.model.BybWeatherIndexModel;
import com.baoyb.gittest.ui.attention.adapter.BybWeatherIndexDetailAdapter;
import com.baoyb.gittest.ui.base.BaseActivityFragment;
import com.baoyb.gittest.view.centerview.CenterViewPager;
import com.baoyb.gittest.view.centerview.ZoomOutPageTransformer;

import java.util.List;

/**
 * Created by baoyb on 2017/4/1.
 */

public class BybWeatherIndexFragment extends BaseActivityFragment {
    private CenterViewPager centerViewPager;
    private BybWeatherIndexDetailAdapter indexDetailAdapter;
    private List<BybWeatherIndexModel> models;
    private int position = 0;
    @Override
    public int getLayoutId() {
        return R.layout.byb_fra_weather_index;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setTitle("生活指数");
        getData();

        centerViewPager = (CenterViewPager)findViewById(R.id.centerViewPager);
        indexDetailAdapter = new BybWeatherIndexDetailAdapter(getContext(), models);
        centerViewPager.setAdapter(indexDetailAdapter);
        centerViewPager.enableCenterLockOfChilds();
        centerViewPager.setPageMargin(30);
        centerViewPager.setPageTransformer(true, new ZoomOutPageTransformer());

        centerViewPager.setCurrentItemInCenter(position);
        //滑动返回
        getCurrentPage().setSwipeEdgePercent(0.05f);

    }

    private void getData() {
        if (getArguments()!= null) {
            models = (List<BybWeatherIndexModel>) getArguments().get("index");
            position = getArguments().getInt("position", 0);
        }
    }
}
