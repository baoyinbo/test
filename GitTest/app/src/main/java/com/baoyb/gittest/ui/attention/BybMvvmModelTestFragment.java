package com.baoyb.gittest.ui.attention;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.baoyb.gittest.R;
import com.baoyb.gittest.databinding.BybFraModelTestBinding;
import com.baoyb.gittest.model.BybMvvmTestModel;

/**
 * Created by baoyb on 2017/4/12.
 */

public class BybMvvmModelTestFragment extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BybFraModelTestBinding binding =
                DataBindingUtil.setContentView(this, R.layout.byb_fra_model_test);
        BybMvvmTestModel model = new BybMvvmTestModel("test", 5);
        binding.setModel(model);
    }
}
