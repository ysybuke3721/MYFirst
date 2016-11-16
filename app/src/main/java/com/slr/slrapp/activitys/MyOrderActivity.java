package com.slr.slrapp.activitys;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.slr.slrapp.BaseApplication;
import com.slr.slrapp.R;
import com.slr.slrapp.factory.FragmentFactory;
import com.slr.slrapp.utils.PixUtil;
import com.slr.slrapp.utils.ToastUtil;
import com.slr.slrapp.utils.UiUtils;
import com.slr.slrapp.widget.TabPageIndicator;

/**
 * Created by HuaChing904 on 2016/6/23.
 */
public class MyOrderActivity extends BaseActivity {

    private TabPageIndicator indicator;
    private ViewPager viewPager;
    private TextView title;
    private Context context;
    private ToastUtil toastUtil;
    private LinearLayout back;
    private BasePagerAdapter adapter;

    @Override
    public int getLayoutResId() {
        return R.layout.viewpager_indicator;
    }

    @Override
    public void initView() {
        back = (LinearLayout) findViewById(R.id.title_left);
        title = (TextView) findViewById(R.id.title_text_tv);
        indicator = (TabPageIndicator) findViewById(R.id.order_indicator);
        viewPager = (ViewPager) findViewById(R.id.order_viewPager);
    }

    @Override
    public void initListener() {
        back.setOnClickListener(this);
    }

    @Override
    public void initData() {

        title.setText(R.string.my_order);
        context = this;
        adapter = new BasePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);
        setTabPagerIndicator();
    }

    private void setTabPagerIndicator() {
        indicator.setIndicatorMode(TabPageIndicator.IndicatorMode.MODE_NOWEIGHT_EXPAND_SAME);// 设置模式，一定要先设置模式
        indicator.setDividerColor(Color.parseColor("#FFFFFF"));// 设置分割线的颜色
        indicator.setDividerPadding(PixUtil.dip2px(BaseApplication.getInstance(), 10));
        indicator.setIndicatorColor(Color.parseColor("#df3d3e"));// 设置底部导航线的颜色
        indicator.setTextColorSelected(Color.parseColor("#df3d3e"));// 设置tab标题选中的颜色
        indicator.setTextColor(Color.parseColor("#494949"));// 设置tab标题未被选中的颜色
        indicator.setTextSize(PixUtil.dip2px(BaseApplication.getInstance(), 16));// 设置字体大小
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
        String[] titles;

        public BasePagerAdapter(FragmentManager fm) {
            super(fm);
            this.titles = UiUtils.getStringArray(R.array.order_titles);
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentFactory.createForExpand(position);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int flag = data.getIntExtra("flag",0);
        System.out.println("返回数据："+requestCode+"////"+flag);
        // 刷新界面
        if (resultCode == 0){

            if (flag == 1){

                viewPager.setAdapter(adapter);
                indicator.setViewPager(viewPager);
            }

        }

    }

}
