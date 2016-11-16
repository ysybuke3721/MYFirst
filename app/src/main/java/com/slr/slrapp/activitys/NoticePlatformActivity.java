package com.slr.slrapp.activitys;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.slr.slrapp.BaseApplication;
import com.slr.slrapp.R;
import com.slr.slrapp.adapters.NoticePlatformAdapter;
import com.slr.slrapp.adapters.OrderDefaultAdapter;
import com.slr.slrapp.beans.NoticePlatformBean;
import com.slr.slrapp.gson.ApiUtils;
import com.slr.slrapp.gson.GsonRequest;
import com.slr.slrapp.utils.ContentValues;
import com.slr.slrapp.utils.ToastUtil;
import com.slr.slrapp.widget.PullToRefreshLayout;
import com.slr.slrapp.widget.PullableListView;

/**
 * UserInfoBean: Administrator
 * Time: 2016/7/18 0018
 * Description: ${todo}(平台消息)
 * Version V1.0
 */
public class NoticePlatformActivity extends BaseActivity {
    private PullToRefreshLayout ptr;
    private PullableListView lv;
    private NoticePlatformAdapter adapter;
    private Context context;
    private LinearLayout back;
    private TextView title;
    private int flag = 0;
    private boolean fresh;
    // 用户id
    private String userId ;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_notice_platform;
    }

    @Override
    public void initView() {
        ptr = (PullToRefreshLayout) findViewById(R.id.notice_platform_ptr);
        lv = (PullableListView) findViewById(R.id.notice_platform_lv);
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
        title.setText(R.string.platform_message);
        MyPullToFreshListener myPullToFreshListener = new MyPullToFreshListener();
        ptr.setOnRefreshListener(myPullToFreshListener);
        adapter = new NoticePlatformAdapter(context);
        lv.setAdapter(adapter);
        loadData(ApiUtils.TransactionList());
    }

    class MyPullToFreshListener implements PullToRefreshLayout.OnRefreshListener {

        @Override
        public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
            flag = 0;
            fresh = true;
            loadData(ApiUtils.TransactionList());
        }

        @Override
        public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
            fresh = false;
            loadData(ApiUtils.TransactionList());
        }
    }

    private void loadData(String url) {

        System.out.println("我的平台消息；"+url+"?userId="+userId+"&num="+flag);
        GsonRequest<NoticePlatformBean> gsonRequest = new GsonRequest<NoticePlatformBean>(
                url+"?userId="+userId+"&num="+flag, NoticePlatformBean.class,
                new Response.Listener<NoticePlatformBean>() {
                    @Override
                    public void onResponse(NoticePlatformBean response) {
                        if (flag==0&&response.getCode()!=200){
                            ToastUtil.showTextToast(response.getMessage());
                            OrderDefaultAdapter DefautAdapter = new OrderDefaultAdapter(context, 5, "您没有消息哦！");
                            lv.setAdapter(DefautAdapter);
                        }

                        if (response.getCode() == 200) {
                            if (fresh) {
                                adapter.refreshItem(response.getList());
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

                            if (fresh) {
                                ptr.refreshFinish(PullToRefreshLayout.SUCCEED);
                            } else {
                                ptr.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                            }
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_left:
                finish();
                break;
        }
    }
}
