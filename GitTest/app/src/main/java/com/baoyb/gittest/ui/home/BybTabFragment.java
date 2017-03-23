package com.baoyb.gittest.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

import com.baoyb.gittest.ui.base.BaseListFragment;

/**
 * Created by baoyinbo on 2017/3/17.
 */

public class BybTabFragment extends Fragment{
    private static String CHANNEL;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView tv = new TextView(getActivity());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(-1, -1);
        tv.setLayoutParams(params);
        tv.setGravity(Gravity.CENTER);
        tv.setText(CHANNEL);
        return tv;
    }


    private void getData() {
        if (getArguments() != null) {
            CHANNEL = getArguments().getString("channel");
        }
    }

}
