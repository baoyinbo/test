package com.baoyb.gittest.ui.attention.adapter;

import com.baoyb.gittest.R;
import com.baoyb.gittest.model.BybAttentionModel;
import com.baoyb.gittest.model.BybWeatherIndexModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by baoyb on 2017/3/31.
 */

public class BybWeatherIndexAdapter extends BaseQuickAdapter<BybWeatherIndexModel> {

    public BybWeatherIndexAdapter(List<BybWeatherIndexModel> data) {
        super(R.layout.byb_list_item_weather, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, BybWeatherIndexModel item) {
        String type = item.getTitle();
        baseViewHolder.setText(R.id.tvTitleIndex, type);
        baseViewHolder.setText(R.id.tvIndex, item.getZs());

        if ("穿衣".equals(type)) {
            baseViewHolder.setImageResource(R.id.ivIndex, R.mipmap.ic_todaycan_dress);
        } else if ("洗车".equals(type)){
            baseViewHolder.setImageResource(R.id.ivIndex, R.mipmap.ic_todaycan_carwash);
        } else if ("旅游".equals(type)){
            baseViewHolder.setImageResource(R.id.ivIndex, R.mipmap.ic_todaycan_tour);
        } else if ("感冒".equals(type)){
            baseViewHolder.setImageResource(R.id.ivIndex, R.mipmap.ic_todaycan_coldl);
        } else if ("运动".equals(type)){
            baseViewHolder.setImageResource(R.id.ivIndex, R.mipmap.ic_todaycan_sport);
        } else {
            baseViewHolder.setImageResource(R.id.ivIndex, R.mipmap.ic_todaycan_ultravioletrays);
        }
    }
}
