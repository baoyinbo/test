package com.baoyb.gittest.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by baoyb on 2017/4/1.
 */

public class BybWeatherResultModel implements Serializable {
    private String currentCity; //杭州
    private String pm25;        //119
    private List<BybWeatherIndexModel> index;
    private List<BybWeatherDataModel> weather_data;

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public List<BybWeatherIndexModel> getIndex() {
        return index;
    }

    public void setIndex(List<BybWeatherIndexModel> index) {
        this.index = index;
    }

    public List<BybWeatherDataModel> getWeather_data() {
        return weather_data;
    }

    public void setWeather_data(List<BybWeatherDataModel> weather_data) {
        this.weather_data = weather_data;
    }
}
