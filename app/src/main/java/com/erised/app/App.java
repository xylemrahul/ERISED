package com.erised.app;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by rahul on 26/9/15.
 */
public class App extends Application {

    private static App instance;
    private static SharedPreferences sp;

    @Override
    public void onCreate() {

        super.onCreate();
        instance = this;

        sp = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
    }

    public static SharedPreferences getS() {

        return sp;
    }

}
