package com.baoyb.gittest.ui.nba.adapter;

import android.view.View;
import android.widget.ImageView;

import com.baoyb.gittest.R;
import com.baoyb.gittest.model.BybNBAListModel;
import com.baoyb.gittest.model.BybNBAModel;
import com.baoyb.gittest.model.BybNBATrModel;
import com.baoyb.gittest.util.GlideUtil;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


/**
 * Created by baoyb on 2017/3/31.
 */

public class BybNbaListAdapter extends BaseSectionQuickAdapter<BybNBATrModel> {
    public BybNbaListAdapter(List<BybNBATrModel> data) {
        super(R.layout.byb_list_item_nba, R.layout.byb_list_item_nba_head, data);
    }

    @Override
    protected void convertHead(BaseViewHolder baseViewHolder, BybNBATrModel item) {
        baseViewHolder.setText(R.id.tvTime, item.header);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final BybNBATrModel item) {
        baseViewHolder.setText(R.id.tvHomeTeam, item.getPlayer1());
        baseViewHolder.setText(R.id.tvCustomerTeam, item.getPlayer2());
        baseViewHolder.setText(R.id.tvScore, item.getScore());
        if ("0".equals(item.getStatus())) {
            baseViewHolder.setText(R.id.tvStatus, "未开始 " + item.getTime().split(" ")[1]); //格式：03/30 07:30
        } else if ("1".equals(item.getStatus())) {
            baseViewHolder.setText(R.id.tvStatus, "正在直播");
        } else {
            baseViewHolder.setText(R.id.tvStatus, "已结束");
        }
        GlideUtil.loadRoundImg(item.getPlayer1logobig(), (ImageView) baseViewHolder.getView(R.id.ivHomeLogo));
        GlideUtil.loadRoundImg(item.getPlayer2logobig(), (ImageView) baseViewHolder.getView(R.id.ivCustomeLogo));
        View llItem = baseViewHolder.getView(R.id.llItem);
        llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.itemClick(item);
            }
        });
    }

    public interface ItemClickListener{
        void itemClick(BybNBATrModel tem);
    }

    /**回调 item点击*/
    public ItemClickListener itemClickListener;

    public void setItemClickListener (ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
