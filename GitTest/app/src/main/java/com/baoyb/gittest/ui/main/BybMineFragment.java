package com.baoyb.gittest.ui.main;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyb.gittest.R;
import com.baoyb.gittest.ui.base.BaseFragment;
import com.baoyb.gittest.ui.base.CommomActivity;
import com.baoyb.gittest.ui.base.LaunchBody;
import com.baoyb.gittest.ui.mine.BybSystemSettingFragment;
import com.baoyb.gittest.util.ToastShowUtils;
import com.baoyb.gittest.view.WJPtrFrameLayout;

import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by baoyb on 2017/3/10.
 */

public class BybMineFragment extends BaseFragment implements View.OnClickListener {
    private WJPtrFrameLayout ptrFrameLayout;
    private boolean isDayStyle = true;  //日间模式
    private TextView tvStyle;
    @Override
    public int getLayoutId() {
        return R.layout.byb_fra_mine;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ptrFrameLayout = (WJPtrFrameLayout) findViewById(R.id.refreshView);
        initPtrFrameLayout();
        ptrFrameLayout.setPullToRefresh(true);
        findViewById(R.id.ivLoginByPhone).setOnClickListener(this);
        findViewById(R.id.ivLoginByQQ).setOnClickListener(this);
        findViewById(R.id.ivLoginByWX).setOnClickListener(this);
        findViewById(R.id.llCollection).setOnClickListener(this);
        findViewById(R.id.llHistory).setOnClickListener(this);
        findViewById(R.id.llStyle).setOnClickListener(this);
        findViewById(R.id.rlMessageSetting).setOnClickListener(this);
        findViewById(R.id.rlShop).setOnClickListener(this);
        findViewById(R.id.rlJD).setOnClickListener(this);
        findViewById(R.id.rlReport).setOnClickListener(this);
        findViewById(R.id.rlFeedback).setOnClickListener(this);
        findViewById(R.id.rlSystemSetting).setOnClickListener(this);

        tvStyle = (TextView) findViewById(R.id.tvStyle);
    }

    private void initPtrFrameLayout() {
        ptrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                onPullDownRefreshListener();
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
    }

    private void onPullDownRefreshListener() {

    }

    private void completePullDownRefresh() {
        ptrFrameLayout.refreshComplete();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLoginByPhone:
                ToastShowUtils.showSuccessToast("账号登录");
                break;
            case R.id.ivLoginByQQ:
                ToastShowUtils.showFailedToast("QQ登录");
                break;
            case R.id.ivLoginByWX:
                ToastShowUtils.showSuccessToast("微信登录");
                break;
            case R.id.llCollection:
                break;
            case R.id.llHistory:
                break;
            case R.id.llStyle:
                if (isDayStyle) {
                    ((AppCompatActivity)this.getActivity()).getDelegate()
                            .setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    tvStyle.setText(R.string.mine_style_night);
                } else {
                    ((AppCompatActivity)this.getActivity()).getDelegate()
                            .setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    tvStyle.setText(R.string.mine_style_day);
                }
                isDayStyle = !isDayStyle;
                this.getActivity().recreate();
                break;
            case R.id.rlSystemSetting:
                LaunchBody.Builder builder = new LaunchBody.Builder(BybMineFragment.this, BybSystemSettingFragment.class);
                builder.launchType(LaunchBody.LaunchType.SINGLE_TOP);
                CommomActivity.launch(builder);

        }
    }
}
