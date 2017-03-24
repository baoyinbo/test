package com.baoyb.gittest.ui.base;


/**
 * Created by baoyb on 2017/3/9.
 */

public abstract class BaseFragment extends LazyFragment {

    /**
     * finish掉当前activity
     */
    public void finishActivity() {
        if (getActivity() != null && !getActivity().isFinishing()) {
            getActivity().finish();
        }
    }
}
