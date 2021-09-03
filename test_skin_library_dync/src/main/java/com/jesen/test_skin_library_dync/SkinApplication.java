package com.jesen.test_skin_library_dync;

import android.app.Application;

import com.jesen.skin_library_dync.SkinManager;

public class SkinApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SkinManager.init(this);
    }
}
