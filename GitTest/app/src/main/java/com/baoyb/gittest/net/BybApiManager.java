package com.baoyb.gittest.net;
import android.support.annotation.StringRes;

import com.baoyb.gittest.BybApplication;
import com.baoyb.gittest.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.util.HashMap;
import java.util.Map;

/**
 * api 管理
 * Created by baoyb on 2017/3/24.
 */

public class BybApiManager {
    private static final String URL_BASE_NEWS_POST = "toutiao/get_list";    //POST方法
    private static BybApiManager apiManager = new BybApiManager();

    private BybRequestManager requestManager;
    /**
     * 请求头，所有请求共同需要的参数
     */
    private Map<String, String> headers;
    public static BybApiManager getInstance() {
        return apiManager;
    }

    private BybApiManager() {
        requestManager = new BybRequestManager();
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

    /**
     * 新闻api
     * @param resId
     * @return
     */
    private String createNewsUrl(@StringRes int resId) {
        StringBuilder builder = new StringBuilder(BybUrlDefines.URL_BASE_NEWS);
        builder.append(BybApplication.getApplication().getString(resId));
        return builder.toString();
    }

    private String createVidiosUrl(@StringRes int resId) {
        StringBuilder builder = new StringBuilder(BybUrlDefines.URL_BASE_IMAGE);
        builder.append(BybApplication.getApplication().getString(resId));
        return builder.toString();
    }


    /**
     * 获取首页新闻
     */
    public void getNewsRecommend(String cate_id, String page, Callback callback) {
        Map params = new HashMap();
        params.put("cate_id", cate_id);
        params.put("page", page);
        requestManager.get(createNewsUrl(R.string.url_home), params, callback);
    }

    /**
     * 获取图片
     * @param page
     * @param callback
     */
    public void getVidioList(String page, Callback callback) {
//        Map params = new HashMap();
//        params.put("page", page);
        requestManager.get(createVidiosUrl(R.string.url_img) + page, null, callback);
    }

    public void getNba(Callback callback) {
        Map params = new HashMap();
        params.put("key", BybUrlDefines.nbaKey);
        requestManager.get(BybUrlDefines.URL_BASE_NBA, params, callback);
    }
}
