package com.hansion.baseapp;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;

/**
 * Description：
 * Author: Hansion
 * Time: 2017/1/19 9:16
 */
public class App extends Application {

    //Context
    private static App mInstance;
    //当前是否是Debug模式，一般用于判断是否打印Log (无需更改此值,会自动判断)
    public static boolean isDebug = true;
    //当前设备是否是平板电脑
    public static boolean isTablet;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        //判断是否是Debug模式
        isDebug = isApkDebugable();
        //判断是否是平板电脑
        isTablet = getResources().getBoolean(R.bool.isTablet);
    }


    //For get Global Context
    public static Context getAppContext() {
        if (mInstance != null) {
            return mInstance;
        } else {
            mInstance = new App();
            mInstance.onCreate();
            return mInstance;
        }
    }


    /**
     * @return 当前是否是Debug模式
     */
    public boolean isApkDebugable() {
        ApplicationInfo info = getApplicationInfo();
        return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }
}
