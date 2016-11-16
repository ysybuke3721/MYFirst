package com.slr.slrapp.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.slr.slrapp.BaseApplication;
import com.slr.slrapp.R;
import com.slr.slrapp.utils.ContentValues;
import com.slr.slrapp.utils.UiUtils;

import java.io.File;

/**
 * 欢迎引导页
 */
public class WelcomeActivity extends AppCompatActivity {
    Context context = UiUtils.getContext();
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
//                    init();
                    finish();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
//        handler.sendEmptyMessageDelayed(1,1000);
        String CachePath = BaseApplication.getCachePath()+"/photos/";
        File newFile = new File(CachePath.trim());

        if (!newFile.exists()){
            newFile.mkdirs();// 创建目录
        }
        init();

    }

    private void init() {
        //是否登录状态
        boolean if_logined = context.getSharedPreferences(ContentValues.SP_NAME, 0).getBoolean(ContentValues.IF_IS_LOGINED, false);
        //是否第一次使用
        boolean if_first_use=context.getSharedPreferences(ContentValues.SP_NAME,0).getBoolean(ContentValues.IF_FIRST_USE,true);
        if (if_first_use){
            Intent  into=new Intent(context,LeaderActivity.class);
            startActivity(into);
            finish();
        }else {
//            if (if_logined) {
            /**延迟几秒**/
            new CountDownTimer(1000*3,1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onFinish() {
                    // TODO Auto-generated method stub
                    //选择性跳转
                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }.start();

//            } else {
//                Intent intent1 = new Intent(context, MyLoginActivity.class);
//                startActivity(intent1);
//            }
        }
    }
}
