package com.baoyb.gittest.util;

import android.app.Activity;

import java.util.Stack;

/**
 * activity 类管理工具
 * Created by baoyb on 2017/4/11.
 */

public class ActivityManager {
    private static volatile ActivityManager instance;
    private Stack<Activity> mActivityStack = new Stack<Activity>();

    private ActivityManager(){

    }

    public static ActivityManager getInstance() {
        if (instance == null) {
            synchronized (ActivityManager.class) {
                if (instance == null) {
                    instance = new ActivityManager();
                }
            }
        }
        return instance;
    }
    public void addActicity(Activity act){
        mActivityStack.push(act);
    }

    public void removeActivity(Activity act){
        mActivityStack.remove(act);
    }

    public void killMyProcess(){
        int nCount = mActivityStack.size();
        for (int i = nCount - 1; i >= 0; i--) {
            Activity activity = mActivityStack.get(i);
            activity.finish();
        }

        mActivityStack.clear();
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
