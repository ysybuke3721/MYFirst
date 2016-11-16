package com.slr.slrapp.managers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.slr.slrapp.activitys.MyLoginActivity;
import com.slr.slrapp.utils.ContentValues;

/**
 * 检查是否登录，未登录跳入登录界面
 * Created by admin on 2016/8/17.
 */
public class LoginManger {

    private Context context;

    public LoginManger(Context context){

        this.context = context;

    }

    //是否登录状态
    private Boolean LoginState ;

    //根据登录状态进入不同页面
    public void LoginCheck(Class nextActivity, boolean SkipState){

        // 未登录进入登录界面
        Intent intent = new Intent(context, MyLoginActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("CLASS", nextActivity);
        bundle.putBoolean("SkipState", SkipState);
        intent.putExtras(bundle);
        context.startActivity(intent);
//            ((Activity)context).finish();

    }

    // 判断是否登录
    public Boolean CheckLoginStatic(){
        return LoginState=context.getSharedPreferences(ContentValues.SP_NAME,0).getBoolean(ContentValues.IF_IS_LOGINED,false);
    }


}
