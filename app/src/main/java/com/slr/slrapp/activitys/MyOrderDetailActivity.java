package com.slr.slrapp.activitys;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.slr.slrapp.BaseApplication;
import com.slr.slrapp.R;
import com.slr.slrapp.adapters.MyOrderDetailAdapter;
import com.slr.slrapp.beans.OrderDetailBean;
import com.slr.slrapp.gson.GsonRequest;
import com.slr.slrapp.utils.ContentValues;
import com.slr.slrapp.utils.ToastUtil;

/**
 * Created by admin on 2016/8/11.
 */
public class MyOrderDetailActivity extends BaseActivity {


    private TextView title;
    private ToastUtil toastUtil;
    private LinearLayout back;
    private ListView listView;
    private Context context;
    private MyOrderDetailAdapter adapter;
    private OrderDetailBean orderDetail;
    private String orderiId;
    private int n;
    @Override
    public int getLayoutResId() {
        return R.layout.activity_myorder_detail;
    }

    @Override
    public void initView() {

        back = (LinearLayout) findViewById(R.id.title_left);
        title = (TextView) findViewById(R.id.title_text_tv);
        listView = (ListView) findViewById(R.id.my_order_detail_lv);

    }

    @Override
    public void initListener() {

        back.setOnClickListener(this);

    }

    @Override
    public void initData() {

        context = this;
        title.setText("订单详情");
        orderDetail = new OrderDetailBean();
        orderiId = getIntent().getExtras().getString("OrderDetail");
        getNetData(ContentValues.BASE_URL+"seachOrderInfor?orderId="+orderiId);

    }

    private OrderDetailBean getNetData(String url) {

        System.out.println("订单详情接口："+url);

        GsonRequest<OrderDetailBean> getData = new GsonRequest<OrderDetailBean>(
                url,
                OrderDetailBean.class,
                new Response.Listener<OrderDetailBean>() {
                    @Override
                    public void onResponse(OrderDetailBean response) {

                        orderDetail = response;
                        if (orderDetail.getCode()!=200){
                            ToastUtil.showTextToast("没有订单！");
                        }else{

                            adapter = new MyOrderDetailAdapter(context, orderDetail, orderiId, orderiId);
                            listView.setAdapter(adapter);

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                ToastUtil.showTextToast("网络异常");
            }
        });
        BaseApplication.getInstance().getRequestQueue().add(getData);

        return orderDetail;

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.title_left:

                finish();

                break;
        }
    }
}
