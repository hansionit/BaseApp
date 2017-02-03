package com.roboocean.baseapp;

import android.app.Application;
import android.content.Context;

/**
 * Description：
 * Author: Hansion
 * Time: 2017/1/19 9:16
 */
public class App extends Application {

    //Context
    private static App mInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
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
}
