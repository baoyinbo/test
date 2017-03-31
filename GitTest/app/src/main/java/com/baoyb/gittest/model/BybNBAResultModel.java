package com.baoyb.gittest.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by baoyb on 2017/3/31.
 */

public class BybNBAResultModel implements Serializable {
    private String title; //NBA常规赛_腾讯体育
    private BybNBAStatusListModel statuslist;
    private List<BybNBAListModel> list;
    private List<BybNBATeammatchModel> teammatch;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BybNBAStatusListModel getStatuslist() {
        return statuslist;
    }

    public void setStatuslist(BybNBAStatusListModel statuslist) {
        this.statuslist = statuslist;
    }

    public List<BybNBAListModel> getList() {
        return list;
    }

    public void setList(List<BybNBAListModel> list) {
        this.list = list;
    }

    public List<BybNBATeammatchModel> getTeammatch() {
        return teammatch;
    }

    public void setTeammatch(List<BybNBATeammatchModel> teammatch) {
        this.teammatch = teammatch;
    }
}
