package com.baoyb.gittest.ui.attention.adapter;

import com.baoyb.gittest.R;
import com.baoyb.gittest.model.BybAttentionModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by baoyb on 2017/3/31.
 */

public class BybAttentionAdapter extends BaseQuickAdapter<BybAttentionModel> {
    public BybAttentionAdapter(List<BybAttentionModel> data) {
        super(R.layout.byb_list_item_attention, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, BybAttentionModel item) {
        baseViewHolder.setText(R.id.tvTitle, item.getTitle());
        baseViewHolder.setText(R.id.tvDesc, item.getDesc());
    }
}
