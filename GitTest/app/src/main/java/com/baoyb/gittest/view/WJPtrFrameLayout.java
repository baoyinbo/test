package com.baoyb.gittest.view;

import android.content.Context;
import android.util.AttributeSet;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 作者：fz on 2016/10/21 13:53
 */
public class WJPtrFrameLayout extends PtrFrameLayout {
    private WJPtrClassicDefaultHeader mPtrClassicHeader;

    public WJPtrClassicDefaultHeader.MessageListener messageListener;

    public WJPtrFrameLayout(Context context) {
        super(context);
        initViews();
    }

    public WJPtrFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public WJPtrFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initViews();
    }

    private void initViews() {
        mPtrClassicHeader = new WJPtrClassicDefaultHeader(getContext());
        setHeaderView(mPtrClassicHeader);
        addPtrUIHandler(mPtrClassicHeader);


        setResistance(1.7f);
        setRatioOfHeaderHeightToRefresh(1.0f);
        setDurationToClose(200);
        setDurationToCloseHeader(800);
        setLoadingMinTime(1500);
        // default is false
        setPullToRefresh(false);
        // default is true
        setKeepHeaderWhenRefresh(true);

        // scroll then refresh
        // comment in base fragment
//        ptrFrame.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                // ptrFrame.autoRefresh();
//            }
//        }, 150);
    }
    
    public WJPtrClassicDefaultHeader getHeader() {
        return mPtrClassicHeader;
    }

    public void setOnMessageListener(WJPtrClassicDefaultHeader.MessageListener messageListener) {
        mPtrClassicHeader.setMessageListener(messageListener);
    }
}
