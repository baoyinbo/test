package com.baoyb.gittest.model;

import java.io.Serializable;

/**
 * 请求响应基类
 * Created by baoyb on 2017/3/16.
 */

public class BaseResponseModel implements Serializable {
    private String error_code;  //0成功
    private String reason;      //返回说明（成功||失败原因）
}
