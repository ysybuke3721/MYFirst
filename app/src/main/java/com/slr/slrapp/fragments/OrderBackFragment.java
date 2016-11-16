package com.slr.slrapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.slr.slrapp.BaseApplication;
import com.slr.slrapp.R;
import com.slr.slrapp.adapters.OrderBackAdapter;
import com.slr.slrapp.adapters.OrderDefaultAdapter;
import com.slr.slrapp.beans.OrderBackBean;
import com.slr.slrapp.gson.ApiUtils;
import com.slr.slrapp.gson.GsonRequest;
import com.slr.slrapp.utils.ContentValues;
import com.slr.slrapp.utils.ToastUtil;
import com.slr.slrapp.widget.PullToRefreshLayout;
import com.slr.slrapp.widget.PullableListView;

/**
 * 退单列表
 * Created by shanyao on 2016/6/17.
 */
public class OrderBackFragment extends Fragment {

    private PullableListView listView;
    private OrderBackAdapter orderAdapter;
    private OrderDefaultAdapter DefautAdapter;
    private Context context;
    private boolean isFirstIn = true;
    private PullToRefreshLayout ptrl;
    private MyPullToFreshListener myPullToFreshListener;
    private OrderBackBean orderBean = null;
    // 用户id
    private String userId ;
    private int flag;
    private int flag1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_order_lv, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);

    }

    /**
     *
     * Time: 2016/7/7 0007 下午 4:35
     * Description: ${todo}(view初始化)
     * param: ${tags}
     * return: ${return_type}
     */
    private void initView(View view) {

        ptrl = ((PullToRefreshLayout) view.findViewById(R.id.refresh_view));
        myPullToFreshListener = new MyPullToFreshListener();
        ptrl.setOnRefreshListener(myPullToFreshListener);
        listView = (PullableListView) view.findViewById(R.id.content_view);
        context = getActivity();
        userId=context.getSharedPreferences(ContentValues.SP_NAME, 0).getString(ContentValues.USER_ID, null);
        flag = 0;
        // 网络请求获取订单数据
         orderBean = getNetData(ApiUtils.Refund(userId, flag));

    }

    class MyPullToFreshListener implements PullToRefreshLayout.OnRefreshListener {


        @Override
        public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
            flag = 0;
            // 网络请求获取订单数据
            orderBean = getNetData(ApiUtils.Refund(userId, flag));
            pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);

        }

        @Override
        public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
            flag1 = flag;
            flag = flag + 10;
            // 网络请求获取订单数据
            orderBean = getNetData(ApiUtils.Refund(userId, flag));
            // 千万别忘了告诉控件加载完毕了哦！
            pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);

        }
    }

    private OrderBackBean getNetData(String url) {
        System.out.println("退单列表接口："+url);
        GsonRequest<OrderBackBean> getData = new GsonRequest<OrderBackBean>(
                url,
                OrderBackBean.class,
                new Response.Listener<OrderBackBean>() {
                    @Override
                    public void onResponse(OrderBackBean response) {

                        orderBean = response;
                        if (orderBean.getCode()!=200){
                            if (flag == 0){


                                OrderDefaultAdapter DefautAdapter = new OrderDefaultAdapter(getActivity(), 0, "您没有订单哦！");
                                listView.setAdapter(DefautAdapter);
                            }
                            flag = flag1;
                        }else{

                            orderAdapter = new OrderBackAdapter(context, orderBean);
                            listView.setAdapter(orderAdapter);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                ToastUtil.showTextToast("网络异常");
            }
        });
        BaseApplication.getInstance().getRequestQueue().add(getData);

        return orderBean;

    }

}
