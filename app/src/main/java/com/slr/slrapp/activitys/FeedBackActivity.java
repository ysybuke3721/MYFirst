package com.slr.slrapp.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.slr.slrapp.BaseApplication;
import com.slr.slrapp.R;
import com.slr.slrapp.beans.DefaultBean;
import com.slr.slrapp.gson.ApiUtils;
import com.slr.slrapp.gson.GsonRequest;
import com.slr.slrapp.utils.ContentValues;
import com.slr.slrapp.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 提供用户的问题反馈
 * Created by admin on 2016/8/14.
 */
public class FeedBackActivity extends BaseActivity{

    private EditText et;
    private Button bt;
    private Context context;
    private LinearLayout back;
    private TextView title;
    private RequestQueue requestQueue;
    // 用户id
    private String userId ;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_feedback;
    }

    @Override
    public void initView() {

        back = (LinearLayout) findViewById(R.id.title_left);
        title = (TextView) findViewById(R.id.title_text_tv);
        et = (EditText) findViewById(R.id.feedback_et);
        bt = (Button) findViewById(R.id.feedback_bt);

    }

    @Override
    public void initListener() {

        bt.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void initData() {
        context = this;
        userId=context.getSharedPreferences(ContentValues.SP_NAME,0).getString(ContentValues.USER_ID,null);
        title.setText("反馈");
        requestQueue = Volley.newRequestQueue(context);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.title_left:

                finish();

                break;
            case R.id.feedback_bt:

                String content = et.getText().toString();

                if (content != null && content.length() != 0){

                    GtNetData(userId, content);

                }else {

                    ToastUtil.showTextToast("请描述您遇到的问题！");
                }

                break;
        }
    }

    private void GtNetData(String userId, String content){

        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("backContent", content);
        GsonRequest<DefaultBean> gsonRequest = new GsonRequest<>(map,
                ApiUtils.FeedBack(),
                DefaultBean.class,
                new Response.Listener<DefaultBean>() {
                    @Override
                    public void onResponse(DefaultBean session) {

                        ToastUtil.showTextToast(session.getMessage());

                        if (session.getCode()==200) {

                            Intent intent = new Intent();
                            intent.putExtra("flag",0);
                            ((Activity)context).setResult(0,intent);
                            ((Activity)context).finish();

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
}
