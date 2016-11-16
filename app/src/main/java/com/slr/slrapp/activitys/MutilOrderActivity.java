package com.slr.slrapp.activitys;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.slr.slrapp.BaseApplication;
import com.slr.slrapp.R;
import com.slr.slrapp.alipay.AliPayUtil;
import com.slr.slrapp.beans.DefaultAddressBean;
import com.slr.slrapp.beans.MyAdressBean;
import com.slr.slrapp.beans.ShoppingCarListBean;
import com.slr.slrapp.beans.SubmitOrderBean;
import com.slr.slrapp.gson.GsonRequest;
import com.slr.slrapp.holders.BaseHolder;
import com.slr.slrapp.utils.ContentValues;
import com.slr.slrapp.utils.LogUtils;
import com.slr.slrapp.utils.NetWorkUtils;
import com.slr.slrapp.utils.PixUtil;
import com.slr.slrapp.utils.ToastUtil;
import com.slr.slrapp.utils.UiUtils;
import com.slr.slrapp.widget.TimePickerView;
import com.squareup.picasso.Picasso;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 多个订单结算页
 */
public class MutilOrderActivity extends BaseActivity {

    private LinearLayout ll_order_activity;

    private ImageView back;
    private TextView tv_ensure_order;
    private ImageView iv_location;
    private TextView tv_receiver_info;
    private TextView tv_write_location;
    private ImageView iv_go_to;
    private LinearLayout ll_user_location;
    private LinearLayout id_gallery;
    private HorizontalScrollView my_hor_scr_view;
    private TextView tv_how_many;
    private LinearLayout rl_goods_details;
    private ImageView iv_go;
    private TextView tv_delivery_style;
    private RelativeLayout rl_delivery_style;
    private ImageView iv_go_1;
    private TextView tv_receive_time;
    private RelativeLayout rl_receive_time;
    private ImageView iv_go_2;
    private TextView tv_pay_style;
    private RelativeLayout rl_pay_style;
    private TextView tv_all_prices;
    private TextView tv_yunfei;
    private EditText et_leave_message;
    private TextView tv_real_pay;
    private TextView tv_submit_order;
    private TimePickerView pvTime;

    //支付弹窗
    private TextView tv_aliy_pay;
    private TextView tv_weixin_pay;
    private View alpha;

    private List<ShoppingCarListBean> lists;

    private int allCount = 0;
    private double allPrice = 0;

    //是否选择了配送方式
    private boolean IF_CHOSE_DELIVER = false;
    //是否选择了收货时间
    private boolean IF_CHOSE_SEND_TIME = false;
    //是否选择支付方式
    private boolean IF_CHOSE_PAY_STYLE = false;
    //是否选择了地址的标记
    private boolean IF_CHOSE_LOCATION = false;
    //支付宝支付orderid
    private String aliyorderid;
    private String receiptDate; //收货日期

    private String subject;
    //收货地址的id
    private int orderId;
    private String paymode;

    private SubmitOrderBean submitOrderBean;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_mutil_order;
    }

    @Override
    public void initView() {
        getDefaultArea();
        //整个布局的父控件
        ll_order_activity = (LinearLayout) findViewById(R.id.ll_order_activity);

        back = (ImageView) findViewById(R.id.back);
        tv_ensure_order = (TextView) findViewById(R.id.tv_ensure_order);
        iv_location = (ImageView) findViewById(R.id.iv_location);
        tv_receiver_info = (TextView) findViewById(R.id.tv_receiver_info);
        tv_write_location = (TextView) findViewById(R.id.tv_write_location);
        iv_go_to = (ImageView) findViewById(R.id.iv_go_to);
        ll_user_location = (LinearLayout) findViewById(R.id.ll_user_location);
        id_gallery = (LinearLayout) findViewById(R.id.id_gallery);
        my_hor_scr_view = (HorizontalScrollView) findViewById(R.id.my_hor_scr_view);
        tv_how_many = (TextView) findViewById(R.id.tv_how_many);
        rl_goods_details = (LinearLayout) findViewById(R.id.rl_goods_details);
        iv_go = (ImageView) findViewById(R.id.iv_go);
        tv_delivery_style = (TextView) findViewById(R.id.tv_delivery_style);
        rl_delivery_style = (RelativeLayout) findViewById(R.id.rl_delivery_style);
        iv_go_1 = (ImageView) findViewById(R.id.iv_go_1);
        tv_receive_time = (TextView) findViewById(R.id.tv_receive_time);
        rl_receive_time = (RelativeLayout) findViewById(R.id.rl_receive_time);
        iv_go_2 = (ImageView) findViewById(R.id.iv_go_2);
        tv_pay_style = (TextView) findViewById(R.id.tv_pay_style);
        rl_pay_style = (RelativeLayout) findViewById(R.id.rl_pay_style);
        tv_all_prices = (TextView) findViewById(R.id.tv_all_prices);
        tv_yunfei = (TextView) findViewById(R.id.tv_yunfei);
        et_leave_message = (EditText) findViewById(R.id.et_leave_message);
        tv_real_pay = (TextView) findViewById(R.id.tv_real_pay);
        tv_submit_order = (TextView) findViewById(R.id.tv_submit_order);


        //获取传递过来的数据
        Intent intent = getIntent();
//        allCount=intent.getIntExtra("allCount",0);
        allPrice = intent.getDoubleExtra(ContentValues.TOTAL_PRICE, 0);
        lists = (List<ShoppingCarListBean>) intent.getExtras().getSerializable(ContentValues.TO_SUBMIT_ORDER);


        submitOrderBean = new SubmitOrderBean();
        if (lists.size() > 0) {
            List<SubmitOrderBean.ListBean> orders = new ArrayList<>();
            SubmitOrderBean.ListBean ord = null;
            ShoppingCarListBean bn = null;
            for (int i = 0; i < lists.size(); i++) {
                bn = lists.get(i);
                List<ShoppingCarListBean.Goods> goods = bn.getmGoods();
                if (goods.size() > 0) {
                    for (int j = 0; j < goods.size(); j++) {
                        ord = new SubmitOrderBean.ListBean();
                        ShoppingCarListBean.Goods good = goods.get(j);
                        ord.setId(Integer.parseInt(good.getGoodsId()));
                        ord.setNum(good.getGoodsNum());
                        orders.add(ord);
                    }
                }
            }
            submitOrderBean.setList(orders);   //TODO
        }


        //初始化商品数量

        tv_all_prices.setText("￥" + String.valueOf(allPrice));
        tv_real_pay.setText("￥" + String.valueOf(allPrice));

        //填充横向照片流的集合
        List<String> imageList = new ArrayList<>();

        if (lists.size() != 0) {
            for (ShoppingCarListBean shop : lists) {
                List<ShoppingCarListBean.Goods> goods = shop.getmGoods();
                if (goods.size() != 0) {
                    for (ShoppingCarListBean.Goods good : goods) {
                        imageList.add(good.getGoodsImgUrl());
                        allCount += good.getGoodsNum();
                    }
                }
            }
        }
        tv_how_many.setText("共" + allCount + "件");
//        //适配器
//        if (imageList.size() != 0) {
//            horiAdapter = new HorizontalScrollViewAdapter(context, imageList);
//        }


        //只展示三张图片
        if (imageList.size() >= 3) {
            for (int i = 0; i < 3; i++) {
                ImageView image = new ImageView(context);
                LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(PixUtil.dip2px(context, 150),PixUtil.dip2px(context, 150));
                image.setLayoutParams(params);
                Picasso.with(context).load(imageList.get(i)).into(image);
                image.setScaleType(ImageView.ScaleType.FIT_XY);
                image.setPadding(0, 0, PixUtil.dip2px(context, 10), 0);
                id_gallery.addView(image);

            }
        } else if (imageList.size() > 0 && imageList.size() <= 3) {
            for (int i = 0; i < imageList.size(); i++) {
                ImageView image = new ImageView(context);
                LinearLayout.LayoutParams param= new LinearLayout.LayoutParams(PixUtil.dip2px(context, 150),PixUtil.dip2px(context,150));
                image.setLayoutParams(param);
                image.setPadding(0, 0, PixUtil.dip2px(context, 10), 0);
                image.setScaleType(ImageView.ScaleType.FIT_XY);
                Picasso.with(context).load(imageList.get(i)).into(image);
                id_gallery.addView(image);
            }
        }


//        //给横向图片流设置适配器
//        if (horiAdapter != null) {
//            my_hor_scr_view.initDatas(horiAdapter);
//        }


        //时间选择器
        pvTime = new TimePickerView(MutilOrderActivity.this, TimePickerView.Type.ALL);
        //控制时间范围
        Calendar calendar = Calendar.getInstance();
        pvTime.setRange(calendar.get(Calendar.YEAR), calendar.get(Calendar.YEAR) + 5);//要在setTime 之前才有效果哦
        pvTime.setTime(new Date());
        pvTime.setCyclic(true);
        pvTime.setCancelable(true);
        //设置标题
        pvTime.setTitle(UiUtils.getContext().getResources().getString(R.string.receive_time));
        //时间选择后回调
        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(String date) {
                tv_receive_time.setText(getTime(date));
                IF_CHOSE_SEND_TIME = true;
                receiptDate = date;
            }
        });


        rl_goods_details.setOnClickListener(this);
        ll_user_location.setOnClickListener(this);
        back.setOnClickListener(this);
        rl_delivery_style.setOnClickListener(this);
        rl_receive_time.setOnClickListener(this);
        rl_pay_style.setOnClickListener(this);
        tv_submit_order.setOnClickListener(this);


    }

    @Override
    public void initListener() {
        //留言框监听
        et_leave_message.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //TODO
            }
        });


    }

    @Override
    public void initData() {


    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.back:  //点击返回按钮
                finish();
                break;
            case R.id.ll_user_location: //收货地址
                //TODO　
                Intent intent = new Intent(context, MyAdressAddActivity.class);
                startActivityForResult(intent, ContentValues.GET_A_RECEIVE_AREA);

                break;
            case R.id.rl_goods_details:   //点击可查看商品详情列表
                //TODO　
                Intent inte = new Intent(context, OrderGoodsListActivity.class);
                inte.putExtra(ContentValues.ALL_COUNT, allCount);
                Bundle bun = new Bundle();
                bun.putSerializable(ContentValues.TO_SEE_LIST, (Serializable) getAllGoods());
                inte.putExtras(bun);
                startActivity(inte);
                break;
            case R.id.rl_delivery_style:   //快递方式
                //TODO　
//                ToastUtil.showTextToast(UiUtils.getContext(), "快递方式");
                break;
            case R.id.rl_receive_time:
                //TODO　  快递时间
                //弹出时间选择器
                pvTime.show();
                break;
            case R.id.tv_submit_order: //提交订单
                if (!IF_CHOSE_LOCATION) {
                    ToastUtil.showTextToast("请选择收货地址");
                    return;
                }
                if (!IF_CHOSE_SEND_TIME) {
                    ToastUtil.showTextToast("请选择收货时间");
                    return;
                }
                if (!IF_CHOSE_PAY_STYLE) {
                    ToastUtil.showTextToast("请选择支付方式");
                    return;
                }

                //paymode==1,微信支付，0支付宝支付
                submitNetOrder("0", paymode,"多订单合并支付  （共"+allCount+"件）",""+allPrice);
//                //TODO 传递支付金额过去
//                Intent inten = new Intent(UiUtils.getContext(), PaySuccessActivity.class);
//                startActivity(inten);
                //TODO 删除购物车数据
//                for (int i = 0; i < lists.size(); i++) {
//                    ShopCarManagerBean.getIntence(context).subAllData();
//                }

                break;

            case R.id.rl_pay_style:
//                ToastUtil.showTextToast("支付方式");
//                chosePayStyle();
                showPayWindow();
                break;
            case R.id.tv_aliy_pay:   //点击支付宝支付
                paymode="1";
                tv_pay_style.setTextColor(UiUtils.getContext().getResources().getColor(R.color.text2));
                tv_pay_style.setText(tv_aliy_pay.getText());
                IF_CHOSE_PAY_STYLE = true;
                closePop();
                break;
            case R.id.tv_weixin_pay:   //点击微信支付

                boolean ifhaswx=UiUtils.isWXAppInstalledAndSupported();
                if (ifhaswx){
                    paymode="0";
                    tv_pay_style.setTextColor(UiUtils.getContext().getResources().getColor(R.color.text2));
                    tv_pay_style.setText(tv_weixin_pay.getText());
                    IF_CHOSE_PAY_STYLE = true;
                }else {
                    ToastUtil.showTextToast("您的手机没有安装微信，请安装后重试");
                    return;
                }
                closePop();
                break;
            case R.id.alpha:  //点击支付弹窗的透明位置
                closePop();
                break;

        }
    }


    //获取传递过来的收货地址
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ContentValues.SEND_A_RECEIVE_AREA) {
            MyAdressBean.ListBean bean = (MyAdressBean.ListBean) data.getExtras().getSerializable(ContentValues.KEY_OF_GET_AREA);
            tv_receiver_info.setText("收货人:" + bean.getReceiptName() + "     " + bean.getReceiptTel());
            orderId = bean.getId();   //收货地址id
            tv_write_location.setText(bean.getReceiptAddress());
            tv_write_location.setVisibility(View.VISIBLE);
            tv_receiver_info.setVisibility(View.VISIBLE);
            //选择了地址
            IF_CHOSE_LOCATION = true;
        }
    }
    //设置时间格式
    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    //设置时间格式
    public static String getTime(String date) {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String[] array = date.split(" ");
        String da = array[0];
        String ap = array[1];
        String[] array1 = da.split("-");
        String year = array1[0];
        String mon = array1[1];
        LogUtils.LogI("mon1", mon);
        if (mon.length() < 2) {
            mon = "0" + mon;
        }
        LogUtils.LogI("mon2", mon);
        String day = array1[2];
        LogUtils.LogI("mon3", day);
        if (day.length() < 2) {
            day = "0" + day;
        }
        da = year + "-" + mon + "-" + day;
        return da + " " + ap;
    }


    private PopupWindow popupWindow;

    private void showPayWindow() {
        //初始化支付弹窗
        View payWindow = UiUtils.inflate(R.layout.pop_pay_style);
        tv_aliy_pay = (TextView) payWindow.findViewById(R.id.tv_aliy_pay);
        tv_weixin_pay = (TextView) payWindow.findViewById(R.id.tv_weixin_pay);
        alpha = payWindow.findViewById(R.id.alpha);

        tv_aliy_pay.setOnClickListener(this);
        tv_weixin_pay.setOnClickListener(this);
        alpha.setOnClickListener(this);


        if (popupWindow == null) {
            popupWindow = new PopupWindow(UiUtils.getContext());
            popupWindow.setWidth(FrameLayout.LayoutParams.MATCH_PARENT);
            popupWindow.setHeight(FrameLayout.LayoutParams.WRAP_CONTENT);
            popupWindow.setOutsideTouchable(true);
            // popupWindow.setHeight(popHeight);
            popupWindow.setContentView(payWindow);
            ColorDrawable dw = new ColorDrawable(0x00000000);
            popupWindow.setBackgroundDrawable(dw);
            //设置可以获得焦点
            popupWindow.setFocusable(true);
        }

        popupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        popupWindow.showAtLocation(MutilOrderActivity.this.findViewById(R.id.ll_order_activity), Gravity.CENTER, 0, 0);

    }


    //隐藏popwindow
    private void closePop() {
        if (popupWindow != null) {
            popupWindow.dismiss();
            popupWindow = null;
        }
    }


    //填充支付方式的holder
    private class Selector2Holder extends BaseHolder<String> {
        TextView tv;

        @Override
        protected View initView() {
            View view = UiUtils.inflate(R.layout.simple_text);
            tv = (TextView) view.findViewById(R.id.tv);
            return view;
        }

        @Override
        protected void refreshView(String s) {
            tv.setText(s);
        }
    }


    //得到所有商品
    private List<ShoppingCarListBean.Goods> getAllGoods() {
        List<ShoppingCarListBean.Goods> goods = new ArrayList<>();
        if (lists.size() != 0) {
            for (ShoppingCarListBean bean : lists) {
                goods.addAll(bean.getmGoods());
            }
        }

        return goods;
    }

    private void getDefaultArea() {
        String url = ContentValues.GET_DEFAULT_AREA_URL;
        String userid = sharedPreferences.getString(ContentValues.USER_ID, "");
        Map<String, String> map = new HashMap<>();
        map.put("userId", userid);
        GsonRequest<DefaultAddressBean> defaultrequest = new GsonRequest<DefaultAddressBean>(map, url, DefaultAddressBean.class, new Response.Listener<DefaultAddressBean>() {
            @Override
            public void onResponse(DefaultAddressBean response) {
                if (response != null) {
                    int code = response.getCode();
                    if (code == 101) {

                        return;
                    }
                    if (code == 200) {
                        DefaultAddressBean.AddressBean bean = response.getAddress();
                        if (bean != null) {
                            orderId = bean.getId();   //收货地址id
                            tv_receiver_info.setText("收货人:" + bean.getReceiptName() + " " + bean.getReceiptTel());
                            tv_write_location.setText(bean.getReceiptAddress());
                            tv_write_location.setVisibility(View.VISIBLE);
                            tv_receiver_info.setVisibility(View.VISIBLE);
                            //选择了地址
                            IF_CHOSE_LOCATION = true;
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtil.showTextToast("获取收货地址失败");
            }
        });
        BaseApplication.getInstance().getRequestQueue().add(defaultrequest);
    }

    private void submitNetOrder(String freightMoney, final String payMode, String subject, String body) {
        try {
        String url = ContentValues.SUBMIT_ORDER_URL;
        String userid = context.getSharedPreferences(ContentValues.SP_NAME, 0).getString(ContentValues.USER_ID, null);

        int type = 1;  // 1购物车提交，0直接购买
        //paymode==1,微信支付，0支付宝支付
        //封装json
        Gson gson = new Gson();
        String json1 = gson.toJson(submitOrderBean,SubmitOrderBean.class);

            JSONObject jsonObject = new JSONObject(json1);

        String json=jsonObject.optString("list");

            LogUtils.LogI("json",json);

        String userIp= NetWorkUtils.getLocalIpAddress(UiUtils.getContext());
        String message=et_leave_message.getText().toString().trim();
        final Map<String, String> map = new HashMap<>();

        map.put("json", json);
        map.put("userId", userid);
        map.put("receiptId", String.valueOf(orderId));
        map.put("type", String.valueOf(type));
        map.put("receiptDate", receiptDate);
        map.put("freightMoney", freightMoney);
        map.put("payMode", payMode);
        map.put("subject", subject);
        map.put("status","");
        map.put("body", body);
        map.put("sendMode","");
        map.put("price", String.valueOf(allPrice));
        map.put("userIp", userIp);
        map.put("messageWaiting",message);


        StringRequest stringReques = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        int code = jsonObject.getInt("code");
                        String message = jsonObject.getString("message");
                        if (code == 101) {
                            ToastUtil.showTextToast(message);
                            return;
                        }
                        String paymode = jsonObject.getString("payMode");
                        ToastUtil.showTextToast(message);
                        if (code == 200) {
                            //支付宝
                            if ("1".equals(paymode)) {
                                //确定是支付宝支付
                                String payinfo = jsonObject.getString("payInfo");
                                if (payinfo != null) {
                                    AliPayUtil aliPayUtil = new AliPayUtil(payinfo, MutilOrderActivity.this);
                                    aliPayUtil.pay();
                                }
                            }
                        } else if (code == 300) { //微信支付
                            if ("0".equals(paymode)) {
                                String timestamp = jsonObject.getString("timestamp");
                                String partnerid = jsonObject.getString("partnerid");
                                String signs = jsonObject.getString("signs");
                                String prepay_id = jsonObject.getString("prepay_id");
                                String packagex = jsonObject.getString("package");
                                String appid = jsonObject.getString("appid");
                                String nonce_str = jsonObject.getString("nonce_str");
                                //商户APP工程中引入微信JAR包，调用API前，需要先向微信注册您的APPID，
                                IWXAPI msgApi = WXAPIFactory.createWXAPI(MutilOrderActivity.this, null);
                                // 将该app注册到微信
                                msgApi.registerApp(appid);
                                PayReq request = new PayReq();
                                request.appId = appid;
                                request.partnerId = partnerid;
                                request.prepayId = prepay_id;
                                request.packageValue = packagex;
                                request.nonceStr = nonce_str;
                                request.sign = signs;
                                request.timeStamp = timestamp;
                                msgApi.sendReq(request);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtil.showTextToast("网络请求异常");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }
        };
        BaseApplication.getInstance().getRequestQueue().add(stringReques);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
