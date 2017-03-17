package com.baoyb.gittest;

import android.app.Application;

/**
 * 程序入口，进行必要的初始化
 * Created by baoyb on 2017/3/17.
 */

public class BybApplication extends Application{
    private static BybApplication application;

    public static BybApplication getApplication() {
        return application;
    }

    @Override
    public void onCreate() {
        application = this;
    }
}
