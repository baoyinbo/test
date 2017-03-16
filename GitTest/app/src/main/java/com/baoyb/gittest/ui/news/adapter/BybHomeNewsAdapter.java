package com.baoyb.gittest.ui.news.adapter;

import com.baoyb.gittest.R;
import com.baoyb.gittest.model.BybHomeNewModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by baoyb on 2017/3/16.
 */

public class BybHomeNewsAdapter extends BaseQuickAdapter<BybHomeNewModel> {


    public BybHomeNewsAdapter(List<BybHomeNewModel> data) {
        super(R.layout.byb_list_item_homenews_no_image, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BybHomeNewModel item) {
        helper.setText(R.id.tvNewsTitle, item.getTitle());
        helper.setText(R.id.tvNewsSrc, item.getSrc());
        helper.setText(R.id.tvNewsCountComment, "100评论");
        helper.setText(R.id.tvNewsTime, item.getPdate());
    }


}
