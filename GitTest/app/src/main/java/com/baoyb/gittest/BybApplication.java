package com.baoyb.gittest;

import android.app.Application;

import com.apkfuns.logutils.LogUtils;
import com.baoyb.gittest.ui.base.BybActivityStackManager;
import com.zhy.http.okhttp.OkHttpUtils;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

/**
 * 程序入口，进行必要的初始化
 * Created by baoyb on 2017/3/17.
 */

public class BybApplication extends Application{
    private static BybApplication application;
    /**
     * activity部分栈管理
     */
    private BybActivityStackManager ssManager;

    /**
     * 网络请求管理
     */
//    private HttpRequestManager httpDataManager;
    public static BybApplication getApplication() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);

        //日志设置
        // 配置日志是否输出(默认true)
        LogUtils.configAllowLog = true;
        // 配置日志前缀
        LogUtils.configTagPrefix = "byb-";
    }

    public synchronized BybActivityStackManager getActivityStackManager() {
        if (ssManager == null) {
            ssManager = new BybActivityStackManager();
        }
        return ssManager;
    }

    /**
     * 获取数据请求管理者
     *
     * @return
     * @see [类、类#方法、类#成员]
     */
//    public synchronized HttpRequestManager getHttpDataManager() {
//        if (httpDataManager == null) {
//            httpDataManager = new HttpRequestManager(this);
//        }
//        return httpDataManager;
//    }

}
