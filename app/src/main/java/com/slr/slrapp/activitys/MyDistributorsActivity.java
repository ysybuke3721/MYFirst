package com.slr.slrapp.activitys;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.slr.slrapp.BaseApplication;
import com.slr.slrapp.R;
import com.slr.slrapp.beans.MyDistributorBean;
import com.slr.slrapp.factory.FragmentFactory;
import com.slr.slrapp.gson.ApiUtils;
import com.slr.slrapp.gson.GsonRequest;
import com.slr.slrapp.managers.LoginManger;
import com.slr.slrapp.utils.ContentValues;
import com.slr.slrapp.utils.PixUtil;
import com.slr.slrapp.utils.ToastUtil;
import com.slr.slrapp.utils.UiUtils;
import com.slr.slrapp.widget.TabPageIndicator;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Administrator
 * Time: 2016/7/5 0005
 * Description: ${todo}(我的经销商界面：显示所有佣金以及各个级别的分成详情)
 * Version V1.0
 */
public class MyDistributorsActivity extends BaseActivity {

    private Context context;
    private LinearLayout back;
    private TextView title, tv;
    private ToastUtil toastUtil;
    private TabPageIndicator indicator;
    private ViewPager viewPager;
    private MyDistributorBean myDistributorBean;
    private int first, second, third;
    private TextView totle_price;
    //是否登录状态
    private Boolean LoginState;
    // 用户id
    private String userId ;
    // 判断登录
    private LoginManger loginManger;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_my_distributors;
    }

    @Override
    public void initView() {
        back = (LinearLayout) findViewById(R.id.title_left);
        title = (TextView) findViewById(R.id.title_text_tv);
        tv = (TextView) findViewById(R.id.my_distrivutors_tv1);
        indicator = (TabPageIndicator) findViewById(R.id.my_distributors_indicator);
        viewPager = (ViewPager) findViewById(R.id.my_distributors_viewPager);
        totle_price = (TextView) findViewById(R.id.my_distrivutors_money);
    }

    @Override
    public void initListener() {
        back.setOnClickListener(this);

    }

    @Override
    public void initData() {
        title.setText(R.string.my_distributors);
        context = this;

        LoginState=context.getSharedPreferences(ContentValues.SP_NAME,0).getBoolean(ContentValues.IF_IS_LOGINED,false);
        userId=context.getSharedPreferences(ContentValues.SP_NAME,0).getString(ContentValues.USER_ID,"");
        toastUtil = new ToastUtil();
        loginManger = new LoginManger(context);
        myDistributorBean = new MyDistributorBean();
        // 默认1为一级分销商， 0为第一页数据
        myDistributorBean = getNetData(1, "", 0);

    }


    private void setTabPagerIndicator() {
        indicator.setIndicatorMode(TabPageIndicator.IndicatorMode.MODE_NOWEIGHT_EXPAND_SAME);// 设置模式，一定要先设置模式
        indicator.setDividerColor(Color.parseColor("#FFFFFF"));// 设置分割线的颜色
        indicator.setDividerPadding(PixUtil.dip2px(BaseApplication.getInstance(), 10));
        indicator.setIndicatorColor(Color.parseColor("#FF0000"));// 设置底部导航线的颜色
        indicator.setTextColorSelected(Color.parseColor("#FF0000"));// 设置tab标题选中的颜色
        indicator.setTextColor(Color.parseColor("#797979"));// 设置tab标题未被选中的颜色
        indicator.setTextSize(PixUtil.dip2px(BaseApplication.getInstance(), 14));// 设置字体大小
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.title_left:

                finish();

                break;

        }

    }

class BasePagerAdapter extends FragmentStatePagerAdapter {
    String[] titles = {"一级分销（"+first+"）", "二级分销（"+second+"）", "三级分销（"+third+"）"};

    public BasePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.createDistributors(position, myDistributorBean);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}


    private MyDistributorBean getNetData(int type, String startDate, int num) {

        System.out.println("我的分销商："+ApiUtils.APP_API + "/myDistributor?type="+type+"&startDate="+startDate
        +"&num="+num+"&userId="+userId);
            Map<String, String> map = new HashMap<>();
            map.put("type", type+"");
            map.put("startDate", startDate);
            map.put("num", num+"");
            map.put("userId", userId);
            GsonRequest<MyDistributorBean> gsonRequest = new GsonRequest<MyDistributorBean>(map,
                    ApiUtils.getMyDistributor(),
                    MyDistributorBean.class,
                    new Response.Listener<MyDistributorBean>() {
                        @Override
                        public void onResponse(MyDistributorBean response) {

                            myDistributorBean = response;
                            if (myDistributorBean.getCode()!=200){
                                ToastUtil.showTextToast(myDistributorBean.getMessage());
                                if (myDistributorBean.getCode()==101){
                                    tv.setVisibility(View.VISIBLE);
                                    indicator.setVisibility(View.INVISIBLE);
                                    viewPager.setVisibility(View.INVISIBLE);
                                }
                            }else{

                                if (myDistributorBean.getIsMember()==1){
                                    if (myDistributorBean.getFirstSize()==0){
                                        ToastUtil.showTextToast("您还没有自己的下级分销商，赶快邀请吧！");
                                    }else {
                                        tv.setVisibility(View.INVISIBLE);
                                        indicator.setVisibility(View.VISIBLE);
                                        viewPager.setVisibility(View.VISIBLE);
                                        first = myDistributorBean.getFirstSize();
                                        second = myDistributorBean.getSecondSize();
                                        third = myDistributorBean.getThirdSize();
                                        totle_price.setText(UiUtils.FormatMoneyStyle(myDistributorBean.getTotlePrice()+""));


                                        BasePagerAdapter adapter = new BasePagerAdapter(getSupportFragmentManager());
                                        viewPager.setAdapter(adapter);
                                        indicator.setViewPager(viewPager);
                                        setTabPagerIndicator();
                                    }

                                }else {

                                    tv.setVisibility(View.VISIBLE);
                                    indicator.setVisibility(View.INVISIBLE);
                                    viewPager.setVisibility(View.INVISIBLE);
                                }

                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    ToastUtil.showTextToast("网络异常！");
                }
            });
            BaseApplication.getInstance().getRequestQueue().add(gsonRequest);


        return myDistributorBean;

    }
}
