package com.slr.slrapp.activitys;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.slr.slrapp.BaseApplication;
import com.slr.slrapp.R;
import com.slr.slrapp.adapters.MyEvalutaionsAdapter;
import com.slr.slrapp.adapters.OrderDefaultAdapter;
import com.slr.slrapp.beans.MyEvalutaionsBean;
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
 * Time: 2016/7/5 0005
 * Description: ${todo}(我的评价)
 * Version V1.0
 */
public class MyEvalutaionsActivity extends BaseActivity {

    private PullableListView lv;
    private MyEvalutaionsAdapter adapter;
    private Context context;
    private PullToRefreshLayout ptrl;
    private MyPullToFreshListener myPullToFreshListener;
    private LinearLayout back;
    private TextView title;
    private int flag = 0;
    private boolean fresh = true;
    // 用户id
    private String userId ;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_my_evalutaions;
    }

    @Override
    public void initView() {
        lv = (PullableListView) findViewById(R.id.my_evaluation_lv);
        back = (LinearLayout) findViewById(R.id.title_left);
        title = (TextView) findViewById(R.id.title_text_tv);
        ptrl = ((PullToRefreshLayout) findViewById(R.id.my_evaluation_refresh_view));
    }

    @Override
    public void initListener() {
        back.setOnClickListener(this);

    }

    @Override
    public void initData() {
        context = this;
        userId=context.getSharedPreferences(ContentValues.SP_NAME,0).getString(ContentValues.USER_ID,null);
        title.setText(R.string.my_evaluation);
        myPullToFreshListener = new MyPullToFreshListener();
        ptrl.setOnRefreshListener(myPullToFreshListener);
        loadData(ApiUtils.MyEvaluate());
    }

    class MyPullToFreshListener implements PullToRefreshLayout.OnRefreshListener {

        @Override
        public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
            flag = 0;
            fresh = true;
            loadData(ApiUtils.MyEvaluate());
            ptrl.refreshFinish(PullToRefreshLayout.SUCCEED);
        }

        @Override
        public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
            fresh =false;
            loadData(ApiUtils.MyEvaluate());
            ptrl.loadmoreFinish(PullToRefreshLayout.SUCCEED);
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

    private void loadData(String url) {
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("number",String.valueOf(flag));
        final GsonRequest<MyEvalutaionsBean> gsonRequest = new GsonRequest<MyEvalutaionsBean>(map, url, MyEvalutaionsBean.class,
                new Response.Listener<MyEvalutaionsBean>() {
                    @Override
                    public void onResponse(MyEvalutaionsBean response) {
                        if (response.getCode() == 200) {
                            if (fresh) {
                                flag = flag + 10;
                                adapter = new MyEvalutaionsAdapter(context, response);
                                lv.setAdapter(adapter);
//                                adapter.refreshItem(response.getList());
//                                // 下拉刷新操作
//                                ptrl.refreshFinish(PullToRefreshLayout.SUCCEED);
                            } else {
                                adapter.addAllItem(response.getList());
                                lv.setSelection(adapter.getCount()-1);
                                // 加载操作
                                ptrl.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                            }

                        } else {
                            ToastUtil.showTextToast(response.getMessage());
                            if (fresh) {
                                ptrl.refreshFinish(PullToRefreshLayout.SUCCEED);
                                OrderDefaultAdapter DefautAdapter = new OrderDefaultAdapter(context, 4, "您没有评论的内容哦！");
                                lv.setAdapter(DefautAdapter);
                            } else {
                                ptrl.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (fresh) {
                    ptrl.refreshFinish(PullToRefreshLayout.FAIL);
                } else {
                    ptrl.loadmoreFinish(PullToRefreshLayout.FAIL);
                }
            }
        });
        BaseApplication.getInstance().getRequestQueue().add(gsonRequest);
    }

}
