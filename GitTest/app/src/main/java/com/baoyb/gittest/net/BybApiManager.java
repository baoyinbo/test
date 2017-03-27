package com.baoyb.gittest.net;

import java.util.HashMap;
import java.util.Map;

/**
 * api 管理
 * Created by baoyb on 2017/3/24.
 */

public class BybApiManager {
    private static BybApiManager apiManager = new BybApiManager();

    /**
     * 请求头，所有请求共同需要的参数
     */
    private Map<String, String> headers;
    public static BybApiManager getInstance() {
        return apiManager;
    }

    private BybApiManager() {
        headers = new HashMap<>();
        headers.putAll(initHeader());
    }

    /**
     * 初始化请求头
     */
    private Map initHeader() {
        Map<String, String> map = new HashMap<>();
        map.put("appVersion", "1.0.0");//应用程序版本号
        map.put("osVersion", "6.0.0");//系统版本号
        map.put("termTyp", "ANDROID");//客户端类型(IOS，ANDROID)
        return map;
    }

    public void test() {

    }
}
