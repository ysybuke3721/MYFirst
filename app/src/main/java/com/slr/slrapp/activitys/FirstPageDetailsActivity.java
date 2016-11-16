package com.slr.slrapp.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.slr.slrapp.R;
import com.slr.slrapp.beans.Firstpage_list_bean;
import com.slr.slrapp.utils.ContentValues;
import com.slr.slrapp.utils.ToastUtil;
import com.slr.slrapp.utils.UiUtils;
import com.squareup.picasso.Picasso;

/*
* 这是首页的二级页面
*
* */
public class FirstPageDetailsActivity extends BaseActivity {


    private ImageView back;
    private ImageView iv_more;
    private ImageView iv_big;
    private TextView tv_price;
    private RatingBar rating_bar;
    private TextView goods_details;
    private TextView tv_in_scroll;
    private TextView tv_see_details;
    private Firstpage_list_bean bean;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_first_page_details;
    }

    @Override
    public void initView() {

        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        //这是当前页面的数据源
        bean= (Firstpage_list_bean) bundle.get(ContentValues.FIRST_INTENT_BEAN_BUNDLE_NAME);



        back= (ImageView) findViewById(R.id.back);
        iv_more= (ImageView) findViewById(R.id.iv_search);
        iv_big= (ImageView) findViewById(R.id.iv_big);
        tv_price= (TextView) findViewById(R.id.tv_price);
        rating_bar= (RatingBar) findViewById(R.id.rating_bar);
        goods_details= (TextView) findViewById(R.id.goods_details);
        tv_in_scroll= (TextView) findViewById(R.id.tv_in_scroll);
        tv_see_details= (TextView) findViewById(R.id.tv_see_details);

        back.setOnClickListener(this);
        iv_more.setOnClickListener(this);
        tv_see_details.setOnClickListener(this);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

//        iv_big.setImageResource(bean.imgUrl);
        Picasso.with(context).load(bean.imgUrl).into(iv_big);


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
                ToastUtil.showTextToast(UiUtils.getContext(),"立即查看");
                break;
        }



    }


}
