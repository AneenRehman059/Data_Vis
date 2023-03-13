package com.zasa.superduper;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

public class MyApp extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    public static Context getMyAppContext(){
        return MyApp.context;
    }
    @Override
    public void onCreate() {

        super.onCreate();
        this.context = this;
    }
}
