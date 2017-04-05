package com.baoyb.gittest.model;

import java.io.Serializable;

/**
 * 具体天气指数
 * Created by baoyb on 2017/4/1.
 */

public class BybWeatherDataModel implements Serializable {
    private String date;       //周六 04月01日 (实时：12℃)
    private String dayPictureUrl;         //较冷
    private String nightPictureUrl;       //穿衣指数
    private String weather;         //多云转晴
    private String wind;            //北风微风;
    private String temperature;     //16 ~ 6℃

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDayPictureUrl() {
        return dayPictureUrl;
    }

    public void setDayPictureUrl(String dayPictureUrl) {
        this.dayPictureUrl = dayPictureUrl;
    }

    public String getNightPictureUrl() {
        return nightPictureUrl;
    }

    public void setNightPictureUrl(String nightPictureUrl) {
        this.nightPictureUrl = nightPictureUrl;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
