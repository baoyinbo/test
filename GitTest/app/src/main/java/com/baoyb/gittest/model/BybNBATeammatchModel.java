package com.baoyb.gittest.model;

import java.io.Serializable;

/**
 * Created by baoyb on 2017/3/31.
 */

public class BybNBATeammatchModel implements Serializable {
    private String name;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
