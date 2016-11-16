package com.slr.slrapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.slr.slrapp.BaseApplication;
import com.slr.slrapp.R;
import com.slr.slrapp.activitys.MyCollectionActivity;
import com.slr.slrapp.activitys.MyDistributorsActivity;
import com.slr.slrapp.activitys.MyEvalutaionsActivity;
import com.slr.slrapp.activitys.MyFriendsActivity;
import com.slr.slrapp.activitys.MyMessageActivity;
import com.slr.slrapp.activitys.MyMoreActivity;
import com.slr.slrapp.activitys.MyOrderActivity;
import com.slr.slrapp.activitys.MyWalletActivity;
import com.slr.slrapp.activitys.UserInfoActivity;
import com.slr.slrapp.beans.UserInfoBean;
import com.slr.slrapp.gson.ApiUtils;
import com.slr.slrapp.gson.GsonRequest;
import com.slr.slrapp.managers.LoginManger;
import com.slr.slrapp.utils.BitmapUtils;
import com.slr.slrapp.utils.ContentValues;
import com.slr.slrapp.utils.ToastUtil;
import com.slr.slrapp.widget.CircleImageView;
import com.squareup.picasso.Picasso;

/**
 * 我的
 * A simple {@link Fragment} subclass.
 */
public class MySelfFragment extends BaseFragment {


    private RelativeLayout  dd, fxs, qb ,pj ,hy , gd;
    private TextView my_name,my_introduction,my_collection_num,my_message_num;
    private CircleImageView my_face;
    private LinearLayout sc , xx;
    private BitmapUtils bitmapUtils;
    private Context context;
    private RelativeLayout me;
    private Boolean isLogin = false;
    // 用户id
    private String userId ;
    // 登录管理
    private LoginManger loginManger;

    @Override
    public int getLayoutResID() {

        return R.layout.fragment_my_self;
    }

    @Override
    public void initView(View view) {
        me = (RelativeLayout) view.findViewById(R.id.my_me);
        sc = (LinearLayout)view.findViewById(R.id.my_collection);
        xx = (LinearLayout)view.findViewById(R.id.my_message);
        dd = (RelativeLayout)view.findViewById(R.id.my_order);
        fxs = (RelativeLayout)view.findViewById(R.id.my_distributors);
        qb= (RelativeLayout)view.findViewById(R.id.my_wallet);
        pj = (RelativeLayout)view.findViewById(R.id.my_evaluation);
        hy = (RelativeLayout)view.findViewById(R.id.my_invite_friends);
        gd = (RelativeLayout)view.findViewById(R.id.my_more);

        my_face = (CircleImageView) view.findViewById(R.id.my_face);
        my_name = (TextView) view.findViewById(R.id.my_name);
        my_introduction = (TextView) view.findViewById(R.id.my_introduction);
        my_collection_num = (TextView) view.findViewById(R.id.my_collection_num);
        my_message_num = (TextView) view.findViewById(R.id.my_message_num);

        Resources res = getResources();
        Bitmap bmp = BitmapFactory.decodeResource(res, R.mipmap.face);
        bitmapUtils = new BitmapUtils();
        Bitmap bg = bitmapUtils.blurBitmap(getActivity(), bmp);
        me.setBackgroundDrawable(new BitmapDrawable(bg));
        context = getActivity();
        userId=context.getSharedPreferences(ContentValues.SP_NAME, 0).getString(ContentValues.USER_ID, null);
        loginManger = new LoginManger(context);
    }

    @Override
    public void initListener() {

        me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (loginManger.CheckLoginStatic()){
                    SkipActivity(UserInfoActivity.class);
                }else {
                    loginManger.LoginCheck(UserInfoActivity.class, false);
                }


            }

        });

        sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (loginManger.CheckLoginStatic()){
                    SkipActivity(MyCollectionActivity.class);
                }else {
                    loginManger.LoginCheck(MyCollectionActivity.class, true);
                }

            }
        });

        xx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SkipActivity(MyMessageActivity.class);
            }
        });

        dd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (loginManger.CheckLoginStatic()){
                    SkipActivity(MyOrderActivity.class);
                }else {
                    loginManger.LoginCheck(MyOrderActivity.class, true);
                }

            }
        });

        fxs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (loginManger.CheckLoginStatic()){
                    SkipActivity(MyDistributorsActivity.class);
                }else {
                    loginManger.LoginCheck(MyDistributorsActivity.class, true);
                }

            }
        });

        qb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (loginManger.CheckLoginStatic()){
                    SkipActivity(MyWalletActivity.class);
                }else {
                    loginManger.LoginCheck(MyWalletActivity.class, true);
                }

            }
        });

        pj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (loginManger.CheckLoginStatic()){
                    SkipActivity(MyEvalutaionsActivity.class);
                }else {
                    loginManger.LoginCheck(MyEvalutaionsActivity.class, true);
                }

            }
        });

        hy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (loginManger.CheckLoginStatic()){
                    SkipActivity(MyFriendsActivity.class);
                }else {
                    loginManger.LoginCheck(MyFriendsActivity.class, true);
                }

            }
        });

        gd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SkipActivity(MyMoreActivity.class);
            }
        });

    }

    @Override
    public void initData() {
        context = getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData(){

        userId=context.getSharedPreferences(ContentValues.SP_NAME, 0).getString(ContentValues.USER_ID, null);
        isLogin = loginManger.CheckLoginStatic();
        System.out.println("登录状态："+isLogin+"///用户id:"+context.getSharedPreferences(ContentValues.SP_NAME, 0).getString(ContentValues.USER_ID, null));
       if (loginManger.CheckLoginStatic()){
           // 获取用户id
           String url = ApiUtils.GetUser(userId);
           System.out.println("用户详情："+url);
           GsonRequest<UserInfoBean> gsonRequest = new GsonRequest<UserInfoBean>(url, UserInfoBean.class,
                   new Response.Listener<UserInfoBean>() {
                       @Override
                       public void onResponse(UserInfoBean response) {
                           if (response.getCode()== 200) {
                               my_introduction.setVisibility(View.VISIBLE);
                               UserInfoBean.UserBean userBean = response.getUser();
                               Picasso.with(context).load(userBean.getHead()).error(R.mipmap.icon_default).into(my_face);
                               if (userBean.getHead().length()!=0||userBean.getHead()!=null){
                                   sharedPreferences.edit().putString(ContentValues.FACE, userBean.getHead()).apply();
                               }else{
                                   sharedPreferences.edit().putString(ContentValues.FACE, null).apply();
                               }
                               my_name.setText(userBean.getNickName());
                               my_introduction.setText(String.valueOf(userBean.getSignature()));
                               my_collection_num.setText(String.valueOf(userBean.getCollectNum()));
                               my_message_num.setText(String.valueOf(userBean.getMessageNum()));
                           }else{
                               ToastUtil.showTextToast(response.getMessage());
                               isLogin = false;
                               ReLogin();
                           }
                       }
                   }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {
                   ToastUtil.showTextToast("网络请求失败！");
               }
           });
           BaseApplication.getInstance().getRequestQueue().add(gsonRequest);

       }else{
           ReLogin();
       }

    }

    // 重新登录
    private void ReLogin(){
        //是否第一次使用
        //boolean if_first_use=context.getSharedPreferences(ContentValues.SP_NAME,0).getBoolean(ContentValues.IF_FIRST_USE,true);
        my_face.setImageResource(R.mipmap.icon_default);
        my_introduction.setVisibility(View.GONE);
        my_name.setText("请点击登录");
        my_name.setTextSize(18);
    }

    /**
     *
     * Time: 2016/7/5 0005 下午 2:44
     * Description: ${todo}(界面跳转)
     * param: ${class}
     * return: ${return_type}
     */
    private void SkipActivity(Class c) {

        Intent intent = new Intent(context, c);
//        if (c == MyMoreActivity.class){
//            startActivityForResult(intent, 0);
//        }else{
            startActivity(intent);
//        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        int flag = data.getIntExtra("flag",0);
//        System.out.println("返回数据："+requestCode+"////"+flag);
//        // 刷新界面
//        if (resultCode == 0){
//
//            if (flag == 1){
//
//                loadData();
//            }
//
//        }

    }
}
