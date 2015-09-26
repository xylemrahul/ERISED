package com.erised.base;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

/**
 * Created by neosoft on 26/9/15.
 */
public abstract class BaseFragment extends Fragment {

    public static final String TAG = "Google Info";

    /**
     * Initializes the UI element
     * */
    protected abstract void initUI(View view);

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
