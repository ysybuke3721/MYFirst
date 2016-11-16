package com.slr.slrapp.fragments;

import android.app.ProgressDialog;
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
import com.slr.slrapp.adapters.MyOrderAdapter;
import com.slr.slrapp.adapters.OrderDefaultAdapter;
import com.slr.slrapp.beans.MyOrderBean;
import com.slr.slrapp.gson.GsonRequest;
import com.slr.slrapp.utils.ContentValues;
import com.slr.slrapp.utils.ToastUtil;
import com.slr.slrapp.widget.PullToRefreshLayout;
import com.slr.slrapp.widget.PullableListView;

/**
 * 全部订单
 * Created by shanyao on 2016/6/17.
 */
public class OrderAllFragment extends Fragment {

    private PullableListView listView;
    private MyOrderAdapter orderAdapter;
   // private Context context;
    private boolean isFirstIn = true;
    private PullToRefreshLayout ptrl;
    private MyPullToFreshListener myPullToFreshListener;
    private MyOrderBean orderBean = null;
    // 用户id
    private String userId ;
    private int flag;
    private int flag1;
    private ProgressDialog dialog;

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
        dialog = BaseApplication.getProgressDialog(getActivity());
        dialog.setMessage(getString(R.string.loading));
        userId=getActivity().getSharedPreferences(ContentValues.SP_NAME, 0).getString(ContentValues.USER_ID, null);
        flag = 0;
        // 网络请求获取订单数据
        orderBean = getNetData(ContentValues.BASE_URL+"getOrder?userId="+userId+"&&number="+flag);

    }

    class MyPullToFreshListener implements PullToRefreshLayout.OnRefreshListener {


        @Override
        public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
            flag = 0;
            // 网络请求获取订单数据
            orderBean = getNetData(ContentValues.BASE_URL+"getOrder?userId="+userId+"&number="+flag);
            pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);

        }

        @Override
        public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
            flag1 = flag;
            flag = flag + 10;
            // 网络请求获取订单数据
            orderBean = getNetData(ContentValues.BASE_URL+"getOrder?userId="+userId+"&number="+flag);
            // 千万别忘了告诉控件加载完毕了哦！
            pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);


        }
    }

    private MyOrderBean getNetData(String url) {
        dialog.show();
        System.out.println("订单接口："+url);

        GsonRequest<MyOrderBean> getData = new GsonRequest<MyOrderBean>(
                url,
                MyOrderBean.class,
                new Response.Listener<MyOrderBean>() {
                    @Override
                    public void onResponse(MyOrderBean response) {

                        dialog.dismiss();
                        orderBean = response;
                        if (orderBean.getCode()!=200){
                            ToastUtil.showTextToast("没有订单！");
                            OrderDefaultAdapter DefautAdapter = new OrderDefaultAdapter(getActivity(), 0, "您没有订单哦！");
                            listView.setAdapter(DefautAdapter);
                            flag = flag1;
                        }else{
                            orderAdapter = new MyOrderAdapter(getActivity(), orderBean);
                            listView.setAdapter(orderAdapter);
                            orderAdapter.notifyDataSetChanged();

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                ToastUtil.showTextToast("网络异常");
                dialog.dismiss();
            }
        });
        BaseApplication.getInstance().getRequestQueue().add(getData);

        return orderBean;

    }

//    // 广播接收，刷新页面
//    public class MyBrandCast extends BroadcastReceiver {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String action = intent.getAction();
//            if (action.equals("com.slr.refresh")) {
//                String s=intent.getStringExtra("REFRESH");
//                if (s.equals("refresh")){
//
//                    n = 5;
//                }
//            }
//
//        }
//    }



}
