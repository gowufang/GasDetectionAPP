package com.example.administrator.base;

import android.app.Application;


public class BaseApplication extends Application {

    private static BaseApplication mApplication;


    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;

    }

    public static BaseApplication getIntance() {
        return mApplication;
    }

}
