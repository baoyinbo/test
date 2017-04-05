package com.baoyb.gittest.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by baoyb on 2017/4/1.
 */

public class BybWeatherModel implements Serializable {
    private String error;   //0：对的
    private String status;  //success
    private String date;    //2017-04-01
    private List<BybWeatherResultModel> results;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<BybWeatherResultModel> getResults() {
        return results;
    }

    public void setResults(List<BybWeatherResultModel> results) {
        this.results = results;
    }
}
