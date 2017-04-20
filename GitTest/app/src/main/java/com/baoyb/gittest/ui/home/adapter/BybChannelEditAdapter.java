package com.baoyb.gittest.ui.home.adapter;

import com.baoyb.gittest.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by baoyb on 2017/4/20.
 */

public class BybChannelEditAdapter extends BaseQuickAdapter<String> {

    public BybChannelEditAdapter(List<String> data) {
        super(R.layout.byb_list_item_channel_edit, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {

    }
}
