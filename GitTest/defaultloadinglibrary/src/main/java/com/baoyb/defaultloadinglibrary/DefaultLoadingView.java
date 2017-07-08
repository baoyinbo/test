package com.baoyb.defaultloadinglibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * 自定义加载视图
 * 可以在多个视图切换，包括加载中，无网络，空白页，加载错误等视图
 * 并在异常情况下提供接口重新加载
 * Created by baoyinbo on 2017/6/12.
 */

public class DefaultLoadingView extends RelativeLayout {
    private static final int STATUS_CONTENT = 0x00;
    private static final int STATUS_LOADING = 0x01;
    private static final int STATUS_EMPTY = 0x02;
    private static final int STATUS_ERROR = 0x03;
    private static final int STATUS_NO_NETWORK = 0x04;

    private static final int NULL_RESOURCE_ID = -1;
    private int viewStatus;    //   当前视图

    /**
     * 各类视图
     */
    private View loadingView;
    private View noNetWorkView;
    private View emptyView;
    private View errorView;
    private View contentView;
    private View emptyRetryView;
    private View errorRetryView;
    private View noNetworkRetryView;

    /**
     * 视图id
     */
    private int loadingViewResId;
    private int noNetWorkViewResId;
    private int emptyViewResId;
    private int errorViewResId;
    private int contentViewResId;


    private LayoutInflater inflater;
    private ViewGroup.LayoutParams layoutParams;

    /**
     * 重新请求加载监听
     */
    private OnClickListener onRetryClickListener;

    public DefaultLoadingView(Context context) {
        super(context);
    }

    public DefaultLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DefaultLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MultipleStatusView);
        loadingViewResId = a.getResourceId(R.styleable.MultipleStatusView_loadingView, R.layout.byb_loading_view);
        noNetWorkViewResId = a.getResourceId(R.styleable.MultipleStatusView_noNetWorkView, R.layout.byb_no_network_view);
        emptyViewResId = a.getResourceId(R.styleable.MultipleStatusView_emptyView, R.layout.byb_empty_view);
        errorViewResId = a.getResourceId(R.styleable.MultipleStatusView_errorView, R.layout.byb_error_view);
        contentViewResId = a.getResourceId(R.styleable.MultipleStatusView_contentView, NULL_RESOURCE_ID);
        a.recycle();

        layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
    }

    /**
     * 当View中所有的子控件均被映射成xml后触发
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        inflater = LayoutInflater.from(getContext());
        showContent();  //显示内容视图
    }

    /**
     * 显示内容视图
     */
    public void showContent() {
        viewStatus = STATUS_CONTENT;
        if (null == contentView) {
            if (contentViewResId != NULL_RESOURCE_ID) {
                contentView = inflater.inflate(contentViewResId, null);
                addView(contentView, 0, layoutParams);
            } else {
                contentView = findViewById(R.id.contentViewResId);
            }
        }
        showViewByStatus(viewStatus);
    }

    /**
     * 显示加载中视图
     */
    public void showLoading() {
        viewStatus = STATUS_LOADING;
        if (null == loadingView) {
            if (loadingViewResId != NULL_RESOURCE_ID) {
                loadingView = inflater.inflate(loadingViewResId, null);
                addView(loadingView, 0, layoutParams);
            } else {
                loadingView = findViewById(R.id.loadingViewResId);
            }
        }
        showViewByStatus(viewStatus);
    }

    /**
     * 显示加载错误视图
     */
    public void showErrorView() {
        viewStatus = STATUS_ERROR;
        if (null == errorView) {
            if (errorViewResId != NULL_RESOURCE_ID) {
                errorView = inflater.inflate(errorViewResId, null);
                addView(errorView, 0, layoutParams);
            } else {
                errorView = findViewById(R.id.errorViewResId);
            }
            errorRetryView = errorView.findViewById(R.id.errorViewRetryId);
            if (null != onRetryClickListener && null != errorRetryView) {
                errorRetryView.setOnClickListener(onRetryClickListener);
            }
        }
        showViewByStatus(viewStatus);
    }

    /**
     * 显示空视图
     */
    public void showEmptyView() {
        viewStatus = STATUS_EMPTY;
        if (null == emptyView) {
            if (emptyViewResId != NULL_RESOURCE_ID) {
                emptyView = inflater.inflate(emptyViewResId, null);
                addView(emptyView, 0, layoutParams);
            } else {
                emptyView = findViewById(R.id.emptyViewResId);
            }

            emptyRetryView = emptyView.findViewById(R.id.emptyViewRetryId);
            if (null != onRetryClickListener && null != emptyRetryView) {
                emptyRetryView.setOnClickListener(onRetryClickListener);
            }
        }
        showViewByStatus(viewStatus);
    }

    /**
     * 显示无网络视图
     */
    public void showNoWifiView() {
        viewStatus = STATUS_NO_NETWORK;
        if (null == noNetWorkView) {
            if (noNetWorkViewResId != NULL_RESOURCE_ID) {
                noNetWorkView = inflater.inflate(noNetWorkViewResId, null);
                addView(noNetWorkView, 0, layoutParams);
            } else {
                noNetWorkView = findViewById(R.id.noNetWorkViewResId);
            }

            noNetworkRetryView = emptyView.findViewById(R.id.noNetWorkViewRetryId);
            if (null != onRetryClickListener && null != noNetworkRetryView) {
                noNetworkRetryView.setOnClickListener(onRetryClickListener);
            }
        }
        showViewByStatus(viewStatus);
    }


    /**
     * 切换视图
     * @param viewStatus
     */
    private void showViewByStatus(int viewStatus) {
        if (null != loadingView)
            loadingView.setVisibility(viewStatus == STATUS_LOADING ? VISIBLE : GONE);
        if (null != noNetWorkView)
            noNetWorkView.setVisibility(viewStatus == STATUS_NO_NETWORK ? VISIBLE : GONE);
        if (null != emptyView)
            emptyView.setVisibility(viewStatus == STATUS_EMPTY ? VISIBLE : GONE);
        if (null != errorView)
            errorView.setVisibility(viewStatus == STATUS_ERROR ? VISIBLE : GONE);
        if (null != contentView)
            contentView.setVisibility(viewStatus == STATUS_CONTENT ? VISIBLE : GONE);
    }

    /**
     * 设置重新加载事件
     * @param onRetryClickListener
     */
    public void setOnRetryClickListener(OnClickListener onRetryClickListener) {
        this.onRetryClickListener = onRetryClickListener;
    }
}
