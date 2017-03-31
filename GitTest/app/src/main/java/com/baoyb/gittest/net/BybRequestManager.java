package com.baoyb.gittest.net;

import android.support.annotation.StringRes;

import com.baoyb.gittest.BybApplication;
import com.baoyb.gittest.util.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import java.util.Map;
/**
 * Created by baoyb on 2017/3/29.
 */

public class BybRequestManager {

    public void post(String url, Map params, Callback callback) {
        LogUtils.e(url);
        OkHttpUtils
                .post()
                .params(params)
                .url(url)
                .build()
                .execute(callback);
    }

    public void get(String url, Map params, Callback callback) {
        LogUtils.e(url);
        OkHttpUtils
                .get()
                .params(params)
                .url(url)
                .build()
                .execute(callback);
    }


}
