package com.baoyb.gittest.model;

import java.io.Serializable;

/**
 * Created by baoyb on 2017/3/31.
 */

public class BybAttentionModel implements Serializable {
    private String title;
    private String desc;
    private String imgUrl;

    private Class clazz;

    public BybAttentionModel(String title, String desc, String imgUrl, Class clazz) {
        this.title = title;
        this.desc = desc;
        this.imgUrl = imgUrl;
        this.clazz = clazz;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
