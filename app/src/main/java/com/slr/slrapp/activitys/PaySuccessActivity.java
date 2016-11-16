package com.slr.slrapp.activitys;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.slr.slrapp.R;
import com.slr.slrapp.utils.UiUtils;

public class PaySuccessActivity extends BaseActivity {

    private ImageView back;
    private TextView tv_pay_how_much;
    private TextView tv_back_to_home;



    @Override
    public int getLayoutResId() {
        return R.layout.activity_pay_success;
    }

    @Override
    public void initView() {
        back = (ImageView) findViewById(R.id.back);
        tv_pay_how_much = (TextView) findViewById(R.id.tv_pay_how_much);
        tv_back_to_home = (TextView) findViewById(R.id.tv_back_to_home);

        back.setOnClickListener(this);
        tv_back_to_home.setOnClickListener(this);

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
            case  R.id.back://点击返回
                //TODO 关闭已经打开的所有页面
                Intent intent=new Intent(UiUtils.getContext(),MainActivity.class);
                startActivity(intent);
                break;

            case R.id.tv_back_to_home://返回首页
                //TODO 关闭已经打开的所有页面
                Intent intent1=new Intent(UiUtils.getContext(),MainActivity.class);
                startActivity(intent1);
                break;




        }

    }

    @Override
    public void onBackPressed() {
        //TODO 关闭已经打开的所有页面
        Intent intent=new Intent(UiUtils.getContext(),MainActivity.class);
        startActivity(intent);;
    }
}
