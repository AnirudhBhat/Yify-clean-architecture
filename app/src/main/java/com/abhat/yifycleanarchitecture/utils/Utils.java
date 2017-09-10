package com.abhat.yifycleanarchitecture.utils;

import android.content.Context;
import android.content.Intent;


import java.io.IOException;
import java.util.List;

/**
 * Created by Anirudh Uppunda on 11/5/17.
 */

public class Utils {
    /*
       http://stackoverflow.com/questions/1560788/how-to-check-internet-access-on-android-inetaddress-never-times-out
    */
    private static Utils utils = null;
    public static boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8"); //Google DNS (8.8.8.8)
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        }
        catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }

        return false;
    }
}
