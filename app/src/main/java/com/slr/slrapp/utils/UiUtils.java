package com.slr.slrapp.utils;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.slr.slrapp.BaseApplication;
import com.slr.slrapp.wxapi.Constants;
import com.squareup.picasso.Picasso;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/7/2 0002.
 */
public class UiUtils {
    /**
     * 通过id  获取String-array
     *
     * @param id
     * @return
     */
    public static String[] getStringArray(int id) {
        return getResources().getStringArray(id);
    }

    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 最长生命周期的上下文
     *
     * @return
     */
    public static Context getContext() {
        return BaseApplication.getInstance();
    }

    /**
     * xml  --  view
     * 解析布局
     *
     * @param id
     * @return
     */
    public static View inflate(int id) {
        return View.inflate(getContext(), id, null);
    }

    /*用picasso加载图片*/
    public static void PicassoLoadImg(String url, ImageView view) {
        Picasso.with(UiUtils.getContext()).load(url).into(view);
    }


    /*格式化金额*/

    public static String FormatMoneyStyle(String moneyStyle) {
        String retunrnMoney = null;
        if (!TextUtils.isEmpty(moneyStyle) && moneyStyle.length() > 0) {
            if (moneyStyle.contains(".")) {
                String[] money = moneyStyle.split("\\.");
                if (money[1].length() < 2) {
                    money[1] += "0";
                }
                retunrnMoney = money[0] + "." + money[1];
            } else {
                retunrnMoney=moneyStyle +".00";
            }
        }
        return "￥"+retunrnMoney;

    }
      /*格式化金额*/

    public static double FormatMoneyStyle(double price) {
        String moneyStyle=String.valueOf(price);
        String retunrnMoney = null;
        if (!TextUtils.isEmpty(moneyStyle) && moneyStyle.length() > 0) {
            if (moneyStyle.contains(".")) {
                String[] money = moneyStyle.split("\\.");
                if (money[1].length() < 2) {
                    money[1] += "0";
                }
                retunrnMoney = money[0] + "." + money[1];
            } else {
                retunrnMoney=moneyStyle +".00";
            }
        }
        return Double.valueOf(retunrnMoney);

    }

    /*格式化时间*/
    //TODO
    public static String FormatDate(String date)  {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date1 = null;
        if (date != null && date.length() > 0) {
            date1 = format.format(date);
        }
        return date1;
    }

    /*匹配邮箱*/
    public static  boolean matchMail(String mail){
       // "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$"
        Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        Matcher matcher = pattern.matcher(mail);
        return  matcher.matches();
    }



    //判断用户是否安装了微信
    public static boolean isWXAppInstalledAndSupported() {
        IWXAPI msgApi = WXAPIFactory.createWXAPI(UiUtils.getContext(), null);
        msgApi.registerApp(Constants.APP_ID);

        boolean sIsWXAppInstalledAndSupported = msgApi.isWXAppInstalled()
                && msgApi.isWXAppSupportAPI();

        return sIsWXAppInstalledAndSupported;
    }

}
