package com.baoyb.gittest.net;

import com.baoyb.gittest.util.LogUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;
import okhttp3.Response;

/**
 * Created by baoyb on 2017/3/29.
 */

public abstract class BybRequestCallback<T> extends Callback<T> {
    private Class object;
    public BybRequestCallback(Class clazz) {
        object = clazz;
    }

    @Override
    public T parseNetworkResponse(Response response, int id) throws Exception {
        String string = response.body().string();
        T data = (T) new Gson().fromJson(string, object);
        try {
            LogUtils.json("byb", string);
        } catch (Exception e) {

        }
        return data;
    }
}
