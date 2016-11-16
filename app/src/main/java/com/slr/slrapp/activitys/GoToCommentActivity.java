package com.slr.slrapp.activitys;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
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
import com.slr.slrapp.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

/*我要评价页面*/
public class GoToCommentActivity extends BaseActivity {

    private ImageView back;
    private TextView tv_publish;
    private LinearLayout ll_commend_success;
    private EditText et_commend;
    private ImageView iv_no_name_commend;
    private LinearLayout ll_no_name_commend;
//    private RatingBar rb_meat;
    private RatingBar rb_describe;
    private RatingBar rb_deliver;
    private LinearLayout ll_commend;

    private boolean if_commend=true;
    private String orderId;
    private int n;
    private String userid;
    private RequestQueue requestQueue;
    private float s1, s2;
    private int flag;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_go_to_comment;
    }
    @Override
    public void initView() {
        back = (ImageView) findViewById(R.id.back);
        tv_publish = (TextView) findViewById(R.id.tv_publish);
        ll_commend_success = (LinearLayout) findViewById(R.id.ll_commend_success);
        et_commend = (EditText) findViewById(R.id.et_commend);
        iv_no_name_commend = (ImageView) findViewById(R.id.iv_no_name_commend);
        ll_no_name_commend = (LinearLayout) findViewById(R.id.ll_no_name_commend);

      //  rb_meat = (RatingBar) findViewById(R.id.rb_meat);
        rb_describe = (RatingBar) findViewById(R.id.rb_describe);
        rb_deliver = (RatingBar) findViewById(R.id.rb_deliver);

        ll_commend = (LinearLayout) findViewById(R.id.ll_commend);

        back.setOnClickListener(this);
        tv_publish.setOnClickListener(this);
        ll_no_name_commend.setOnClickListener(this);

    }

    @Override
    public void initListener() {

        //输入的监听
        et_commend.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String commend=editable.toString();
                if (TextUtils.isEmpty(commend)) {
                    ToastUtil.showTextToast("请在此填您对此次服务的评价，如您有什么问题，请拨打商务热线0371-5522-7158");
                    return;
                }
            }
        });







    }

    @Override
    public void initData() {

        orderId = getIntent().getExtras().getString("oid");
        userid = 5+"";
        requestQueue = Volley.newRequestQueue(context);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //是否匿名评价
            case  R.id.ll_no_name_commend:
                if (if_commend){
                    iv_no_name_commend.setImageResource(R.mipmap.assess_2);
                    n = 1;
                }else {
                    iv_no_name_commend.setImageResource(R.mipmap.assess_1);
                    n = 0;
                }
                if_commend=!if_commend;
                break;
            case R.id.tv_publish:
               // registerBoradcastReceiver();

                String commend=et_commend.getText().toString().trim();
             //   int xing1=rb_meat.getNumStars();
                s1 = rb_describe.getRating();
                s2 = rb_deliver.getRating();

                if (commend.length()==0||commend == null){
                    ToastUtil.showTextToast("请详述您的这次购物体验！");
                }else {
                    Comment(userid, Integer.parseInt(orderId), commend, s1, s2, n);
                }

                break;
            case R.id.back: //返回

                // 评价成功返回刷新界面

                Intent intent = new Intent();
                intent.putExtra("flag",flag);
                setResult(0, intent);
                finish();
                break;
        }

    }

    // 评价
    private void Comment(String userid, int oid, String message, float s1, float s2, int n){

        Map<String, String> map = new HashMap<>();
        map.put("userId", userid);
        map.put("oid", oid+"");
        map.put("message", message);
        map.put("s1", s1+"");
        map.put("s2", s2+"");
        map.put("anonymity", n+"");
        GsonRequest<DefaultBean> gsonRequest = new GsonRequest<>(map,
                ApiUtils.Comment(),
                DefaultBean.class,
                new Response.Listener<DefaultBean>() {
                    @Override
                    public void onResponse(DefaultBean session) {

                        ToastUtil.showTextToast(session.getMessage());

                        if (session.getCode()==200) {

                            flag = 1;
                            ll_commend_success.setVisibility(View.VISIBLE);
                            ll_commend.setVisibility(View.GONE);
                            tv_publish.setVisibility(View.INVISIBLE);

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

//    public void registerBoradcastReceiver(){
//        // 发送广播
//        Intent intent = new Intent();
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.setAction("com.slr.refresh");
//        intent.putExtra("REFRESH", "refresh");
//        context.sendBroadcast(intent);
//
//    }

}
