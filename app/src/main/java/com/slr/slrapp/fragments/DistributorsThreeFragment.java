package com.slr.slrapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.slr.slrapp.BaseApplication;
import com.slr.slrapp.R;
import com.slr.slrapp.adapters.MyDistributorsAdapter;
import com.slr.slrapp.beans.MyDistributorBean;
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
import java.util.HashMap;
import java.util.Map;

/**
 * User: Administrator
 * Time: 2016/7/19 0019
 * Description: ${todo}(用一句话描述该文件做什么)
 * Version V1.0
 */
public class DistributorsThreeFragment extends Fragment {

    private PullableListView listView;
    private MyDistributorsAdapter Adapter;
    private Context context;
    private boolean isFirstIn = true;
    private PullToRefreshLayout ptrl;
    private MyPullToFreshListener myPullToFreshListener;

    private RequestQueue requestQueue;
    private BaseApplication baseApplication;
    private MyDistributorBean myDistributorBean;
    private TextView time, distributorPrice;
    private int flag = 0;
    private mTimePickerView pvTime;
    // 用户id
    private String userId ;
    private String SelectDate = "";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_distributors_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);

    }

    /**
     * Time: 2016/7/7 0007 下午 4:35
     * Description: ${todo}(view初始化)
     * param: ${tags}
     * return: ${return_type}
     */
    private void initView(View view) {

        myDistributorBean = new MyDistributorBean();
        myPullToFreshListener = new MyPullToFreshListener();
        context = getActivity();
        userId=context.getSharedPreferences(ContentValues.SP_NAME, 0).getString(ContentValues.USER_ID, null);

        requestQueue = Volley.newRequestQueue(context);
        ptrl = ((PullToRefreshLayout) view.findViewById(R.id.distributors_refresh_view));
        ptrl.setOnRefreshListener(myPullToFreshListener);
        listView = (PullableListView) view.findViewById(R.id.distributors_content_view);
        time = (TextView) view.findViewById(R.id.my_distributors_time);
        distributorPrice = (TextView) view.findViewById(R.id.my_distributors_total);
        // 时间选择
        showTimeSelect();
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pvTime.show();
            }
        });

        GetNetData(flag);
        GetNetData(flag);

    }

    class MyPullToFreshListener implements PullToRefreshLayout.OnRefreshListener {

        @Override
        public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {

            flag = 0;

            // 下拉刷新操作
            new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    // 千万别忘了告诉控件刷新完毕了哦！
                    GetNetData(flag);
                    pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                }
            }.sendEmptyMessageDelayed(0, 2000);
        }

        @Override
        public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
            // 加载操作
            new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    GetNetData(flag);
                    // 千万别忘了告诉控件加载完毕了哦！
                    pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                }
            }.sendEmptyMessageDelayed(0, 2000);


        }
    }

    private void GetNetData(int n){

        Map<String, String> map = new HashMap<>();
        map.put("type", 3+"");
        map.put("startDate", SelectDate);
        map.put("num", n+"");
        map.put("userId", userId);
        GsonRequest<MyDistributorBean> gsonRequest = new GsonRequest<MyDistributorBean>(map,
                ApiUtils.getMyDistributor(),
                MyDistributorBean.class,
                new Response.Listener<MyDistributorBean>() {
                    @Override
                    public void onResponse(MyDistributorBean response) {

                        if (response.getCode() == 200){
                            flag =+ 10;
                            distributorPrice.setText("合计："+ UiUtils.FormatMoneyStyle(response.getPrice()+""));
                            Adapter = new MyDistributorsAdapter(context, response);
                            listView.setAdapter(Adapter);
                        }else{
                            ToastUtil.showTextToast(response.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                ToastUtil.showTextToast("网络异常！");
            }
        });
        BaseApplication.getInstance().getRequestQueue().add(gsonRequest);

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