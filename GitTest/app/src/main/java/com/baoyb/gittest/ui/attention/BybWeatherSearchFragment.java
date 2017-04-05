package com.baoyb.gittest.ui.attention;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baoyb.gittest.R;
import com.baoyb.gittest.model.BybWeatherDataModel;
import com.baoyb.gittest.model.BybWeatherIndexModel;
import com.baoyb.gittest.model.BybWeatherModel;
import com.baoyb.gittest.model.BybWeatherResultModel;
import com.baoyb.gittest.net.BybApiManager;
import com.baoyb.gittest.net.BybRequestCallback;
import com.baoyb.gittest.ui.attention.adapter.BybWeatherIndexAdapter;
import com.baoyb.gittest.ui.base.BaseActivityFragment;
import com.baoyb.gittest.ui.base.CommomActivity;
import com.baoyb.gittest.ui.base.LaunchBody;
import com.baoyb.gittest.ui.common.BybDefaultLoadingView;
import com.baoyb.gittest.util.LogUtils;
import com.baoyb.gittest.util.ToastShowUtils;
import com.baoyb.gittest.util.WeatherPmUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by baoyb on 2017/4/1.
 */

public class BybWeatherSearchFragment extends BaseActivityFragment implements View.OnClickListener {
    private BybDefaultLoadingView loadingView;
    private Button btnLocation;
    private View llFutureWeather; //未来5天天气
    private TextView tvPm;  //
    private TextView tvTemperatureScrope;   //温度范围
    private TextView tvNowWeather;  //实时天气
    private TextView tvWeatherAbout;    //

    private BybWeatherModel weatherModel;
    private List<BybWeatherIndexModel> indexModels; //生活指数
    private BybWeatherResultModel weatherResultModel;
    private List<BybWeatherDataModel> futureWeatherModels;  //未来天气
    private BybWeatherDataModel todayWeatherModel;  //今天天气

    private RecyclerView recyclerView;
    private BybWeatherIndexAdapter indexAdapter;
    @Override
    public int getLayoutId() {
        return R.layout.byb_fra_weather_search;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setTitle("天气情况");
        loadingView = (BybDefaultLoadingView) findViewById(R.id.loadingView);
        btnLocation = (Button) findViewById(R.id.btnLocation);
        btnLocation.setOnClickListener(this);
        llFutureWeather = findViewById(R.id.llFutureWeather);
        llFutureWeather.setOnClickListener(this);
        tvPm = (TextView) findViewById(R.id.tvPm);
        tvTemperatureScrope = (TextView) findViewById(R.id.tvTemperatureScrope);
        tvNowWeather = (TextView) findViewById(R.id.tvNowWeather);
        tvWeatherAbout = (TextView) findViewById(R.id.tvWeatherAbout);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        GridLayoutManager mgr=new GridLayoutManager(BybWeatherSearchFragment.this.getContext(), 3);
        recyclerView.setLayoutManager(mgr);
        indexAdapter = new BybWeatherIndexAdapter(new ArrayList<BybWeatherIndexModel>());
        recyclerView.setAdapter(indexAdapter);

        indexAdapter.setOnRecyclerViewItemClickListener(
                new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("index", (Serializable) indexModels);
                bundle.putInt("position", i);
                LaunchBody.Builder builder = new LaunchBody.Builder(BybWeatherSearchFragment.this,
                        BybWeatherIndexFragment.class);
                builder.bundle(bundle);
                builder.launchType(LaunchBody.LaunchType.SINGLE_TOP);
                CommomActivity.launch(builder);
            }
        });

        loadingView.loading();
        searchWeather("杭州");
    }

    /**
     * 初始化数据
     */
    private void initData() {
        String pm = weatherResultModel.getPm25();
        String pmResult = WeatherPmUtils.getPmQuality(pm);
        if ("0".equals(pmResult)) {
            tvPm.setText(pm + " 优");
            tvPm.setBackgroundResource(R.drawable.byb_corners_weather_blue);
        } else if ("1".equals(pmResult)) {
            tvPm.setText(pm + " 良");
            tvPm.setBackgroundResource(R.drawable.byb_corners_weather_yellow);
        } else if ("2".equals(pmResult)) {
            tvPm.setText(pm + " 轻度");
            tvPm.setBackgroundResource(R.drawable.byb_corners_weather_orange);
        } else {
            tvPm.setText(pm + " 重度");
            tvPm.setBackgroundResource(R.drawable.byb_corners_weather_red);
        }

        tvNowWeather.setText(todayWeatherModel.getDate());
        tvTemperatureScrope.setText(todayWeatherModel.getTemperature());
        tvWeatherAbout.setText(todayWeatherModel.getWeather() + "  " + todayWeatherModel.getWind());

        indexAdapter.setNewData(indexModels);
    }

    private void searchWeather(final String location) {
        BybApiManager.getInstance().getWeatherSearch(location,
                new BybRequestCallback(BybWeatherModel.class) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.e(e.toString());
                        ToastShowUtils.showFailedToast("查询失败");
//                        loadingView.error();
                    }

                    @Override
                    public void onResponse(Object response, int id) {
                        weatherModel = (BybWeatherModel) response;
                        weatherResultModel = weatherModel.getResults().get(0);
                        indexModels = weatherResultModel.getIndex();
                        futureWeatherModels = weatherResultModel.getWeather_data();
                        todayWeatherModel = futureWeatherModels.get(0);
                        initData();
                        loadingView.content();
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLocation:
                break;
            case R.id.llFutureWeather:
                break;

        }
    }
}
