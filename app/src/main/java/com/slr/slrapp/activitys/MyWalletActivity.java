package com.slr.slrapp.activitys;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.slr.slrapp.BaseApplication;
import com.slr.slrapp.R;
import com.slr.slrapp.adapters.MyWalletAdapter;
import com.slr.slrapp.beans.MyWalletBean;
import com.slr.slrapp.gson.ApiUtils;
import com.slr.slrapp.gson.GsonRequest;
import com.slr.slrapp.utils.ContentValues;
import com.slr.slrapp.utils.ToastUtil;
import com.slr.slrapp.utils.UiUtils;
import com.slr.slrapp.widget.PullToRefreshLayout;
import com.slr.slrapp.widget.PullableListView;
import com.slr.slrapp.widget.mTimePickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * User: Administrator
 * Time: 2016/7/20 0020
 * Description: ${todo}(我的钱包余额)
 * Version V1.0
 */
public class MyWalletActivity extends BaseActivity {

    private Context context;
    private MyWalletAdapter adapter;
    private LinearLayout back;
    private TextView title, time, price, tv;
    private Button getmoney;
    private ToastUtil toastUtil;
    private int flag;
    private RelativeLayout wallet_null;
    private PullableListView listView;
    private PullToRefreshLayout ptrl;
    private MyPullToFreshListener myPullToFreshListener;
    private RequestQueue requestQueue;
    private BaseApplication baseApplication;
    private MyWalletBean myWalletBean;
    private int date;
    // 用户id
    private String userId ;
    private mTimePickerView pvTime;
    private String SelectDate = "";

    @Override
    public int getLayoutResId() {
        return R.layout.activity_my_wallet;
    }

    @Override
    public void initView() {
        context = this;
        back = (LinearLayout) findViewById(R.id.title_left);
        title = (TextView) findViewById(R.id.title_text_tv);
        time = (TextView) findViewById(R.id.my_wallet_time_select);
        wallet_null = (RelativeLayout) findViewById(R.id.my_wallet_null_layout);
        listView = (PullableListView) findViewById(R.id.my_wallet_content_view);
        ptrl = (PullToRefreshLayout) findViewById(R.id.my_wallet_refresh_view);
        price = (TextView) findViewById(R.id.my_wallet_money);
        getmoney = (Button) findViewById(R.id.my_wallet_getmoney_bt);
        tv = (TextView) findViewById(R.id.my_wallet_getmoney_tv);
        myPullToFreshListener = new MyPullToFreshListener();
        myWalletBean = new MyWalletBean();
    }

    @Override
    public void initListener() {
        back.setOnClickListener(this);
        time.setOnClickListener(this);

        getmoney.setOnClickListener(this);
    }

    @Override
    public void initData() {
        context = this;
        userId=context.getSharedPreferences(ContentValues.SP_NAME, 0).getString(ContentValues.USER_ID, null);
        title.setText(R.string.my_wallet);
        requestQueue = Volley.newRequestQueue(context);
        ptrl.setOnRefreshListener(myPullToFreshListener);
        // 获取系统时间
        long time=System.currentTimeMillis();
        final Calendar mCalendar=Calendar.getInstance();
        mCalendar.setTimeInMillis(time);
        date = mCalendar.get(Calendar.DATE);
        if (date != 28){
            getmoney.setClickable(false);
            getmoney.setBackgroundResource(R.drawable.shape_bt3);
            getmoney.setTextColor(Color.parseColor("#494949"));
        }else {
            getmoney.setClickable(true);
            getmoney.setBackgroundResource(R.drawable.shape_bt);
            getmoney.setTextColor(Color.parseColor("#fff"));
            tv.setVisibility(View.INVISIBLE);
        }
        // 时间选择
        showTimeSelect();

        GetNetData(flag);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.title_left:

                finish();

                break;
            case R.id.my_wallet_time_select:

                    pvTime.show();

                break;
            case R.id.my_wallet_getmoney_bt:
                if (date == 28){

                    String money = price.getText().toString();
                    if (!money.equals("￥0.00")){
                        Intent intent = new Intent(context, GetMoneyActivity.class);
                        startActivityForResult(intent, 0);
                    }else {
                        ToastUtil.showTextToast("没有余额呦！");
                    }

                }else {
                    ToastUtil.showTextToast("提现日期：每月28号！"+date);
                }
                break;
        }

    }

    class MyPullToFreshListener implements PullToRefreshLayout.OnRefreshListener {

        @Override
        public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {

            flag = 0;

            // 下拉刷新操作
            GetNetData(flag);
            // 千万别忘了告诉控件刷新完毕了哦！
            pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
        }

        @Override
        public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {

            // 加载操作
            GetNetData(flag);
            // 千万别忘了告诉控件加载完毕了哦！
            pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.FAIL);

        }
    }

    private void GetNetData(int n){

        GsonRequest<MyWalletBean> getData = new GsonRequest<MyWalletBean>(
                ApiUtils.getMyWallet(SelectDate, userId, n),
                MyWalletBean.class,
                new Response.Listener<MyWalletBean>() {
                    @Override
                    public void onResponse(MyWalletBean response) {

//                        System.out.println(response.getList().size()+"");
                        if (response.getCode() == 200){
                            if (response.getList().size()>0){

                                ptrl.setVisibility(View.VISIBLE);
                                wallet_null.setVisibility(View.INVISIBLE);
                                flag =+ 10;
                                myWalletBean = response;
                                price.setText(UiUtils.FormatMoneyStyle(myWalletBean.getPrice())+"");
                                MyWalletAdapter adapter = new MyWalletAdapter(context, response);
                                listView.setAdapter(adapter);
                            }else {
                                wallet_null.setVisibility(View.VISIBLE);
                                ptrl.setVisibility(View.INVISIBLE);
                            }

                        }else{
                            ToastUtil.showTextToast(response.getMessage());
                            wallet_null.setVisibility(View.VISIBLE);
                            ptrl.setVisibility(View.INVISIBLE);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                ToastUtil.showTextToast("网络异常");
            }
        });
        BaseApplication.getInstance().getRequestQueue().add(getData);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 0){

            flag = 0;
            GetNetData(flag);

        }

    }

    private void showTimeSelect() {

        //时间选择器
        pvTime = new mTimePickerView(context, mTimePickerView.mType.YEAR_MONTH_DAY);
        //控制时间范围
        Calendar calendar = Calendar.getInstance();
        pvTime.setRange(calendar.get(Calendar.YEAR) - 10, calendar.get(Calendar.YEAR));//要在setTime 之前才有效果哦
        pvTime.setTime(new Date());
        pvTime.setCyclic(true);
        pvTime.setCancelable(true);
        //设置标题
        pvTime.setTitle("起始时间选择");
        //时间选择后回调
        pvTime.setOnTimeSelectListener(new mTimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                SelectDate = getTime(date);
                time.setText(SelectDate + " - 至今");
                GetNetData(flag);
            }
        });

    }

    //设置时间格式
    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}
