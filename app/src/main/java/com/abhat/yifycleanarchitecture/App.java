package com.abhat.yifycleanarchitecture;

import android.app.Application;
import android.content.Context;


/**
 * Created by Anirudh Uppunda on 18/4/17.
 */

public class App extends Application {

    private static App mContext;

    public static Context getContext() {
        return mContext;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }
}
