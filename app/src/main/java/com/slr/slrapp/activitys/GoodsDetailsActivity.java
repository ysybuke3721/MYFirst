package com.slr.slrapp.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.slr.slrapp.BaseApplication;
import com.slr.slrapp.R;
import com.slr.slrapp.beans.Firstpage_list_bean;
import com.slr.slrapp.beans.GoodsInfoBean;
import com.slr.slrapp.gson.GsonRequest;
import com.slr.slrapp.utils.ContentValues;
import com.slr.slrapp.utils.ToastUtil;
import com.slr.slrapp.utils.UiUtils;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

/*
* 这是首页的二级页面  商品详情页面
*
* */
public class GoodsDetailsActivity extends BaseActivity {


    private ImageView back;
    private ImageView iv_more;
    private ImageView iv_big;
    private TextView tv_price;
    private RatingBar rating_bar;
    private TextView goods_details;
    private TextView tv_in_scroll;
    private TextView tv_see_details;
    private Firstpage_list_bean bean;
    private TextView goods_name;
    private RelativeLayout rl_wait;
    private ScrollView scrollView;
    private int goodid;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_first_page_details;
    }

    @Override
    public void initView() {

        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        //这是当前页面的数据源
        bean= (Firstpage_list_bean) bundle.getSerializable(ContentValues.FIRST_INTENT_BEAN_BUNDLE_NAME);
        if (bean!=null){
            goodid=bean.goodid;

        }


        scrollView= (ScrollView) findViewById(R.id.scrollView);
        rl_wait= (RelativeLayout) findViewById(R.id.rl_wait);
        goods_name= (TextView) findViewById(R.id.goods_name);
        back= (ImageView) findViewById(R.id.back);
        iv_more= (ImageView) findViewById(R.id.iv_search);
        iv_big= (ImageView) findViewById(R.id.iv_big);
        tv_price= (TextView) findViewById(R.id.tv_price);
        rating_bar= (RatingBar) findViewById(R.id.rating_bar);
        goods_details= (TextView) findViewById(R.id.goods_details);
        tv_in_scroll= (TextView) findViewById(R.id.tv_in_scroll);
        tv_see_details= (TextView) findViewById(R.id.tv_see_details);

        getNetData(goodid);

        back.setOnClickListener(this);
        iv_more.setOnClickListener(this);
        tv_see_details.setOnClickListener(this);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                //点击左上角返回按钮
                finish();
                break;
            case  R.id.iv_search:
                //点击右上角更多
                ToastUtil.showTextToast(UiUtils.getContext(),"更多");
                break;
            case R.id.tv_see_details:
                //点击查看更多
//                ToastUtil.showTextToast(UiUtils.getContext(),"立即查看");
                //打开养殖场监控页面
                Intent intent =new Intent(UiUtils.getContext(),FarmCameraActivity.class);
                intent.putExtra("goodsId",goodid);
                startActivity(intent);

                break;
        }
    }

    private void getNetData(int id){
        String url=ContentValues.GOODS_DETAIL_URL;
        Map<String,String> map=new HashMap<>();
        map.put("id", String.valueOf(id));
        GsonRequest<GoodsInfoBean> goodsRequest=new GsonRequest<GoodsInfoBean>(map, url, GoodsInfoBean.class, new Response.Listener<GoodsInfoBean>() {
            @Override
            public void onResponse(GoodsInfoBean response) {
                if (response!=null){
                    int code=response.getCode();
                    if (code==101){
                        ToastUtil.showTextToast("该商品已下架");
                        return;
                    }if (code==200){
                        GoodsInfoBean.ComBean comBean=response.getCom();
                        if (comBean!=null){
                            Picasso.with(UiUtils.getContext()).load(comBean.getPhoto()).into(iv_big);

                            tv_in_scroll.setText("     "+comBean.getDetails());
                            //星星数量
                            rating_bar.setRating(comBean.getWNumber());
                            String goodsName=comBean.getComName();
                            goods_name.setText(goodsName);
                            if ("土鸡蛋".equals(goodsName)){
                                tv_price.setText(UiUtils.FormatMoneyStyle(String.valueOf(comBean.getComPrice()))+"(30枚)");
                            }else {
                                tv_price.setText(UiUtils.FormatMoneyStyle(String.valueOf(comBean.getComPrice())));
                            }

                            //显示数据
                            rl_wait.setVisibility(View.GONE);
                            scrollView.setVisibility(View.VISIBLE);

                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                ToastUtil.showTextToast("网络请求异常");

            }
        });

        BaseApplication.getInstance().getRequestQueue().add(goodsRequest);
    }


}
