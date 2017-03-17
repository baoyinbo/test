package com.baoyb.gittest.ui.main;

import android.os.Bundle;
import com.baoyb.gittest.R;
import com.baoyb.gittest.ui.base.BaseFragment;

/**
 * Created by baoyb on 2017/3/10.
 */
public class BybHomeFragment extends BaseFragment {

    @Override
    public int getLayoutId() {
        return R.layout.byb_fra_home;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        initSystemBarTint(false, R.color.background_red);
    }
}
