package com.slr.slrapp.activitys;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.slr.slrapp.BaseApplication;
import com.slr.slrapp.R;
import com.slr.slrapp.adapters.NoticeSystemAdapter;
import com.slr.slrapp.adapters.OrderDefaultAdapter;
import com.slr.slrapp.beans.NoticeSystemBean;
import com.slr.slrapp.gson.ApiUtils;
import com.slr.slrapp.gson.GsonRequest;
import com.slr.slrapp.utils.ToastUtil;
import com.slr.slrapp.widget.PullToRefreshLayout;
import com.slr.slrapp.widget.PullableListView;

import java.util.HashMap;
import java.util.Map;

/**
 * UserInfoBean: Administrator
 * Time: 2016/7/18 0018
 * Description: ${todo}(系统通知)
 * Version V1.0
 */
public class NoticeSystemActivity extends BaseActivity {

    private PullToRefreshLayout ptr;
    private PullableListView lv;
    private NoticeSystemAdapter adapter;
    private Context context;
    private LinearLayout back;
    private TextView title;
    private int flag = 0;
    private boolean fresh;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_notice_system;
    }

    @Override
    public void initView() {
        ptr= (PullToRefreshLayout) findViewById(R.id.notice_system_ptr);
        lv = (PullableListView) findViewById(R.id.notice_system_lv);
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
        title.setText(R.string.system_message);
        MyPullToFreshListener myPullToFreshListener = new MyPullToFreshListener();
        ptr.setOnRefreshListener(myPullToFreshListener);
        adapter = new NoticeSystemAdapter(context);
        lv.setAdapter(adapter);
        loadData(ApiUtils.NoticeList());
    }

    class MyPullToFreshListener implements PullToRefreshLayout.OnRefreshListener {

        @Override
        public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
            flag = 0;
            fresh = true;
            loadData(ApiUtils.NoticeList());
        }

        @Override
        public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
            fresh =false;
            loadData(ApiUtils.NoticeList());
        }
    }

    private void loadData(String url) {
        Map<String, String> map = new HashMap<>();
        map.put("number",String.valueOf(flag));
        GsonRequest<NoticeSystemBean> gsonRequest = new GsonRequest<NoticeSystemBean>(map, url, NoticeSystemBean.class,
                new Response.Listener<NoticeSystemBean>() {
                    @Override
                    public void onResponse(NoticeSystemBean response) {
                        if (response.getCode() == 200) {
                            if (flag==0&&response.getCode()!=200){
                                ToastUtil.showTextToast(response.getMessage());
                                OrderDefaultAdapter DefautAdapter = new OrderDefaultAdapter(context, 5, "您没有消息哦！");
                                lv.setAdapter(DefautAdapter);
                            }
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
