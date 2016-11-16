package com.slr.slrapp.activitys;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.slr.slrapp.BaseApplication;
import com.slr.slrapp.R;
import com.slr.slrapp.adapters.NoticeLogisticesAdapter;
import com.slr.slrapp.adapters.OrderDefaultAdapter;
import com.slr.slrapp.beans.NoticeLogisticesBean;
import com.slr.slrapp.gson.ApiUtils;
import com.slr.slrapp.gson.GsonRequest;
import com.slr.slrapp.utils.ContentValues;
import com.slr.slrapp.utils.ToastUtil;
import com.slr.slrapp.widget.PullToRefreshLayout;
import com.slr.slrapp.widget.PullableListView;

import java.util.HashMap;
import java.util.Map;

/**
 * UserInfoBean: Administrator
 * Time: 2016/7/18 0018
 * Description: ${todo}(物流通知)
 * Version V1.0
 */
public class NoticeLogisticesActivity extends BaseActivity{
    private PullToRefreshLayout ptr;
    private PullableListView lv;
    private NoticeLogisticesAdapter adapter;
    private Context context;
    private LinearLayout back;
    private TextView title;
    private int flag = 0;
    // 用户id
    private String userId ;
    private boolean fresh = true;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_notice_logistices;
    }

    @Override
    public void initView() {
        ptr = (PullToRefreshLayout) findViewById(R.id.notice_logistices_ptr);
        lv = (PullableListView) findViewById(R.id.notice_logistices_lv);
        back = (LinearLayout) findViewById(R.id.title_left);
        title = (TextView) findViewById(R.id.title_text_tv);
    }

    @Override
    public void initListener() {
        back.setOnClickListener(this);
    }

    @Override
    public void initData() {
        context = this;
        userId=context.getSharedPreferences(ContentValues.SP_NAME, 0).getString(ContentValues.USER_ID, null);
        title.setText(R.string.logistics_message);
        MyPullToFreshListener myPullToFreshListener = new MyPullToFreshListener();
        ptr.setOnRefreshListener(myPullToFreshListener);
        adapter = new NoticeLogisticesAdapter(context);
        loadData(ApiUtils.LogisticsList());
    }

    class MyPullToFreshListener implements PullToRefreshLayout.OnRefreshListener {

        @Override
        public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
            flag = 0;
            fresh = true;
            loadData(ApiUtils.LogisticsList());
        }

        @Override
        public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
            fresh =false;
            loadData(ApiUtils.LogisticsList());
        }
    }

    private void loadData(String url) {
        System.out.println("物流通知列表："+ ApiUtils.APP_API +"/logisticsList?userId="+userId+"&num="+String.valueOf(flag));
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("num",String.valueOf(flag));
        GsonRequest<NoticeLogisticesBean> gsonRequest = new GsonRequest<NoticeLogisticesBean>(map, url, NoticeLogisticesBean.class,
                new Response.Listener<NoticeLogisticesBean>() {
                    @Override
                    public void onResponse(NoticeLogisticesBean response) {

                        System.out.println("物流消息返回："+response.getMessage()+response.getCode());
                        if (response.getCode() == 200) {
                            if (fresh) {
                                adapter.refreshItem(response.getList());
                                lv.setAdapter(adapter);
                                // 下拉刷新操作
                                ptr.refreshFinish(PullToRefreshLayout.SUCCEED);
                            } else {
                                adapter.addAllItem(response.getList());
                                lv.setSelection(adapter.getCount()-1);
                                // 加载操作
                                ptr.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                            }
                            flag = flag + 10;
                        } else {

                            if (flag==0&&response.getCode()!=200){
                                ToastUtil.showTextToast(response.getMessage());
                                OrderDefaultAdapter DefautAdapter = new OrderDefaultAdapter(context, 5, "您没有消息哦！");
                                lv.setAdapter(DefautAdapter);
                            }
                            if (fresh) {
                                ptr.refreshFinish(PullToRefreshLayout.SUCCEED);
                            } else {
                                ptr.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (fresh) {
                    ptr.refreshFinish(PullToRefreshLayout.FAIL);
                } else {
                    ptr.loadmoreFinish(PullToRefreshLayout.FAIL);
                }
            }
        });
        BaseApplication.getInstance().getRequestQueue().add(gsonRequest);
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
