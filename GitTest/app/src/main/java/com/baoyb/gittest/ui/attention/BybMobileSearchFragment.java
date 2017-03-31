package com.baoyb.gittest.ui.attention;

import android.os.Bundle;

import com.baoyb.gittest.R;
import com.baoyb.gittest.ui.base.BaseActivityFragment;

/**
 * Created by baoyb on 2017/3/31.
 */

public class BybMobileSearchFragment extends BaseActivityFragment {
    @Override
    public int getLayoutId() {
        return R.layout.byb_fra_mobile_search;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setTitle("号码查询");
    }
}
