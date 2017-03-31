package com.baoyb.gittest.model;

import java.io.Serializable;

/**
 * Created by baoyb on 2017/3/30.
 */

public class BybTestModel implements Serializable{
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
