package com.slr.slrapp.utils;

import android.os.Handler;

import com.slr.slrapp.managers.ThreadPoolManager;


/**
 * 线程工具类
 */
public class ThreadUtils {

    /**
     * 在子线程执行
     * @param runnable
     */
    public static void runOnBackThread(Runnable runnable){

        //  show ---> 新的线程 (线程池)
//        new Thread(runnable).start();
        ThreadPoolManager.getInstatnce().createThreadPool().execute(runnable);
    }

private static Handler handler = new Handler();

    /**
     * 在主线程执行
     * @param runnable
     */
    public static  void runOnUIThread(Runnable runnable){
        handler.post(runnable);
    }

}
