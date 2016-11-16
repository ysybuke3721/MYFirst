package com.slr.slrapp.activitys;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.slr.slrapp.BaseApplication;
import com.slr.slrapp.R;
import com.slr.slrapp.adapters.MyCollectionAdapter;
import com.slr.slrapp.adapters.OrderDefaultAdapter;
import com.slr.slrapp.beans.MyCollectBean;
import com.slr.slrapp.gson.ApiUtils;
import com.slr.slrapp.gson.GsonRequest;
import com.slr.slrapp.utils.ContentValues;
import com.slr.slrapp.utils.ToastUtil;
import com.slr.slrapp.widget.PullToRefreshLayout;
import com.slr.slrapp.widget.PullableListView;

/**
 * UserInfoBean: Administrator
 * Time: 2016/7/5 0005
 * Description: ${todo}(我的收藏界面：包括商品收藏和养殖场收藏)
 * Version V1.0
 */
public class MyCollectionActivity extends BaseActivity {

    private Context context;
    private PullToRefreshLayout ptr;
    private PullableListView lv;
    private MyCollectionAdapter adapter;
    private LinearLayout back;
    private TextView  title;
    private int flag = 0;
    private boolean fresh = true;
    // 用户id
    private String userId ;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_my_collection;
    }

    @Override
    public void initView() {
        ptr = (PullToRefreshLayout) findViewById(R.id.my_collection_ptr);
        lv = (PullableListView) findViewById(R.id.my_collection_lv);
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
        title.setText("我的收藏");
        MyPullToFreshListener myPullToFreshListener = new MyPullToFreshListener();
        ptr.setOnRefreshListener(myPullToFreshListener);
//        adapter = new MyCollectionAdapter(context);
//        lv.setAdapter(adapter);
        loadData(ApiUtils.SearchCollect(), ptr);
    }

    class MyPullToFreshListener implements PullToRefreshLayout.OnRefreshListener{

        @Override
        public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
            flag = 0;
            fresh = true;
            loadData(ApiUtils.SearchCollect(),ptr);
        }

        @Override
        public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
            fresh =false;
            loadData(ApiUtils.SearchCollect(), ptr);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.title_left:
                finish();
                break;
        }
    }

    private void loadData(String url, final PullToRefreshLayout ptr) {
        System.out.println("我的收藏；"+url+"?userId="+userId+"&number="+flag);
        GsonRequest<MyCollectBean> gsonRequest = new GsonRequest<MyCollectBean>(
                url+"?userId="+userId+"&number="+flag, MyCollectBean.class,
                new Response.Listener<MyCollectBean>() {
                    @Override
                    public void onResponse(MyCollectBean response) {
                        if (response.getCode() == 200) {
                            if (fresh) {
//                                adapter.refreshItem(response.getList());
                                adapter = new MyCollectionAdapter(context, response.getList());
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
                            if (fresh) {
                                ptr.refreshFinish(PullToRefreshLayout.SUCCEED);
                                ToastUtil.showTextToast(response.getMessage());
                                OrderDefaultAdapter DefautAdapter = new OrderDefaultAdapter(context, 1, "您没有收藏的内容哦！");
                                lv.setAdapter(DefautAdapter);
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
//        Map<String, String> map = new HashMap<>();
//        map.put("userId", userId);
//        map.put("number",String.valueOf(flag));
//        GsonRequest<>  = new GsonRequest<MyCollectBean>(map, url, MyCollectBean.class,
//                new Response.Listener<MyCollectBean>() {
//                    @Override
//                    public void onResponse(MyCollectBean response) {
//
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                if (fresh) {
//                    ptr.refreshFinish(PullToRefreshLayout.FAIL);
//                } else {
//                    ptr.loadmoreFinish(PullToRefreshLayout.FAIL);
//                }
//            }
//        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ptr.setVisibility(View.GONE);
    }
}
