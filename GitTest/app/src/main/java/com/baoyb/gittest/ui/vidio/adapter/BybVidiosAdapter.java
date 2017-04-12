package com.baoyb.gittest.ui.vidio.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.baoyb.gittest.R;
import com.baoyb.gittest.model.BybVidiosModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by baoyb on 2017/3/16.
 */

public class BybVidiosAdapter extends BaseQuickAdapter<BybVidiosModel> {
    private Context context;

    public BybVidiosAdapter(Context context, List<BybVidiosModel> data) {
        super(R.layout.byb_list_item_vidio, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, BybVidiosModel item) {
//        helper.setText(R.id.tvNewsSrc, item.getWho());
//        helper.setText(R.id.tvNewsCountComment, "100评论");
//        helper.setText(R.id.tvNewsTime, item.getCreatedAt());
        Glide.with(context).load(item.getUrl()).asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into((ImageView) helper.getView(R.id.ivVidio));
    }


}
