package com.baoyb.gittest.model;

import com.chad.library.adapter.base.entity.SectionEntity;

import java.util.List;

/**
 * Created by baoyb on 2017/3/31.
 */

public class BybNBAListModel extends SectionEntity {


    private String title; //12-07 周日
    private List<BybNBATrModel> tr;
    private List<BybNBABottomLinkModel> bottomlink;

    private List<BybNBALiveModel> live;

    public BybNBAListModel(boolean isHeader, String header) {
        super(isHeader, header);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<BybNBATrModel> getTr() {
        return tr;
    }

    public void setTr(List<BybNBATrModel> tr) {
        this.tr = tr;
    }

    public List<BybNBABottomLinkModel> getBottomlink() {
        return bottomlink;
    }

    public void setBottomlink(List<BybNBABottomLinkModel> bottomlink) {
        this.bottomlink = bottomlink;
    }

    public List<BybNBALiveModel> getLive() {
        return live;
    }

    public void setLive(List<BybNBALiveModel> live) {
        this.live = live;
    }
}
