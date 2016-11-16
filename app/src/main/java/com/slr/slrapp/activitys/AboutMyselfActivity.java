package com.slr.slrapp.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.slr.slrapp.R;
import com.slr.slrapp.gson.ApiUtils;

/**
 * 关于我们
 * Created by admin on 2016/8/10.
 */
public class AboutMyselfActivity extends BaseActivity{


    private TextView title;
    private Context context;
    private LinearLayout back;
    private ImageView iv;
    private String url = null;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_about_myself;
    }

    @Override
    public void initView() {
        back = (LinearLayout) findViewById(R.id.title_left);
        title = (TextView) findViewById(R.id.title_text_tv);
        iv = (ImageView) findViewById(R.id.about_myself_ewm);
    }

    @Override
    public void initListener() {
        back.setOnClickListener(this);
        iv.setOnClickListener(this);
    }

    @Override
    public void initData() {
        context = this;
        title.setText("关于我们");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.title_left:

                finish();

                break;
            case R.id.about_myself_ewm:

                Intent intent = new Intent(context, WebViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("WEBVIEW", ApiUtils.About());
                bundle.putString("TITLE", "关于我们");
                intent.putExtras(bundle);
                startActivity(intent);

                break;

        }
    }

}
