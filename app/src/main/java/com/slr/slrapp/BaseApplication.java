package com.slr.slrapp;


import android.app.AlertDialog;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.baidu.mapapi.SDKInitializer;
import com.umeng.socialize.PlatformConfig;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;


/**
 * 全局最大的对象
 * Created by Administrator on 2016/7/2 0002.
 */



public class BaseApplication extends Application {
    private static BaseApplication instance;
    private RequestQueue requestQueue;

    // 用于存放倒计时时间
    public static Map<String, Long> map;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        SDKInitializer.initialize(this);
//
//        UnCeHandler catchExcep = new UnCeHandler(this);
//        Thread.setDefaultUncaughtExceptionHandler(catchExcep);

        /**
         * 配置okHttpClient
         */
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);

        /**
         * 设置volley
         */
        requestQueue = Volley.newRequestQueue(this);

        //各个平台的配置，建议放在全局Application或者程序入口
        //微信
        PlatformConfig.setWeixin("wx70997146974e950c", "241a6164f77f00d8f648126d85e10c3e");
        //新浪微博
        PlatformConfig.setSinaWeibo("726702500", "241a6164f77f00d8f648126d85e10c3e");
        //QQ空间
        PlatformConfig.setQQZone("1105577374", "MjiHlPzm3qZ1tkiE");
    }

    public static ProgressDialog getProgressDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    public AlertDialog.Builder getAlertDialog(int titleId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(titleId);
        return builder;
    }

    public static BaseApplication getInstance() {
        return instance;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    // 缓存地址
    public static String getCachePath() {
        return "/mnt/sdcard/slrr/cache";
    }

}
