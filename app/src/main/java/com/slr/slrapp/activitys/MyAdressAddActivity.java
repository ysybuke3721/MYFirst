package com.slr.slrapp.activitys;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.slr.slrapp.BaseApplication;
import com.slr.slrapp.R;
import com.slr.slrapp.adapters.AdressAddAdapter;
import com.slr.slrapp.beans.MyAdressBean;
import com.slr.slrapp.gson.GsonRequest;
import com.slr.slrapp.utils.ContentValues;
import com.slr.slrapp.utils.ToastUtil;
import com.slr.slrapp.utils.UiUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * UserInfoBean: Administrator
 * Time: 2016/7/20 0020
 * Description: ${todo}(地址管理：添加或删除送货地址)
 * Version V1.0
 */
public class MyAdressAddActivity extends BaseActivity {

    private LinearLayout back;
    private Context context;
    private TextView title;
    private ListView lv;
    private AdressAddAdapter adapter;
    private View add_adress;
    private RelativeLayout adress_add_rl;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_adress;
    }

    @Override
    public void initView() {
        lv = (ListView) findViewById(R.id.adress_add_lv);
        back = (LinearLayout) findViewById(R.id.title_left);
        title = (TextView) findViewById(R.id.title_text_tv);
        add_adress = UiUtils.inflate(R.layout.foot_address_activity);
        adress_add_rl = (RelativeLayout) add_adress.findViewById(R.id.adress_add_rl);
        lv.addFooterView(add_adress);

    }

    @Override
    public void initListener() {
        back.setOnClickListener(this);
        adress_add_rl.setOnClickListener(this);
    }

    @Override
    public void initData() {
        context = this;
        title.setText("收货地址");
        loadData();
    }
    private void loadData() {
        String url= ContentValues.SEARCH_RECEIVE_AREA;
        String userid=context.getSharedPreferences(ContentValues.SP_NAME,0).getString(ContentValues.USER_ID,null);
        Map<String,String> map=new HashMap<>();
        map.put("userId",userid);
        GsonRequest<MyAdressBean> gsonRequest = new GsonRequest<MyAdressBean>(map,url, MyAdressBean.class,
                new Response.Listener<MyAdressBean>() {
                    @Override
                    public void onResponse(final MyAdressBean response) {
                        if (response.getCode() == 200) {
                            adapter = new AdressAddAdapter(context, response.getList());
                            lv.setAdapter(adapter);

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtil.showTextToast("网络请求失败！");
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
            case R.id.adress_add_rl:
                Intent intent = new Intent(this, AdressAddActivity.class);
                startActivityForResult(intent, 1000);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1001) {
            loadData();
        }
    }

    /**
     * Time: 2016/7/5 0005 下午 2:44
     * Description: ${todo}(界面跳转)
     * param: ${class}
     * return: ${return_type}
     */
    private void SkipActivity(Class c) {
        Intent intent = new Intent(context, c);
        startActivity(intent);
    }

}
