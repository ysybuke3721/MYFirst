package com.slr.slrapp.utils;

import android.util.Log;

/**
 * Created by Administrator on 2016/6/30 0030.
 */
public class LogUtils {
    private static boolean isDebug = false;

    public static void LogI(String tag, String log) {
        if (isDebug) {
            Log.i(tag, log);
        }
    }
    public static void LogI(Class<?> clazz, String log) {
        if (isDebug) {
            Log.i("SLR"+clazz.getSimpleName(), log);
        }
    }

}
