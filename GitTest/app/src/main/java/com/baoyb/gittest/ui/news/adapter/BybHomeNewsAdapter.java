package com.baoyb.gittest.ui.news.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.baoyb.gittest.R;
import com.baoyb.gittest.model.BybHomeNewModel;
import com.baoyb.gittest.model.BybHomeNewsModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by baoyb on 2017/3/16.
 */

public class BybHomeNewsAdapter extends BaseQuickAdapter<BybHomeNewsModel> {
    public static final int TMPELATE_NO_PIC = 1;//没有照片
    public static final int TMPELATE_ONE_BIG_PIC = 3;//一张大图
    public static final int TMPELATE_THREE_SMALL_PIC = 4;//三张小图
    public static final int TMPELATE_ONE_SAMLL_PIC = 5;//一张小图

    private Context context;

    public BybHomeNewsAdapter(Context context, List<BybHomeNewsModel> data) {
        super(R.layout.byb_list_item_homenews_one_image, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, BybHomeNewsModel item) {
        helper.setText(R.id.tvNewsTitle, item.getTitle());
        helper.setText(R.id.tvNewsSrc, item.getSource_name());
        helper.setText(R.id.tvNewsCountComment, "100评论");
        helper.setText(R.id.tvNewsTime, item.getCreate_time());
        String templateType = item.getTemplate_id();
        if (templateType.equals("1")) {
            helper.setVisible(R.id.ivOneSmallImg, false);
            helper.setVisible(R.id.llImg, false);
        } else if (templateType.equals("3")) {
            helper.setVisible(R.id.ivOneSmallImg, false);
            helper.setVisible(R.id.llImg, true);
            helper.setVisible(R.id.llThreeImg, false);
            helper.setVisible(R.id.ivBigImg, true);
            ImageView ivBigImg = helper.getView(R.id.ivBigImg);
            Glide.with(context).load(item.getImg().get(0).getUrl()).asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.RESULT).into(ivBigImg);
        } else if (templateType.equals("4")) {
            helper.setVisible(R.id.ivOneSmallImg, false);
            helper.setVisible(R.id.llImg, true);
            helper.setVisible(R.id.llThreeImg, true);
            helper.setVisible(R.id.ivBigImg, false);
            ImageView ivSmallImg1 = helper.getView(R.id.ivThreeSmallImg1);
            ImageView ivSmallImg2 = helper.getView(R.id.ivThreeSmallImg3);
            ImageView ivSmallImg3 = helper.getView(R.id.ivThreeSmallImg2);
            Glide.with(context).load(item.getImg().get(0).getUrl()).asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.RESULT).into(ivSmallImg1);
            Glide.with(context).load(item.getImg().get(1).getUrl()).asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.RESULT).into(ivSmallImg2);
            Glide.with(context).load(item.getImg().get(2).getUrl()).asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.RESULT).into(ivSmallImg3);
        } else if (templateType.equals("5")) {
            helper.setVisible(R.id.ivOneSmallImg, true);
            helper.setVisible(R.id.llImg, false);
            ImageView ivSmallImg = helper.getView(R.id.ivOneSmallImg);
            Glide.with(context).load(item.getImg().get(0).getUrl()).asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.RESULT).into(ivSmallImg);
        }
    }


}
