package com.baoyb.gittest.model;


import java.io.Serializable;

/**
 * Created by baoyb on 2017/3/31.
 */

public class BybNBAModel implements Serializable{


    private String reason; //查询成功
    private BybNBAResultModel result;
    private String error_code;
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public BybNBAResultModel getResult() {
        return result;
    }

    public void setResult(BybNBAResultModel result) {
        this.result = result;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }
}
