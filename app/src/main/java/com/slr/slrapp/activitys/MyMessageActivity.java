package com.slr.slrapp.activitys;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.slr.slrapp.R;
import com.slr.slrapp.managers.LoginManger;

/**
 * UserInfoBean: Administrator
 * Time: 2016/7/5 0005
 * Description: ${todo}(消息中心)
 * Version V1.0
 */
public class MyMessageActivity extends BaseActivity {

    private RelativeLayout mMessage_system, mMessage_platform,mMessage_logistics;
    private Context context;
    private LinearLayout back;
    private TextView title;
    private LoginManger loginManger;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_mymessage;
    }

    @Override
    public void initView() {
        mMessage_system = (RelativeLayout) findViewById(R.id.message_system);
        mMessage_platform = (RelativeLayout) findViewById(R.id.message_platform);
        mMessage_logistics = (RelativeLayout) findViewById(R.id.message_logistics);
        back = (LinearLayout) findViewById(R.id.title_left);
        title = (TextView) findViewById(R.id.title_text_tv);
    }


    @Override
    public void initListener() {

        mMessage_system.setOnClickListener(this);
        mMessage_platform.setOnClickListener(this);
        mMessage_logistics.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void initData() {
        title.setText(R.string.message);
        context = this;
        loginManger = new LoginManger(context);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.title_left:
                finish();
                break;
            case R.id.message_system:
                SkipActivity(NoticeSystemActivity.class);
                break;
            case R.id.message_platform:

                if (loginManger.CheckLoginStatic()){
                    Intent intent = new Intent(context, NoticePlatformActivity.class);
                    startActivity(intent);
                }else {
                    loginManger.LoginCheck(NoticePlatformActivity.class, true);
                }

                break;
            case R.id.message_logistics:

                if (loginManger.CheckLoginStatic()){
                    Intent intent = new Intent(context, NoticeLogisticesActivity.class);
                    startActivity(intent);
                }else {
                    loginManger.LoginCheck(NoticeLogisticesActivity.class, true);
                }

                break;
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
