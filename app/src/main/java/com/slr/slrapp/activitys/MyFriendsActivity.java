package com.slr.slrapp.activitys;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.slr.slrapp.BaseApplication;
import com.slr.slrapp.R;
import com.slr.slrapp.beans.ShareBean;
import com.slr.slrapp.gson.ApiUtils;
import com.slr.slrapp.gson.GsonRequest;
import com.slr.slrapp.utils.ContentValues;
import com.slr.slrapp.utils.ToastUtil;
import com.slr.slrapp.wxapi.WXShare;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWebPage;
import com.umeng.socialize.utils.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Administrator
 * Time: 2016/7/20 0020
 * Description: ${todo}(邀请好友)
 * Version V1.0
 */
public class MyFriendsActivity extends BaseActivity {

    private Context context;
    private LinearLayout back;
    private TextView title;
    private TextView qqkj, pyq, qqhy, wxhq, xlwb;
    private ShareAction shareAction;
    private int mediatype = 0;//0 none 1 image 2 music 3 video
//   UMImage image = new UMImage(MyFriendsActivity.this, Defaultcontent.imageurl);
//    UMusic music = new UMusic(Defaultcontent.musicurl);
//    UMVideo video = new UMVideo(Defaultcontent.videourl);
    UMWebPage webPage;
    private String url;
    // 用户id
    private String userId;
    // 微信分享
    private WXShare wxShare;
    private ShareBean shareBean;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_my_friends;
    }

    @Override
    public void initView() {
        back = (LinearLayout) findViewById(R.id.title_left);
        title = (TextView) findViewById(R.id.title_text_tv);
        qqkj = (TextView) findViewById(R.id.my_fridends_qqkj);
        pyq = (TextView) findViewById(R.id.my_fridends_pyq);
        qqhy = (TextView) findViewById(R.id.my_fridends_qqhy);
        wxhq = (TextView) findViewById(R.id.my_fridends_wxhy);
        xlwb = (TextView) findViewById(R.id.my_fridends_xlwb);

    }

    @Override
    public void initListener() {
        back.setOnClickListener(this);
        qqkj.setOnClickListener(this);
        pyq.setOnClickListener(this);
        qqhy.setOnClickListener(this);
        wxhq.setOnClickListener(this);
        xlwb.setOnClickListener(this);
    }

    @Override
    public void initData() {
        context = this;
        title.setText(R.string.my_invite_friends);
        wxShare = new WXShare(context);
        shareAction = new ShareAction(MyFriendsActivity.this);
        userId = context.getSharedPreferences(ContentValues.SP_NAME, 0).getString(ContentValues.USER_ID, null);
        url = ApiUtils.APP_API + "/share";

        // 网络请求获取分享内容
        GetNetData(url, userId);

        //init music
//        music.setTitle("This is music title");
//        music.setThumb("http://www.umeng.com/images/pic/social/chart_1.png");
//        music.setDescription("my description");
        //init video
//        video.setThumb("http://www.adiumxtras.com/images/thumbs/dango_menu_bar_icon_set_11_19047_6240_thumb.png");
    }

    // 获取分享内容
    private void GetNetData(String url, String userId) {

        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        GsonRequest<ShareBean> gsonRequest = new GsonRequest<ShareBean>(map,
                url, ShareBean.class,
                new Response.Listener<ShareBean>() {
                    @Override
                    public void onResponse(ShareBean response) {

                        shareBean = response;
                        if (response.getCode()!=200){

                            ToastUtil.showTextToast(response.getMessage());

                        }else{
                            webPage = new UMWebPage(response.getUrl());
                            webPage.setTitle("山里人人");
                            webPage.setDescription(response.getContent());
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                ToastUtil.showTextToast("网络异常！");
                shareBean = null;
            }
        });
        BaseApplication.getInstance().getRequestQueue().add(gsonRequest);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.my_fridends_qqkj:

                if (shareBean!=null){

                    if (shareBean.getCode()==200){


                        shareAction.withText(shareBean.getContent());
                        shareAction.withTargetUrl(shareBean.getUrl());
//                if (mediatype == 1) {
//                    shareAction.withMedia(image);
//                } else if (mediatype == 2) {
//                    shareAction.withMedia(music);
//                } else if (mediatype == 3) {
//                    shareAction.withMedia(video);
//                }
                        shareAction.setPlatform(SHARE_MEDIA.QZONE).setCallback(umShareListener).share();

                    }else {
                        ToastUtil.showTextToast(shareBean.getMessage());
                    }
                }else {
                    ToastUtil.showTextToast("网络异常！");
                }
                break;
            case R.id.my_fridends_pyq:

                if (shareBean!=null){

                    if (shareBean.getCode()==200){

                        wxShare.WXShareWebPage(shareBean.getUrl(), 0, "山里人人", shareBean.getContent());

                    }else {
                        ToastUtil.showTextToast(shareBean.getMessage());
                    }
                }else {
                    ToastUtil.showTextToast("网络异常！");
                }

                break;
            case R.id.my_fridends_qqhy:

                if (shareBean!=null){

                    if (shareBean.getCode()==200){

                        shareAction.withText(shareBean.getContent());
                        shareAction.withTargetUrl(shareBean.getUrl());
//                if (mediatype == 1) {
//                    shareAction.withMedia(image);
//                } else if (mediatype == 2) {
//                    shareAction.withMedia(music);
//                } else if (mediatype == 3) {
//                    shareAction.withMedia(video);
//                }
                        shareAction.setPlatform(SHARE_MEDIA.QQ).setCallback(umShareListener).share();

                    }else {
                        ToastUtil.showTextToast(shareBean.getMessage());
                    }
                }else {
                    ToastUtil.showTextToast("网络异常！");
                }
                break;
            case R.id.my_fridends_wxhy:

                if (shareBean!=null){

                    if (shareBean.getCode()==200){

                        wxShare.WXShareWebPage(shareBean.getUrl(), 1, "山里人人", shareBean.getContent());

                    }else {
                        ToastUtil.showTextToast(shareBean.getMessage());
                    }
                }else {
                    ToastUtil.showTextToast("网络异常！");
                }

                break;
            case R.id.my_fridends_xlwb:

                if (shareBean!=null){

                    if (shareBean.getCode()==200){

                        shareAction.withText(shareBean.getContent());
                        shareAction.withTargetUrl(shareBean.getUrl());
//                if (mediatype == 1) {
//                    shareAction.withMedia(image);
//                } else if (mediatype == 2) {
//                    shareAction.withMedia(music);
//                } else if (mediatype == 3) {
//                    shareAction.withMedia(video);
//                }
                        shareAction.setPlatform(SHARE_MEDIA.SINA).setCallback(umShareListener).share();

                    }else {
                        ToastUtil.showTextToast(shareBean.getMessage());
                    }
                }else {
                    ToastUtil.showTextToast("网络异常！");
                }
                break;

        }

    }


    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            if (platform.name().equals("WEIXIN_FAVORITE")) {
                ToastUtil.showTextToast("收藏成功啦");
            } else {
                ToastUtil.showTextToast("分享成功啦");
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            ToastUtil.showTextToast("分享失败啦");
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            ToastUtil.showTextToast("分享取消了");
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MyFriends Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.slr.slrapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MyFriends Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.slr.slrapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    //打开面板方法
//    new ShareAction(MyFriendsActivity.this).setDisplayList(SHARE_MEDIA.QZONE,SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.SINA)
//    .withTitle(Defaultcontent.title)
//    .withText(Defaultcontent.text+"——来自山里人人")
//    .withMedia(new UMImage(MyFriendsActivity.this,"http://dev.umeng.com/images/tab2_1.png"))
//            .withTargetUrl("https://wsq.umeng.com/")
//    .setCallback(umShareListener)
//    .open();

}
