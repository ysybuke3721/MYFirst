package com.slr.slrapp.wxapi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.slr.slrapp.R;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import static com.slr.slrapp.utils.UiUtils.getResources;

/**
 * 微信分享
 * Created by admin on 2016/8/24.
 */
public class WXShare {

    private Context context;
    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;

    public WXShare (Context context){

        this.context = context;
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(context, Constants.APP_ID, false);
        //将该app注册到微信
        api.registerApp(Constants.APP_ID);

    }

    // 分享一个网页 0分享到朋友圈
    public void WXShareWebPage(String WebUrl, int n, String title, String description){

        // 初始化WXWebpageObject对象，填写分享的url
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = WebUrl;

        // 用WXWebpageObject对象初始化一个WXMediaMessage 对象，填写标题、描述
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = title;
        msg.description = description;
        Bitmap thumb = BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon);
//        msg.thumbData = Ut

        // 构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;

        if (n == 0){
            //分享到朋友圈（微信版本4.2之前不支持分享到朋友圈）
            req.scene = SendMessageToWX.Req.WXSceneTimeline ;
        }else {
            //分享给好友
            req.scene = SendMessageToWX.Req.WXSceneSession;

        }

        api.sendReq(req);

    }

    // 分享类型
    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

}
