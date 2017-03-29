package com.baoyb.gittest.net;

import android.support.annotation.StringRes;

import com.apkfuns.logutils.LogUtils;
import com.baoyb.gittest.BybApplication;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import java.util.Map;
/**
 * Created by baoyb on 2017/3/29.
 */

public class BybRequestManager {

    public void post(int apiId, Map params, Callback callback) {
        String url = createUrl(apiId);
        LogUtils.e(url);
        LogUtils.e(params);
        OkHttpUtils
                .post()
                .params(params)
                .url(url)
                .build()
                .execute(callback);
    }

    public void get(int apiId, Map params, Callback callback) {
        String url = createUrl(apiId);
        LogUtils.e(url);
        LogUtils.e(params);
        OkHttpUtils
                .get()
                .params(params)
                .url(url)
                .build()
                .execute(callback);
    }

    private String createUrl(@StringRes int resId) {
        StringBuilder builder = new StringBuilder(BybUrlDefines.URL_BASE_NEWS);
        builder.append(BybApplication.getApplication().getString(resId));
        return builder.toString();
    }
}
