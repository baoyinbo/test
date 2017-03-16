package com.baoyb.gittest.model;

import java.util.List;

/**
 * 首页推荐新闻model
 * Created by baoyb on 2017/3/16.
 */

public class BybHomeNewsModel extends BaseResponseModel {
    private List<BybHomeNewModel> result;

    public List<BybHomeNewModel> getResult() {
        return result;
    }

    public void setResult(List<BybHomeNewModel> result) {
        this.result = result;
    }
}
