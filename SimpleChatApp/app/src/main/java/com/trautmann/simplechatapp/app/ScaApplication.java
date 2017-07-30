package com.trautmann.simplechatapp.app;

import android.app.Application;

import com.trautmann.simplechatapp.util.PreferencesHelper;

/**
 * Created by Brandon Trautmann
 */

public class ScaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PreferencesHelper.initialize(this);

    }
}
