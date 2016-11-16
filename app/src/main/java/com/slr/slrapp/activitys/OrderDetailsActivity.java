package com.slr.slrapp.activitys;


import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
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
import com.slr.slrapp.beans.CameraBean;
import com.slr.slrapp.beans.DefaultAddressBean;
import com.slr.slrapp.beans.Firstpage_list_bean;
import com.slr.slrapp.beans.MyAdressBean;
import com.slr.slrapp.beans.SubmitOrderBean;
import com.slr.slrapp.gson.GsonRequest;
import com.slr.slrapp.utils.ContentValues;
import com.slr.slrapp.utils.LogUtils;
import com.slr.slrapp.utils.NetWorkUtils;
import com.slr.slrapp.utils.ToastUtil;
import com.slr.slrapp.utils.UiUtils;
import com.slr.slrapp.widget.MyNumberButton;
import com.slr.slrapp.widget.TimePickerView;
import com.squareup.picasso.Picasso;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*确认订单页面
* 购买单个商品
*
* */
public class OrderDetailsActivity extends BaseActivity {
    private ImageView back;
    private TextView tv_ensure_order;
    private ImageView iv_location;
    private TextView tv_receiver_info;
    private TextView tv_write_location;
    private ImageView iv_go_to;
    private LinearLayout ll_user_location;
    private ImageView iv_farm_logo;
    private TextView tv_farm_name;
    private ImageView iv_goods_pic;
    private TextView tv_goods_name;
    private TextView tv_seller_commend;
    private TextView tv_goods_price;
    private TextView tv_goods_count;
    private MyNumberButton number_button;
    //数量改变的按钮
    private LinearLayout ll_add_sub_btn;

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
    private LinearLayout ll_goods_details;

    private View alpha;
    //是否选择了地址的标记
    private boolean IF_CHOSE_LOCATION = false;
    //是否选择了配送方式
//    private boolean IF_CHOSE_DELIVER = false;
    //是否选择了收货时间
    private boolean IF_CHOSE_SEND_TIME = false;
    //是否选择支付方式
    private boolean IF_CHOSE_PAY_STYLE = false;

    //时间选择器
    private TimePickerView pvTime;
    //页面数据源
    private CameraBean.ComIdformationBean goodJson;

    SubmitOrderBean.ListBean orderbea = new SubmitOrderBean.ListBean();
    //提交订单路径，0 直接提交，1 购物车提交
    private int submitPath;
    private String orderJson;
    private int orderId;
    private double totalPrice;  //总价格
    private String payMode;
    private String orderDate;    //收货日期

    private String info;
    //支付弹窗
    private TextView tv_aliy_pay;
    private TextView tv_weixin_pay;

    private int maxNum;

    private Firstpage_list_bean deliverBean;

    @Override
    protected void onResume() {
        super.onResume();

        //初始化从外面传过来的商品数量
        //初始化从外面传过来的商品数量
        int num = getIntent().getIntExtra(ContentValues.HOW_MANY_TO_BUY, 1);

        if (number_button != null) {
            number_button.setCurrentNumber(num);
        }


    }


    @Override
    public int getLayoutResId() {
        return R.layout.activity_order_details;
    }

    @Override
    public void initView() {
        deliverBean = new Firstpage_list_bean();
        getDefaultArea();
        back = (ImageView) findViewById(R.id.back);
        ll_goods_details = (LinearLayout) findViewById(R.id.rl_goods_details);
        tv_ensure_order = (TextView) findViewById(R.id.tv_ensure_order);
        iv_location = (ImageView) findViewById(R.id.iv_location);
        tv_receiver_info = (TextView) findViewById(R.id.tv_receiver_info);

        tv_write_location = (TextView) findViewById(R.id.tv_write_location);
        iv_go_to = (ImageView) findViewById(R.id.iv_go_to);
        ll_user_location = (LinearLayout) findViewById(R.id.ll_user_location);
        iv_farm_logo = (ImageView) findViewById(R.id.iv_farm_logo);
        tv_farm_name = (TextView) findViewById(R.id.tv_farm_name);
        iv_goods_pic = (ImageView) findViewById(R.id.iv_goods_pic);
        tv_goods_name = (TextView) findViewById(R.id.tv_goods_name);
        tv_seller_commend = (TextView) findViewById(R.id.tv_seller_commend);
        tv_goods_price = (TextView) findViewById(R.id.tv_goods_price);
        tv_goods_count = (TextView) findViewById(R.id.tv_goods_count);
        tv_all_prices = (TextView) findViewById(R.id.tv_all_prices);
        number_button = (MyNumberButton) findViewById(R.id.number_button);
        tv_real_pay = (TextView) findViewById(R.id.tv_real_pay);

        Intent intent = getIntent();
        int num = intent.getIntExtra(ContentValues.HOW_MANY_TO_BUY, 1);
        number_button.setCurrentNumber(num);
        submitPath = intent.getIntExtra(ContentValues.ORDER_PATH, 0);
//        goodJson=intent.getStringExtra(ContentValues.GOODS_JSON);
        goodJson = (CameraBean.ComIdformationBean) intent.getExtras().getSerializable(ContentValues.GOODS_JSON);
        if (goodJson != null) {
            Picasso.with(context).load(goodJson.getSphoto()).into(iv_farm_logo);
            tv_farm_name.setText(goodJson.getStoreName());
            Picasso.with(context).load(goodJson.getSmallPhoto()).into(iv_goods_pic);
            tv_goods_name.setText(goodJson.getComName());
            tv_seller_commend.setText(goodJson.getPromise());
            tv_goods_price.setText(UiUtils.FormatMoneyStyle(String.valueOf(goodJson.getComPrice())));
            tv_goods_count.setText("x" + String.valueOf(num));
            tv_real_pay.setText(UiUtils.FormatMoneyStyle(String.valueOf(goodJson.getComPrice() * num)));
            tv_all_prices.setText(UiUtils.FormatMoneyStyle(String.valueOf(goodJson.getComPrice() * num)));
            totalPrice = goodJson.getComPrice() * num;   //支付的总价
            maxNum = goodJson.getMaxCount();

            deliverBean.imgUrl = goodJson.getPhoto();
            deliverBean.goodid = goodJson.getId();

            orderbea.setId(goodJson.getId());
            orderbea.setNum(num);

        }


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


        //时间选择器
        pvTime = new TimePickerView(OrderDetailsActivity.this, TimePickerView.Type.ALL);
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
                //是否选择时间
                IF_CHOSE_SEND_TIME = true;


                orderDate = date;

            }
        });


        back.setOnClickListener(this);
        ll_user_location.setOnClickListener(this);
        ll_goods_details.setOnClickListener(this);
        rl_delivery_style.setOnClickListener(this);
        rl_receive_time.setOnClickListener(this);
        rl_pay_style.setOnClickListener(this);
        tv_submit_order.setOnClickListener(this);
        number_button.setOnClickListener(this);


    }


    @Override
    public void initListener() {
        //留言框的输入监听
        et_leave_message.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String words = editable.toString();

            }
        });


        number_button.setBuyMax(maxNum)   //设置可以购买的最大数量
                .setInventory(maxNum)      //设置库存大小
                .setOnWarnListener(new MyNumberButton.OnWarnListener() {    //数量超出时的警报
                    @Override
                    public void onWarningForInventory(int inventory) {
                        ToastUtil.showTextToast("超过最大购买数量");
                    }

                    @Override
                    public void onWarningForBuyMax(int max) {

                    }
                });
        //数量按钮数量改变的监听
        number_button.setOnNumChangeListener(new MyNumberButton.OnNumChangeListener() {
            @Override
            public void onNumChange(View view, int num) {
                tv_goods_count.setText("x" + String.valueOf(num));
                tv_real_pay.setText(UiUtils.FormatMoneyStyle(String.valueOf(goodJson.getComPrice() * num)));
                tv_all_prices.setText(UiUtils.FormatMoneyStyle(String.valueOf(goodJson.getComPrice() * num)));

                totalPrice = goodJson.getComPrice() * num;   //支付的总价

                orderbea.setId(goodJson.getId());
                orderbea.setNum(num);

            }
        });


    }

    @Override
    public void initData() {

    }

    // 获取传递过来的收货地址
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ContentValues.SEND_A_RECEIVE_AREA) {
            MyAdressBean.ListBean bean = (MyAdressBean.ListBean) data.getExtras().getSerializable(ContentValues.KEY_OF_GET_AREA);
            tv_receiver_info.setText("收货人:" + bean.getReceiptName() + "     " + bean.getReceiptTel());
            orderId = bean.getId();  //收货地址的id
            tv_write_location.setText(bean.getReceiptAddress());
            tv_write_location.setVisibility(View.VISIBLE);
            tv_receiver_info.setVisibility(View.VISIBLE);
            //选择了地址
            IF_CHOSE_LOCATION = true;


        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:  //点击返回按钮
                finish();
                break;
            case R.id.ll_user_location: //收货地址
                Intent intent = new Intent(context, MyAdressAddActivity.class);
                intent.putExtra("order", 99);
                startActivityForResult(intent, ContentValues.GET_A_RECEIVE_AREA);
                break;
            case R.id.rl_goods_details:   //点击可查看商品详情
                //TODO　
               // ToastUtil.showTextToast(UiUtils.getContext(), "详情");

                break;
            case R.id.rl_delivery_style:   //快递方式

               // ToastUtil.showTextToast(UiUtils.getContext(), "快递方式");
                break;
            case R.id.rl_receive_time:
                // 快递时间
                // ToastUtil.showTextToast(UiUtils.getContext(), "快递时间");

                //弹出时间选择器
                pvTime.show();


                break;
            case R.id.rl_pay_style:    //支付方式
//                choseToPay();

                showPayWindow();

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
                //提交订单
                submitNetOrder(orderDate, 0, goodJson.getComName(), goodJson.getBrief(), "" + totalPrice);


                //TODO 传递支付金额过去
//                Intent inten = new Intent(UiUtils.getContext(), PaySuccessActivity.class);
//                startActivity(inten);

                //TODO  删除购物车数据(后期处理)
                //   ShopCarManagerBean.getIntence(context).subAllData();

                break;

            case R.id.tv_aliy_pay:   //点击支付宝支付
                payMode = "1";

                tv_pay_style.setTextColor(UiUtils.getContext().getResources().getColor(R.color.text2));
                tv_pay_style.setText(tv_aliy_pay.getText());
                IF_CHOSE_PAY_STYLE = true;
                closePop();
                break;
            case R.id.tv_weixin_pay:   //点击微信支付
                boolean ifhaswx=UiUtils.isWXAppInstalledAndSupported();
                if (ifhaswx){
                    payMode = "0";
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
        popupWindow.showAtLocation(OrderDetailsActivity.this.findViewById(R.id.main), Gravity.CENTER, 0, 0);

    }


    //隐藏popwindow
    private void closePop() {
        if (popupWindow != null) {
            popupWindow.dismiss();
            popupWindow = null;
        }
    }


    //设置时间格式
    public static String getTime(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
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
//        return format.format(da)+" "+ap;
        return da + " " + ap;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (pvTime.isShowing()) {
                pvTime.dismiss();
                return true;
            }
            closePop();
        }
        return super.onKeyDown(keyCode, event);
    }


    private void getDefaultArea() {
        String url = ContentValues.GET_DEFAULT_AREA_URL;
        String userid = context.getSharedPreferences(ContentValues.SP_NAME, 0).getString(ContentValues.USER_ID, null);

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

                            tv_receiver_info.setText("收货人:" + bean.getReceiptName() + "     " + bean.getReceiptTel());
                            tv_write_location.setText(bean.getReceiptAddress());
                            tv_write_location.setVisibility(View.VISIBLE);
                            tv_receiver_info.setVisibility(View.VISIBLE);
                            //选择了地址
                            IF_CHOSE_LOCATION = true;
                        }
                    }else {
                        ToastUtil.showTextToast(response.getMessage());
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


    private void submitNetOrder(final String receiptDate, double freightMoney, String subject, String body, String price) {
        String url = ContentValues.SUBMIT_ORDER_URL;
        String userid = context.getSharedPreferences(ContentValues.SP_NAME, 0).getString(ContentValues.USER_ID, null);
        int type = 0;  // 1购物车提交，0直接购买
        //paymode==1,微信支付，0支付宝支付

        //封装json
//        List<SubmitOrderBean.ListBean> list = new ArrayList<>();
//        list.add(orderbea);
//        SubmitOrderBean bea = new SubmitOrderBean();
//        bea.setList(list);
        Gson gson = new Gson();
        final String json1 = gson.toJson(orderbea, SubmitOrderBean.ListBean.class);
        final String json = "["+json1+"]";

        String userIp= NetWorkUtils.getLocalIpAddress(UiUtils.getContext());
        String message=et_leave_message.getText().toString().trim();
        final Map<String, String> map = new HashMap<>();
        map.put("json", json);
        map.put("userId", userid);
        map.put("sendMode","");
        map.put("receiptId",orderId+"");
        map.put("userIp", userIp);
        map.put("type", String.valueOf(type));
        map.put("receiptDate", receiptDate);
        map.put("freightMoney", String.valueOf(freightMoney));
        map.put("payMode", payMode);
        map.put("subject", subject);
        map.put("body", body);
        map.put("price", price);
        map.put("status","");
        map.put("messageWaiting",message);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        int code = jsonObject.getInt("code");
//                        System.out.println("code:"+code);
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
                                    AliPayUtil aliPayUtil = new AliPayUtil(payinfo, OrderDetailsActivity.this);
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
                                IWXAPI msgApi = WXAPIFactory.createWXAPI(OrderDetailsActivity.this, null);
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
        BaseApplication.getInstance().getRequestQueue().add(stringRequest);
    }

    private class orderBean {
        /**
         * payInfo：签名字符串
         * message : 保存订单失败！
         * code : 101
         */
        private String message;
        private int code;
        private String payInfo;
        private String payMode;

        public String getPayMode() {
            return payMode;
        }

        public void setPayMode(String payMode) {
            this.payMode = payMode;
        }

        public String getPayInfo() {
            return payInfo;
        }

        public void setPayInfo(String payInfo) {
            this.payInfo = payInfo;
        }

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
