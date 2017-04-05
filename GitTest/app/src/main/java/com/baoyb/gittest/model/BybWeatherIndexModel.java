package com.baoyb.gittest.model;

import java.io.Serializable;

/**
 * 天气指数
 * Created by baoyb on 2017/4/1.
 */

public class BybWeatherIndexModel implements Serializable {
    private String title;       //穿衣
    private String zs;         //较冷
    private String tipt;       //穿衣指数
    private String des;         //建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getZs() {
        return zs;
    }

    public void setZs(String zs) {
        this.zs = zs;
    }

    public String getTipt() {
        return tipt;
    }

    public void setTipt(String tipt) {
        this.tipt = tipt;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
