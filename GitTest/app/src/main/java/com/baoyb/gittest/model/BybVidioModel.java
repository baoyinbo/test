package com.baoyb.gittest.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by baoyb on 2017/3/30.
 */

public class BybVidioModel implements Serializable {
    private String error;
    private List<BybVidiosModel> results;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<BybVidiosModel> getResults() {
        return results;
    }

    public void setResults(List<BybVidiosModel> results) {
        this.results = results;
    }
}
