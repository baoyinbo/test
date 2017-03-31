package com.baoyb.gittest.ui.attention;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.baoyb.gittest.R;
import com.baoyb.gittest.model.BybMobileModel;
import com.baoyb.gittest.model.BybMobileSearchModel;
import com.baoyb.gittest.model.BybNBAModel;
import com.baoyb.gittest.model.BybNBATrModel;
import com.baoyb.gittest.net.BybApiManager;
import com.baoyb.gittest.net.BybRequestCallback;
import com.baoyb.gittest.ui.base.BaseActivityFragment;
import com.baoyb.gittest.util.LogUtils;
import com.baoyb.gittest.util.ToastShowUtils;

import okhttp3.Call;

/**
 * Created by baoyb on 2017/3/31.
 */

public class BybMobileSearchFragment extends BaseActivityFragment implements View.OnClickListener {
    private EditText etMobile;
    private Button btnMobileSearch;

    private TextView tvMobile;
    private TextView tvProvince;
    private TextView tvCompany;
    private View searchView;
    @Override
    public int getLayoutId() {
        return R.layout.byb_fra_mobile_search;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setTitle("号码查询");
        etMobile = (EditText) findViewById(R.id.etMobile);
        findViewById(R.id.btnMobileSearch).setOnClickListener(this);

        tvMobile = (TextView) findViewById(R.id.tvMobile);
        tvProvince = (TextView) findViewById(R.id.tvProvince);
        tvCompany = (TextView) findViewById(R.id.tvCompany);
        searchView = findViewById(R.id.llSearchContent);
        searchView.setVisibility(View.GONE);
    }


    /**
     *
     */
    private void searchMobile(final String mobile) {
        BybApiManager.getInstance().getMobileSearch(mobile,
                new BybRequestCallback(BybMobileSearchModel.class) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.e(e.toString());
                        searchView.setVisibility(View.GONE);
                        ToastShowUtils.showFailedToast("查询失败");
                    }

                    @Override
                    public void onResponse(Object response, int id) {
                        BybMobileSearchModel model = (BybMobileSearchModel)response;
                        searchView.setVisibility(View.VISIBLE);
                        tvMobile.setText(model.getTelString());
                        tvProvince.setText(model.getProvince());
                        tvCompany.setText(model.getCarrier());
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnMobileSearch:
                searchMobile(etMobile.getText().toString());
                break;
        }
    }
}
