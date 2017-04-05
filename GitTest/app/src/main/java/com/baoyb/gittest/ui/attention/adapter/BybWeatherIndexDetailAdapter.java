package com.baoyb.gittest.ui.attention.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baoyb.gittest.R;
import com.baoyb.gittest.model.BybWeatherIndexModel;

import java.util.List;

/**
 * Created by baoyb on 2017/4/1.
 */

public class BybWeatherIndexDetailAdapter extends PagerAdapter {
    Context mContext;
    List<BybWeatherIndexModel> models;
    public BybWeatherIndexDetailAdapter(Context context, List<BybWeatherIndexModel> models) {
        this.mContext = context;
        this.models = models;
    }

    @Override
    public int getCount() {
        return models == null ? 0 : models.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.byb_list_item_weather_index, null);
        ImageView ivIndex = (ImageView) view.findViewById(R.id.ivIndex);
        TextView tvTitleIndex = (TextView) view.findViewById(R.id.tvTitleIndex);
        TextView tvIndex = (TextView) view.findViewById(R.id.tvIndex);
        TextView tvDescIndex = (TextView) view.findViewById(R.id.tvDescIndex);

        tvTitleIndex.setText(models.get(position).getTipt());
        tvIndex.setText(models.get(position).getZs());
        tvDescIndex.setText(models.get(position).getDes());

        String type = models.get(position).getTitle();
        if ("穿衣".equals(type)) {
            ivIndex.setImageResource(R.mipmap.ic_todaycan_dress);
        } else if ("洗车".equals(type)){
            ivIndex.setImageResource(R.mipmap.ic_todaycan_carwash);
        } else if ("旅游".equals(type)){
            ivIndex.setImageResource(R.mipmap.ic_todaycan_tour);
        } else if ("感冒".equals(type)){
            ivIndex.setImageResource(R.mipmap.ic_todaycan_coldl);
        } else if ("运动".equals(type)){
            ivIndex.setImageResource(R.mipmap.ic_todaycan_sport);
        } else {
            ivIndex.setImageResource(R.mipmap.ic_todaycan_ultravioletrays);
        }

        container.addView(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onClickListener.click(position);
            }
        });

        return view;
    }

    @Override
    public float getPageWidth(int position) {
        return 0.7f;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    public interface OnClickListener{
        void click(int position);
    }

    /**点击 回调*/
    public OnClickListener onClickListener;

    public void setOnClickListener (OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
