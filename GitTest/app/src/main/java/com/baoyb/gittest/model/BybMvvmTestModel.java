package com.baoyb.gittest.model;

/**
 * Created by baoyb on 2017/4/12.
 */

public class BybMvvmTestModel {
    private String type;
    private int index;

    public BybMvvmTestModel(String type, int index) {
        this.type = type;
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
