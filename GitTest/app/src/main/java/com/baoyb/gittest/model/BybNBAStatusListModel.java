package com.baoyb.gittest.model;

import java.io.Serializable;

/**
 * Created by baoyb on 2017/3/31.
 */

public class BybNBAStatusListModel implements Serializable {
    private String st0; //未开赛
    private String st1; //直播中
    private String st2; //已结束

    public String getSt0() {
        return st0;
    }

    public void setSt0(String st0) {
        this.st0 = st0;
    }

    public String getSt1() {
        return st1;
    }

    public void setSt1(String st1) {
        this.st1 = st1;
    }

    public String getSt2() {
        return st2;
    }

    public void setSt2(String st2) {
        this.st2 = st2;
    }
}
