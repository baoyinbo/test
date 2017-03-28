package com.baoyb.gittest.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


import com.baoyb.gittest.R;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * 作者：fz on 2016/10/21 14:19
 */
public class WJPtrClassicDefaultHeader extends FrameLayout implements PtrUIHandler {

//    private final static String KEY_SharedPreferences = "wj_cube_ptr_classic_last_update";
    private int mRotateAniTime = 150;
    private TextView mTitleTextView;
    private ImageView mRotateView;
    private ImageView mProgressBar;

    private RotateAnimation mFlipAnimation;
    private RotateAnimation mReverseFlipAnimation;





    public WJPtrClassicDefaultHeader(Context context) {
        super(context);
        initViews(null);
    }

    public WJPtrClassicDefaultHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(attrs);
    }

    public WJPtrClassicDefaultHeader(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initViews(attrs);
    }

    protected void initViews(AttributeSet attrs) {
        TypedArray arr = getContext().obtainStyledAttributes(attrs, in.srain.cube.views.ptr.R.styleable.PtrClassicHeader, 0, 0);
        if (arr != null) {
            mRotateAniTime = arr.getInt(in.srain.cube.views.ptr.R.styleable.PtrClassicHeader_ptr_rotate_ani_time, mRotateAniTime);
        }
        buildAnimation();
        View header = LayoutInflater.from(getContext()).inflate(R.layout.wj_cube_ptr_classic_default_header, this);

        mRotateView = (ImageView)header.findViewById(in.srain.cube.views.ptr.R.id.ptr_classic_header_rotate_view);

        mTitleTextView = (TextView) header.findViewById(in.srain.cube.views.ptr.R.id.ptr_classic_header_rotate_view_header_title);

        mProgressBar = (ImageView)header.findViewById(in.srain.cube.views.ptr.R.id.ptr_classic_header_rotate_view_progressbar);
        mProgressBar.setImageResource(R.drawable.wj_pull_anim2);
        resetView();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

    }

    public void setRotateAniTime(int time) {
        if (time == mRotateAniTime || time == 0) {
            return;
        }
        mRotateAniTime = time;
        buildAnimation();
    }

    private void buildAnimation() {
        mFlipAnimation = new RotateAnimation(0, -180, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        mFlipAnimation.setInterpolator(new LinearInterpolator());
        mFlipAnimation.setDuration(mRotateAniTime);
        mFlipAnimation.setFillAfter(true);

        mReverseFlipAnimation = new RotateAnimation(-180, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        mReverseFlipAnimation.setInterpolator(new LinearInterpolator());
        mReverseFlipAnimation.setDuration(mRotateAniTime);
        mReverseFlipAnimation.setFillAfter(true);
    }

    private void resetView() {
        mRotateView.setImageResource(R.mipmap.anmi_01);
        hideRotateView();
        hideProgressBar();
    }

    private void hideRotateView() {
        mRotateView.clearAnimation();
        mRotateView.setVisibility(INVISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.clearAnimation();
        mProgressBar.setVisibility(INVISIBLE);
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
        resetView();


    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        mRotateView.setImageResource(R.mipmap.anmi_01);
        hideProgressBar();

        mRotateView.setVisibility(VISIBLE);
        mTitleTextView.setVisibility(VISIBLE);
        mTitleTextView.setText("下拉...        ");
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        mRotateView.setImageResource(R.drawable.wj_pull_anim1);
        mReverseFlipAnimation.setDuration(20);
        mRotateView.startAnimation(mReverseFlipAnimation);
        mRotateView.postDelayed(new Runnable() {
            public void run() {
                AnimationDrawable animationDrawable = (AnimationDrawable) mRotateView.getDrawable();
                animationDrawable.start();
                int duration = 0;

                for(int i=0;i<animationDrawable.getNumberOfFrames();i++){
                    duration += animationDrawable.getDuration(i);
                }

                mRotateView.postDelayed(new Runnable() {
                    public void run() {
                        hideRotateView();
                        mProgressBar.setVisibility(VISIBLE);
                        AnimationDrawable animationDrawable = (AnimationDrawable) mProgressBar.getDrawable();
                        animationDrawable.start();
                        mTitleTextView.setVisibility(VISIBLE);
                        mTitleTextView.setText("正在刷新...");
                    }
                }, duration);
            }
        }, 20);

    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
//        hideRotateView();
//        hideProgressBar();
//        mRotateView.setVisibility(GONE);
//        mProgressBar.setVisibility(GONE);
//        mTitleTextView.setVisibility(VISIBLE);
//        mTitleTextView.setText(getResources().getString(in.srain.cube.views.ptr.R.string.cube_ptr_refresh_complete));
        frame.setEnabledNextPtrAtOnce(true);
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {

        final int mOffsetToRefresh = frame.getOffsetToRefresh();
        final int currentPos = ptrIndicator.getCurrentPosY();
        final int lastPos = ptrIndicator.getLastPosY();

        if (messageListener != null) {
            if (currentPos > 0) {
                messageListener.hideMessage();
            } else {
                messageListener.showMessage();
            }
        }

        if (currentPos < mOffsetToRefresh && lastPos >= mOffsetToRefresh) {
            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
                crossRotateLineFromBottomUnderTouch(frame);
                if (mRotateView != null) {
                    mRotateView.clearAnimation();
                    mReverseFlipAnimation.setDuration(mRotateAniTime);
                    mRotateView.startAnimation(mReverseFlipAnimation);
                }

            }
        } else if (currentPos > mOffsetToRefresh && lastPos <= mOffsetToRefresh) {
            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
                crossRotateLineFromTopUnderTouch(frame);
                if (mRotateView != null) {
                    mRotateView.clearAnimation();
                    mRotateView.startAnimation(mFlipAnimation);
                }
            }
        }
    }

    private void crossRotateLineFromTopUnderTouch(PtrFrameLayout frame) {
        if (!frame.isPullToRefresh()) {
            mTitleTextView.setVisibility(VISIBLE);
            mTitleTextView.setText("释放刷新...");
        }
    }

    private void crossRotateLineFromBottomUnderTouch(PtrFrameLayout frame) {
        mTitleTextView.setVisibility(VISIBLE);
        mTitleTextView.setText("下拉...        ");
    }


    public interface MessageListener{
        void showMessage();
        void hideMessage();
    }

    /**回调 消息入口显示隐藏*/
    public MessageListener messageListener;

    public void setMessageListener (MessageListener messageListener) {
        this.messageListener = messageListener;
    }
}
