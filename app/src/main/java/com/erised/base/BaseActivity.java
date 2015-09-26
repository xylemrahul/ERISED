package com.erised.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Rahul on 26/9/15.
 */
public abstract class  BaseActivity  extends AppCompatActivity {

    public static final String TAG = "Google Info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        initUI();

        initData();
    }

    /**
     * Initializes the UI element
    * */
    protected abstract void initUI();

    /**
     * Initializes or creating Data element required for Activity
     * */
    protected abstract void initData();


    public void logE(String msg) {

        Log.e(TAG, " " + msg);
    }
    public void logD(String msg) {

        Log.d(TAG, " " + msg);
    }
    public void logI(String msg) {

        Log.i(TAG, " " + msg);
    }
}
