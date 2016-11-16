package com.slr.slrapp.activitys;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.slr.slrapp.BaseApplication;
import com.slr.slrapp.R;
import com.slr.slrapp.beans.DefaultBean;
import com.slr.slrapp.beans.WithCashInfoBean;
import com.slr.slrapp.gson.ApiUtils;
import com.slr.slrapp.gson.GsonRequest;
import com.slr.slrapp.utils.ToastUtil;
import com.slr.slrapp.utils.UiUtils;
import com.squareup.picasso.Picasso;

/**
 * 提现
 * Created by admin on 2016/8/13.
 */
public class GetMoneyActivity extends BaseActivity{

    private TextView title;
    private LinearLayout back;
    private ImageView icon;
    private TextView payType;
    private TextView payNum;
    private TextView price;
    private EditText money;
    private Button getMoney;
    private Context context;
    private RequestQueue requestQueue;
    private BaseApplication baseApplication;
    private double totleMoney, withCash;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_getmoney;
    }

    @Override
    public void initView() {
        context = this;
        back = (LinearLayout) findViewById(R.id.title_left);
        title = (TextView) findViewById(R.id.title_text_tv);
        icon = (ImageView) findViewById(R.id.getmoney_icon);
        payType = (TextView) findViewById(R.id.getmoney_paytype);
        payNum = (TextView) findViewById(R.id.getmoney_paynum);
        price = (TextView) findViewById(R.id.getmoney_price);
        money = (EditText) findViewById(R.id.getmoney_edittext);
        getMoney = (Button) findViewById(R.id.getmoney_bt);
        money.addTextChangedListener(new EditChangedListener());

        requestQueue = Volley.newRequestQueue(context);
        GetNetData(5);
    }

    @Override
    public void initListener() {

        getMoney.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void initData() {

        title.setText("提现");
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.title_left:

                finish();

                break;

            case R.id.getmoney_bt:

                if (money.getText().toString().length()!=0&&money.getText().toString()!=null){
                    totleMoney = Double.parseDouble(price.getText().toString().substring(6,price.getText().toString().length()));
                    withCash = Double.parseDouble(money.getText().toString());
                    System.out.println("余额："+withCash);
                    if (withCash > totleMoney){

                        ToastUtil.showTextToast("提现金额超出可用余额，请重新输入！");

                    }else if (withCash <= 0 ){

                        ToastUtil.showTextToast("请输入正确的提现金额！");

                    }else{
                         WithCash(5, withCash);
                    }
                }else{
                    ToastUtil.showTextToast("请输入提现金额！");
                }

                break;

        }

    }

    // 提现
    private void WithCash(int userid, double price) {

        GsonRequest<DefaultBean> getData = new GsonRequest<DefaultBean>(
                ApiUtils.WithDraw(userid, price),
                DefaultBean.class,
                new Response.Listener<DefaultBean>() {
                    @Override
                    public void onResponse(DefaultBean response) {

                        ToastUtil.showTextToast(response.getMessage());
                        if (response.getCode()==200){
                            mfinish();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                ToastUtil.showTextToast("");
            }
        });
        BaseApplication.getInstance().getRequestQueue().add(getData);

    }

    // 获取用户余额信息
    private void GetNetData(int userid){

        GsonRequest<WithCashInfoBean> getData = new GsonRequest<WithCashInfoBean>(
                ApiUtils.getWithDraw(userid),
                WithCashInfoBean.class,
                new Response.Listener<WithCashInfoBean>() {
                    @Override
                    public void onResponse(WithCashInfoBean response) {

                        if (response.getCode() == 200){

                            price.setText("可用余额："+UiUtils.FormatMoneyStyle(response.getUser().getRemainMoney()));

                            payNum.setText(response.getUser().getBuyer_email());

                            if (response.getUser().getBuyType().equals("支付宝")){
                                payType.setText("支付宝账户");
                                Picasso.with(context).load(R.mipmap.pay_zfb).error(R.mipmap.ic_launcher).into(icon);
                            }
                        }else{
                            ToastUtil.showTextToast(response.getMessage());
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                ToastUtil.showTextToast("");
            }
        });
        BaseApplication.getInstance().getRequestQueue().add(getData);




    }

    class EditChangedListener implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            if (s.toString().contains(".")){

//当已经输入了一个小数点时判断接下来输入是否是float类型不是截取之前输入的
                try {
                    float money = Float.parseFloat(s.toString());
                } catch (NumberFormatException e) {
                    s = s.toString().subSequence(0,s.length()-1);
                    money.setText(s);
                    money.setSelection(s.length());
                }
            }
            if (s.toString().contains(".")) {

//保留后两位小数（3包含小数点和后两位数）
                if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                    s = s.toString().subSequence(0,
                            s.toString().indexOf(".") + 3);
                    money.setText(s);
                    money.setSelection(s.length());
                }
            }
            if (s.toString().trim().substring(0).equals(".")) {

//当输入是0.时正常输入
                s = "0" + s;
                money.setText(s);
                money.setSelection(2);
            }
            if (s.toString().startsWith("0") && s.toString().trim().length() > 1) {

//当输入01时取1
                if (!s.toString().substring(1, 2).equals(".")) {
                    money.setText(s.subSequence(1, 2));
                    money.setSelection(1);
                    return;
                }
            }

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }

    // 关闭本activity，刷新上级activity
    private void mfinish() {
        setResult(0);
        finish();
    }
}
