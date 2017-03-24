package com.baoyb.gittest.ui.home;

import android.os.Bundle;
import android.widget.TextView;

import com.baoyb.gittest.R;
import com.baoyb.gittest.ui.base.BaseFragment;

/**
 * Created by baoyinbo on 2017/3/17.
 */

public class BybTabFragment extends BaseFragment{
    private TextView tv;
    private static String CHANNEL;

    public static BybTabFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString("channel", title);
        BybTabFragment fragment = new BybTabFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.byb_fra_home_table;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        getData();
        tv = (TextView) findViewById(R.id.tv);
        tv.setText(CHANNEL);
    }

    private void getData() {
        if (getArguments() != null) {
            CHANNEL = getArguments().getString("channel");
        }
    }

}
