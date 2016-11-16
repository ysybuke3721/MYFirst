package com.slr.slrapp.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.netease.neliveplayer.NELivePlayer;
import com.netease.neliveplayer.NEMediaPlayer;
import com.slr.slrapp.BaseApplication;
import com.slr.slrapp.R;
import com.slr.slrapp.adapters.MyDefaultAdapter;
import com.slr.slrapp.beans.CameraBean;
import com.slr.slrapp.beans.UserCommountBean;
import com.slr.slrapp.beans.simpleNetBean;
import com.slr.slrapp.gson.GsonRequest;
import com.slr.slrapp.managers.LoginManger;
import com.slr.slrapp.utils.ContentValues;
import com.slr.slrapp.utils.ToastUtil;
import com.slr.slrapp.utils.UiUtils;
import com.slr.slrapp.widget.MyNumberButton;
import com.slr.slrapp.widget.NEMediaController;
import com.slr.slrapp.widget.NEVideoView;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*养殖场监控页面*/
public class FarmCameraActivity extends Activity implements View.OnClickListener {

    private ImageView back;
    private ImageView iv_search;
    private ListView lv_users_appraise;
    private TextView tv_shop_car;
    private TextView tv_buy_now;
    private TextView tv_put_in_shop_car;
    private NELivePlayer mMediaPlayer = null;

    //Listview的头布局
    private LinearLayout ll_video;   //包含直播控件的控件
    private NEVideoView video_player;   //直播控件
    private NEMediaController mMediaController;
    private TextView tv_price;   //价格
    private RatingBar rating_bar; //星星
    private int palyType = 0;    //0：低延时 1：防抖动
    private boolean deCodeer = true;  //true  : 硬解码  false：软解码
    private String videoPath;
    private LinearLayout ll_collect;
    private ImageView iv_if_collect;   //收藏

    private TextView tv_farm_name;     //牧场名字
    private TextView tgv_farm_introduce;  //牧场介绍

    private EditText et_leave_message;   //留言框
    private TextView tv_leave_message;   //留言按钮

    private RelativeLayout rl_prepare;
    private TextView tv_tishi;
    private ProgressBar pb;

    private RelativeLayout rl_wait;

    private PopupWindow popupWindow;
    //popwindow中的控件
    private ImageView iv_farm_pic;
    private TextView tv_pop_farm_name;
    private TextView tv_goods_price;
    private TextView tv_count_left;
    private ImageView iv_cancel_buy;
//    private NumberButton number_button;

    //第一次和第二次点击返回键的时间
    private long firsttime = 0;
    private long secondtime = 0;
    boolean ispressed = false;

    //增加数量按钮
    private MyNumberButton number_button;
//    private AddAndSubView addAndSubView ;//= new AddAndSubView(UiUtils.getContext(), 1);

    private TextView iv_enure;
    //弹窗的透明区域
    private View alpha_view;

    //弹窗的view
    private View popview;

    //商品是否被收藏
    private boolean if_chose_collect = false;
    private SharedPreferences sharedPreferences = UiUtils.getContext().getSharedPreferences(ContentValues.SP_NAME, 0);
    //加入购物车和立即购买的标记
    private String TAG_BUY_NOW = "buy_now";
    private String TAG_PUT_IN_SHOP_CAR = "put_in_shop_car";
    //商品id
    private int goodid = -1;
    //分页数目
    private int freshNum = 0;
    private String userid = sharedPreferences.getString(ContentValues.USER_ID, "");

    //商品详情的json用于提交订单使用
    private CameraBean.ComIdformationBean goodJson;
    //提交订单路径，0 直接提交，1 购物车提交
    private int submitPath = 0;

    private boolean if_first_submit = true;

    private String mes = "";
    //提交按钮是否可用
    private boolean if_submit_useful = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_farm_camera);
        initView();
        initListener();
        initData();

    }
    //    @Override
//    public int getLayoutResId() {
//        return R.layout.activity_farm_camera;
//    }

    //    @Override
    public void initView() {
        mMediaPlayer = new NEMediaPlayer();


        //获取商品id
        goodid = getIntent().getIntExtra("goodsId", -1);
        //联网获取数据
        getNetData(goodid, freshNum);
        rl_wait = (RelativeLayout) findViewById(R.id.rl_wait);
        back = (ImageView) findViewById(R.id.back);
        iv_search = (ImageView) findViewById(R.id.iv_search);
        lv_users_appraise = (ListView) findViewById(R.id.lv_users_appraise);
        tv_shop_car = (TextView) findViewById(R.id.tv_shop_car);
        tv_buy_now = (TextView) findViewById(R.id.tv_buy_now);
        tv_put_in_shop_car = (TextView) findViewById(R.id.tv_put_in_shop_car);


        //给两个按钮添加标记，以便弹出相同的弹窗进行不同的操作
//        tv_buy_now.setTag(TAG_BUY_NOW);
//        tv_put_in_shop_car.setTag(TAG_PUT_IN_SHOP_CAR);

        back.setOnClickListener(this);
        iv_search.setOnClickListener(this);
        tv_shop_car.setOnClickListener(this);
        tv_buy_now.setOnClickListener(this);
        tv_put_in_shop_car.setOnClickListener(this);


        View view = initHeadView();


        //listview添加头布局
        lv_users_appraise.addHeaderView(view);


        //弹框的布局初始化
        popview = UiUtils.inflate(R.layout.pop_view_buy_instance);


        iv_farm_pic = (ImageView) popview.findViewById(R.id.iv_farm_pic);
        tv_pop_farm_name = (TextView) popview.findViewById(R.id.tv_pop_farm_name);
        tv_goods_price = (TextView) popview.findViewById(R.id.tv_goods_price);
        tv_count_left = (TextView) popview.findViewById(R.id.tv_count_left);
        iv_cancel_buy = (ImageView) popview.findViewById(R.id.iv_cancel_buy);
        number_button = (MyNumberButton) popview.findViewById(R.id.number_button);

        iv_enure = (TextView) popview.findViewById(R.id.iv_enure);
        alpha_view = popview.findViewById(R.id.alpha_view);


        iv_cancel_buy.setOnClickListener(this);
        iv_enure.setOnClickListener(this);
        alpha_view.setOnClickListener(this);
        number_button.setOnClickListener(this);


    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:

                    String path = msg.getData().getString("path");
                    video_player.setVideoPath(path);
                    //得到直播视频的网址，并启动
                    video_player.start();


                    break;
            }


        }
    };


    @NonNull
    private View initHeadView() {
        //初始化listview的头布局
        View view = UiUtils.inflate(R.layout.list_head_farm_camera);

        video_player = (NEVideoView) view.findViewById(R.id.video_player);
        ll_video = (LinearLayout) view.findViewById(R.id.ll_video);
        tv_leave_message = (TextView) view.findViewById(R.id.tv_leave_message);
        et_leave_message = (EditText) view.findViewById(R.id.et_leave_message);
        tv_price = (TextView) view.findViewById(R.id.tv_price);
        rating_bar = (RatingBar) view.findViewById(R.id.rating_bar);
        iv_if_collect = (ImageView) view.findViewById(R.id.iv_if_collect);
        tv_farm_name = (TextView) view.findViewById(R.id.tv_farm_name);
        tgv_farm_introduce = (TextView) view.findViewById(R.id.tgv_farm_introduce);
        ll_collect = (LinearLayout) view.findViewById(R.id.ll_collect);
        rl_prepare = (RelativeLayout) view.findViewById(R.id.rl_prepare);
        tv_tishi = (TextView) view.findViewById(R.id.tv_tishi);
        pb = (ProgressBar) view.findViewById(R.id.pb);
//        mMediaController = new NEMediaController(this);
        ll_video.setOnClickListener(this);




//        video_player.setMediaController(mMediaController);


//        mVideoView.setMediaBufferingIndicator(mBufferingIndicator);

        //mVideoView.setBufferStrategy(0); //低延时
        //mVideoView.setBufferStrategy(1); //抗抖动

        video_player.setBufferStrategy(palyType);  //直播低延时
        video_player.setHardwareDecoder(deCodeer); //硬件解码还是软件解码


        video_player.requestFocus();

        tv_leave_message.setOnClickListener(this);
        ll_collect.setOnClickListener(this);

        return view;
    }


    //    @Override
    public void initListener() {
        //视频准备好的监听
        video_player.setOnPreparedListener(new NELivePlayer.OnPreparedListener() {
            @Override
            public void onPrepared(NELivePlayer neLivePlayer) {
                ll_video.setVisibility(View.VISIBLE);
                rl_prepare.setVisibility(View.INVISIBLE);
            }
        });


        video_player.setOnErrorListener(new NELivePlayer.OnErrorListener() {
            @Override
            public boolean onError(NELivePlayer neLivePlayer, int i, int i1) {
                return true;
            }
        });

        et_leave_message.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String str=editable.toString();
                if (TextUtils.isEmpty(str));{
                tv_leave_message.setEnabled(true);}
            }
        });

    }

    //    @Override
    public void initData() {

    }

    @Override
    protected void onResume() {
        //video_player.isPaused() &&
        if (video_player != null && videoPath != null) {
            video_player.setVideoPath(videoPath);
            //得到直播视频的网址，并启动
            video_player.start();
        }

        super.onResume();
    }

    //初始化弹出框
    private void initPopView() {


//        //初始化输入框输入的内容

        //TODO  数量按钮数量改变的监听
        number_button.setOnNumChangeListener(new MyNumberButton.OnNumChangeListener() {
            @Override
            public void onNumChange(View view, int num) {
                //TODO
                //控制按钮是否可以点击
                if (num == 0) {
                    iv_enure.setEnabled(false);
                } else {
                    iv_enure.setEnabled(true);
                }
            }
        });


//        int popHeight = popview.getHeight();
        if (popupWindow == null) {
            popupWindow = new PopupWindow(UiUtils.getContext());
            popupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
            popupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
            popupWindow.setOutsideTouchable(true);
            popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            // popupWindow.setHeight(popHeight);
            popupWindow.setContentView(popview);
            ColorDrawable dw = new ColorDrawable(0x00000000);
            popupWindow.setBackgroundDrawable(dw);
            //设置可以获得焦点
            popupWindow.setFocusable(true);
        }

        popupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        popupWindow.showAtLocation(FarmCameraActivity.this.findViewById(R.id.main), Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.tv_shop_car:
                //ToastUtil.showTextToast(UiUtils.getContext(), "购物车");
                LoginManger ma = new LoginManger(FarmCameraActivity.this);
                if (!ma.CheckLoginStatic()) {
                    ma.LoginCheck(ShopCarActivity.class, false);
                } else {
                    Intent intent1 = new Intent(this, ShopCarActivity.class);
                    startActivity(intent1);
                }

                break;
            case R.id.tv_buy_now:
                iv_enure.setTag(TAG_BUY_NOW);

                boolean iflog = sharedPreferences.getBoolean(ContentValues.IF_IS_LOGINED, false);
                if (iflog) {  //弹出对话框
                    initPopView();
                } else {
                    LoginManger manger = new LoginManger(FarmCameraActivity.this);
                    if (!manger.CheckLoginStatic()) {
                        manger.LoginCheck(FarmCameraActivity.class, false);
                    } else {
                        Intent intent2 = new Intent(this, FarmerActivity.class);
                        startActivity(intent2);
                    }

                    return;
                }
                break;
            case R.id.tv_put_in_shop_car:
                iv_enure.setTag(TAG_PUT_IN_SHOP_CAR);

                //TODO　将当前商品信息对象加入到购物车管理对象的列表中

                boolean iflogin = sharedPreferences.getBoolean(ContentValues.IF_IS_LOGINED, false);
                if (iflogin) {  //弹出对话框
                    initPopView();
                } else {
                    LoginManger manger = new LoginManger(FarmCameraActivity.this);
                    if (!manger.CheckLoginStatic()) {
                        manger.LoginCheck(FarmCameraActivity.class, false);
                    } else {
                        Intent intent3 = new Intent(this, FarmerActivity.class);
                        startActivity(intent3);
                    }
                    return;
                }


                break;
            case R.id.back:
                finish();
                break;
            case R.id.iv_search:
                break;
            case R.id.ll_collect:  //点击收藏

                boolean iflo = sharedPreferences.getBoolean(ContentValues.IF_IS_LOGINED, false);
                if (iflo) {
                    collect();
                } else {
                    LoginManger manger = new LoginManger(FarmCameraActivity.this);
                    if (!manger.CheckLoginStatic()) {
                        manger.LoginCheck(FarmCameraActivity.class, false);
                    }
                    return;
                }

                break;
            case R.id.iv_enure:
                //点击确定按钮
                if (TAG_BUY_NOW.equals(iv_enure.getTag())) {

                    //TODO  要携带购买数量的数据过去(带商品对象过去)
                    //TODO 根据商品数量和种类的多少选择进入单个支付页面还是多个支付的页面
                    if (goodJson != null) {
                        Intent intent2 = new Intent(UiUtils.getContext(), OrderDetailsActivity.class);
                        int count = number_button.getNumber();
                        intent2.putExtra(ContentValues.ORDER_PATH, submitPath);
                        intent2.putExtra(ContentValues.HOW_MANY_TO_BUY, count);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(ContentValues.GOODS_JSON, goodJson);
                        intent2.putExtras(bundle);
                        startActivity(intent2);
                    }
                } else if (TAG_PUT_IN_SHOP_CAR.equals(iv_enure.getTag())) {
                    //TODO 将当前商品的信息添加到购物车数据管理类里

                    addToshopCar(number_button.getNumber());

                }
                closePop();
                break;
            case R.id.iv_cancel_buy: //点击右上角叉号
                closePop();
                break;
            case R.id.alpha_view:  //点击popwindow上的空白区域
                closePop();

                break;
            case R.id.ll_video:
//                ToastUtil.showTextToast("点击了直播");

                if (videoPath == null) {
                    ToastUtil.showTextToast("没有视频播放资源，请稍后再试");
                    return;
                }
                if (!video_player.isPlaying()) {
                    ToastUtil.showTextToast("没有视频播放资源");
                    return;
                }
                if (ispressed) {
                    secondtime = System.currentTimeMillis();
                    if (secondtime - firsttime > 2000) {
                        ToastUtil.showTextToast("双击查看全屏直播");
                        ispressed = true;
                        firsttime = System.currentTimeMillis();
                    } else {
                        if (videoPath != null) {
                            video_player.pause();
                            Intent intent = new Intent(FarmCameraActivity.this, FullScreenVideoActivity.class);
                            intent.putExtra("media_type", palyType);
                            intent.putExtra("decode_type", deCodeer);
                            intent.putExtra("videoPath", videoPath);
                            startActivity(intent);

                        }
                        ispressed = false;
                        firsttime = 0;
                        secondtime = 0;
                    }
                } else {
                    ToastUtil.showTextToast("双击查看全屏直播");
                    ispressed = true;
                    firsttime = System.currentTimeMillis();
                }
                break;
            case R.id.tv_leave_message:   //点击留言
                String message = et_leave_message.getText().toString().trim();


                if (message == null || message.length() == 0) {
                    ToastUtil.showTextToast("请输入您的留言");
                    return;
                }
                boolean ifl = sharedPreferences.getBoolean(ContentValues.IF_IS_LOGINED, false);
                if (ifl) {
                    if (if_submit_useful) {
                        if_submit_useful=false;
                        tv_leave_message.setEnabled(if_submit_useful);
                        submitMessage(message);

                    }


                } else {
                    LoginManger manger = new LoginManger(FarmCameraActivity.this);
                    if (!manger.CheckLoginStatic()) {
                        manger.LoginCheck(FarmCameraActivity.class, false);
                    }
                    return;
                }
                break;

        }
    }


    private class MyHolder {
        ImageView iv_user_head;
        TextView tv_user_name;
        ImageView iv_user_degree;
        TextView tv_discuss_time;
        TextView tv_user_comment;
    }

    @Override
    public void onBackPressed() {
        video_player.release_resource();
        onDestroy();
        finish();
        closePop();
        super.onBackPressed();


    }

    @Override
    protected void onPause() {
//        video_player.release_resource();
        video_player.pause(); //锁屏时暂停
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        video_player.release_resource();
        super.onDestroy();
    }

    //隐藏输入法
    private void hiddenInput() {
        // 隐藏输入法
        InputMethodManager imm = (InputMethodManager) UiUtils.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        // 显示或者隐藏输入法
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }


    //隐藏popwindow
    private void closePop() {
        if (popupWindow != null) {
            popupWindow.dismiss();
            popupWindow = null;
        }
    }


    private void getNetData(int goodid, int Number) {
        String url = ContentValues.FARM_CAMERA_URL;
        String id = null;
        if (goodid == -1) {
            id = "";
        } else {
            id = String.valueOf(goodid);
        }
        String num = String.valueOf(Number);
        final Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("number", num);
        final String userid = sharedPreferences.getString(ContentValues.USER_ID, "");
        map.put("userId", userid);
        GsonRequest<CameraBean> cameraRequest = new GsonRequest<CameraBean>(map, url, CameraBean.class, new Response.Listener<CameraBean>() {
            @Override
            public void onResponse(CameraBean response) {
                if (response != null) {
                    int code = response.getCode();
                    if (code == 101) {
                        ToastUtil.showTextToast(response.getMessage());
                        return;
                    }
                    if (code == 200) {
                        //用于传递到支付页面用
                        CameraBean.ComIdformationBean ccb = response.getComIdformation();
                        if (ccb != null) {
                            goodJson = ccb;

                            rl_wait.setVisibility(View.GONE);
                            lv_users_appraise.setVisibility(View.VISIBLE);

                            String goodsName = ccb.getComName();
                            if ("土鸡蛋".equals(goodsName)) {
                                tv_price.setText(UiUtils.FormatMoneyStyle(String.valueOf(ccb.getComPrice())) + "(30枚)");
                            } else {
                                tv_price.setText(UiUtils.FormatMoneyStyle(String.valueOf(ccb.getComPrice())));
                            }
                            rating_bar.setRating((float) ccb.getStar());
                            Picasso.with(UiUtils.getContext()).load(ccb.getSmallPhoto()).into(iv_farm_pic); //牧场照片
                            tv_pop_farm_name.setText(ccb.getStoreName());
                            tv_goods_price.setText(UiUtils.FormatMoneyStyle(String.valueOf(ccb.getComPrice())));
                            tv_count_left.setText("(剩余" + ccb.getMaxCount() + ")");
                            tv_farm_name.setText(ccb.getStoreName());
                            if (ccb.getCollect() == 0) {
                                iv_if_collect.setImageResource(R.mipmap.shoucang);
                            } else {
                                iv_if_collect.setImageResource(R.mipmap.shoucang1);
                            }

                            tgv_farm_introduce.setText("   " + ccb.getSynopsis());
                            //视频路径
                            videoPath = ccb.getMonitor();
//                            videoPath = "http://vc7bd8aa.live.126.net/live/0c70e084aec84c348361ca59551e1480.flv";

                            if (videoPath == null) {
                                tv_tishi.setText("没有视频资源");
                                pb.setVisibility(View.INVISIBLE);

                            } else {
                                try {
                                    //设置播放地址，返回0正常，返回-1则说明地址非法，需要使用网易视频云官方生成的地址
                                    int error = mMediaPlayer.setDataSource(videoPath);
                                    if (error < 0) {
                                        tv_tishi.setText("资源错误");
                                        pb.setVisibility(View.INVISIBLE);
                                    } else {
                                        Message message = new Message();
                                        message.what = 1;
                                        Bundle bundle = new Bundle();
                                        bundle.putString("path", videoPath);
                                        message.setData(bundle);
                                        handler.sendMessage(message);
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }
                            int maxcount = ccb.getMaxCount();
                            number_button.setBuyMax(maxcount)   //设置可以购买的最大数量
                                    .setInventory(maxcount)      //设置库存大小
                                    .setCurrentNumber(number_button.getNumber())   //当前购买数量
                                    .setOnWarnListener(new MyNumberButton.OnWarnListener() {    //数量超出时的警报
                                        @Override
                                        public void onWarningForInventory(int inventory) {
                                            ToastUtil.showTextToast("购买数量超过库存量");
                                        }

                                        @Override
                                        public void onWarningForBuyMax(int max) {

                                        }
                                    });
                            //填充listview的数据源
                            final List<UserCommountBean> listbeans = new ArrayList<>();
                            List<CameraBean.ComIdformationBean.MessageBean> ccbms = ccb.getMessage();
                            if (ccbms != null && ccbms.size() > 0) {

                                UserCommountBean bean = null;
                                for (int j = 0; j < ccbms.size(); j++) {
                                    CameraBean.ComIdformationBean.MessageBean mb = ccbms.get(j);
                                    bean = new UserCommountBean();
                                    bean.user_id = mb.getUserId();
                                    bean.communt_time = mb.getLeaveDate();
                                    bean.user_communt = mb.getContent();
                                    bean.user_head = mb.getHead();

//                                    bean.user_level_img = mb.getComId();

                                    bean.user_name = mb.getUserName();
                                    listbeans.add(bean);
                                }
                                //给Listview设置适配器
                                lv_users_appraise.setAdapter(new MyDefaultAdapter<UserCommountBean>(listbeans) {
                                    @Override
                                    public View getAdapterView(int i, View view, ViewGroup viewGroup) {
                                        MyHolder holder = null;
                                        if (view == null) {
                                            holder = new MyHolder();
                                            view = UiUtils.inflate(R.layout.list_item_farm_camera);
                                            holder.iv_user_head = (ImageView) view.findViewById(R.id.iv_user_head);
                                            holder.tv_user_name = (TextView) view.findViewById(R.id.tv_user_name);
                                            holder.iv_user_degree = (ImageView) view.findViewById(R.id.iv_user_degree);
                                            holder.tv_discuss_time = (TextView) view.findViewById(R.id.tv_discuss_time);
                                            holder.tv_user_comment = (TextView) view.findViewById(R.id.tv_user_comment);
                                            view.setTag(holder);
                                        } else {
                                            holder = (MyHolder) view.getTag();
                                        }

//                                        holder.iv_user_head.setImageResource(listbeans.get(i).user_head);

                                        holder.tv_user_name.setText(listbeans.get(i).user_name);
                                        //TODO  根据用户等级切换不同等级图片
//                                        holder.iv_user_degree.setImageResource(listbeans.get(i).user_level_img);
                                        //TODO　用户头像
//                                        Picasso.with(UiUtils.getContext()).load(listbeans.get(i).user_head).into(holder.iv_user_head);
                                        holder.tv_discuss_time.setText(listbeans.get(i).communt_time);
                                        holder.tv_user_comment.setText(listbeans.get(i).user_communt);

                                        return view;
                                    }
                                });
                            } else {
                                final List<String> datas = new ArrayList<>();
                                datas.add("暂无留言");
                                lv_users_appraise.setAdapter(new MyDefaultAdapter<String>() {
                                    @Override
                                    public View getAdapterView(int i, View v, ViewGroup viewGroup) {
                                        Holder holder = null;
                                        if (v == null) {
                                            v = UiUtils.inflate(R.layout.simple_text);
                                            holder = new Holder();
                                            holder.tv = (TextView) v.findViewById(R.id.tv);
                                            v.setTag(holder);
                                        } else {
                                            holder = (Holder) v.getTag();
                                        }
                                        holder.tv.setText(datas.get(i));
                                        return v;
                                    }
                                });
                            }
                        }
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtil.showTextToast("网络连接异常");
            }
        });
        BaseApplication.getInstance().getRequestQueue().add(cameraRequest);
    }

    private class Holder {
        TextView tv;
    }

    private void collect() {
        String url = ContentValues.ADD_COLLECT_URL;
        Map<String, String> map = new HashMap<>();

        map.put("userId", userid);
        map.put("comId", String.valueOf(goodid));
        GsonRequest<CollectBean> collectRequest = new GsonRequest<CollectBean>(map, url, CollectBean.class, new Response.Listener<CollectBean>() {
            @Override
            public void onResponse(CollectBean response) {
                if (response != null) {
                    int code = response.getCode();
                    if (code == 200) {
                        ToastUtil.showTextToast(response.getMessage());
                        iv_if_collect.setImageResource(R.mipmap.shoucang1);
                    }
                    if (code == 300) {
                        ToastUtil.showTextToast(response.getMessage());
                        iv_if_collect.setImageResource(R.mipmap.shoucang);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtil.showTextToast("添加收藏联网操作失败");


            }
        });
        BaseApplication.getInstance().getRequestQueue().add(collectRequest);


    }

    private void addToshopCar(final int comNumber) {
        String url = ContentValues.ADD_TO_SHOP_CAR_URL;
        Map<String, String> map = new HashMap<>();
        map.put("userId", userid);
        map.put("comId", String.valueOf(goodid));
        map.put("comNumber", String.valueOf(comNumber));
        GsonRequest<CollectBean> addRequest = new GsonRequest<CollectBean>(map, url, CollectBean.class, new Response.Listener<CollectBean>() {
            @Override
            public void onResponse(CollectBean response) {
                if (response != null) {
                    int code = response.getCode();
                    if (code == 101) {
                        ToastUtil.showTextToast("添加失败");
                        return;
                    }
                    if (code == 200) {
                        ToastUtil.showTextToast("成功添加" + comNumber + "件商品到购物车");
                        //加入购物车成功后让输入框数据变为默认的1；
                        number_button.setCurrentNumber(1);
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtil.showTextToast("请求网络失败");
            }
        });

        BaseApplication.getInstance().getRequestQueue().add(addRequest);
    }

    private void submitMessage(final String message) {

            if (TextUtils.isEmpty(message)){
                ToastUtil.showTextToast("请输入留言内容");
                return;
            }


            if(!if_first_submit) {
                if (!TextUtils.isEmpty(mes)&&message.equals(mes)) {
                    ToastUtil.showTextToast("您已提交过该评论");
                    return;
                }
            }


        String url = ContentValues.LEAVE_MESSAGE_URL;
        Map<String, String> map = new HashMap<>();
        map.put("content", message);
        String userId = sharedPreferences.getString(ContentValues.USER_ID, "");
        map.put("userId", userId);
        map.put("comId", String.valueOf(goodid));
        GsonRequest<simpleNetBean> simple = new GsonRequest<simpleNetBean>(map, url, simpleNetBean.class, new Response.Listener<simpleNetBean>() {
            @Override
            public void onResponse(simpleNetBean response) {
                if (response != null) {
                    int code = response.getCode();
                    if (code == 101) {
                        ToastUtil.showTextToast("留言失败");
                        return;
                    }
                    if (code == 200) {
                        ToastUtil.showTextToast("留言成功");
                        et_leave_message.setText("");
                        if_submit_useful=true;
                        tv_leave_message.setEnabled(if_submit_useful);

                        //记录下第一次输入的内容
                        if (if_first_submit) {
                            mes = message;
                            if_first_submit = false;
                        }



                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtil.showTextToast("网络出现异常");
            }
        });
        BaseApplication.getInstance().getRequestQueue().add(simple);
    }

    private class CollectBean {

        /**
         * message : 添加收藏！
         * code : 200
         */
        private String message;
        private int code;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }
}
