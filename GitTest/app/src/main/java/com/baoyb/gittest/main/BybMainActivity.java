package com.baoyb.gittest.main;

import android.os.Bundle;
import com.baoyb.gittest.R;
import com.baoyb.gittest.base.BaseActivity;

/**
 * Created by baoyb on 2017/3/9.
 */

public class BybMainActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void doCreate(Bundle savedInstanceState) {
        setSwipeBackEnable(false);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {

    }

}
