package com.baoyb.gittest.ui.mine.adapter;

import com.baoyb.gittest.R;
import com.baoyb.gittest.model.BybTestModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by baoyb on 2017/3/30.
 */

public class BybTestAdapter extends BaseQuickAdapter<BybTestModel> {


    public BybTestAdapter(List<BybTestModel> data) {
        super(R.layout.byb_list_item_test, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, BybTestModel bybTestModel) {
        baseViewHolder.setText(R.id.tvTest, bybTestModel.getText());
    }
}
