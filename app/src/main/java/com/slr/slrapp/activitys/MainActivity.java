package com.slr.slrapp.activitys;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.slr.slrapp.R;
import com.slr.slrapp.fragments.FarmerFragment;
import com.slr.slrapp.fragments.FirstPageFragment;
import com.slr.slrapp.fragments.MySelfFragment;
import com.slr.slrapp.fragments.ShoppingCarFragment;
import com.slr.slrapp.managers.mUpdateManager;
import com.slr.slrapp.utils.FragmentUtils;

public class MainActivity extends BaseActivity {


    private RadioGroup tab_menu;
    private FirstPageFragment firstPageFragment;
    private FarmerFragment farmerFragment;
    private ShoppingCarFragment ShoppingCarFargment;
    private MySelfFragment mySelfFragemt;
    private RadioButton bottom_btn_first;


    private FragmentManager mFragmentManager;
    private Fragment myCurrentFragment;


    //第一次和第二次点击返回键的时间
    private long firsttime = 0;
    private long secondtime = 0;
    //是否点击了返回键
    private boolean ispressed = false;

    private class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i) {
                //点击“首页”
                case R.id.bottom_btn_first:
//                    if (firstPageFragment == null) {
//                        firstPageFragment = new FirstPageFragment();
//                    }
//                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_main, firstPageFragment).commit();
                    replaceFragment(FirstPageFragment.class);

                    break;
                //点击“商家”
                case R.id.bottom_btn_bussniss:
//                    if (farmerFragment == null) {
//                        farmerFragment = new FarmerFragment();
//                    }
//                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_main, farmerFragment).commit();
                    replaceFragment(FarmerFragment.class);
                    break;
                //点击“购物车”
                case R.id.bottom_btn_shopcar:
//                    if (ShoppingCarFargment == null) {
//                        ShoppingCarFargment = new ShoppingCarFragment();
//                    }
//                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_main, ShoppingCarFargment).commit();
                    replaceFragment(ShoppingCarFragment.class);
                    break;
                //点击“我的”
                case R.id.bottom_btn_me:
//                    if (mySelfFragemt == null) {
//                        mySelfFragemt = new MySelfFragment();
//                    }
//                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_main, mySelfFragemt).commit();
                    replaceFragment(MySelfFragment.class);
                    break;
            }
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }


    public void replaceFragment(Class<? extends Fragment> newFragment) {

        myCurrentFragment = FragmentUtils.switchFragment(mFragmentManager,
                R.id.frameLayout_main, myCurrentFragment,
                newFragment, null, false);
    }

    private BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {


            replaceFragment(FarmerFragment.class);

        }
    };

    public void initView() {

        mFragmentManager=getSupportFragmentManager();

        //默认选中第一个图标
        bottom_btn_first = (RadioButton) findViewById(R.id.bottom_btn_first);
        bottom_btn_first.setChecked(true);
        //初始化第一个fragment
        firstPageFragment = new FirstPageFragment();
        FragmentUtils.replaceFragment(mFragmentManager, R.id.frameLayout_main,FirstPageFragment.class, null, false);

        tab_menu = (RadioGroup) findViewById(R.id.tab_menu);
        bottom_btn_first= (RadioButton) findViewById(R.id.bottom_btn_first);

//        IntentFilter filter=new IntentFilter("200");
//        registerReceiver(broadcastReceiver,filter);

    }

    @Override
    public void initListener() {
        tab_menu.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
    }

    @Override
    public void initData() {

    }

    //双击退出
    @Override
    public void onBackPressed() {
        if (ispressed) {
            secondtime = System.currentTimeMillis();
            //执行这里说明已经按过一次返回键，准备按下第二次
            if (secondtime - firsttime > 2000) {
                //说明距离第一次按下返回键超过两秒，继续土司
                Toast toast = Toast.makeText(this, "双击退出", Toast.LENGTH_SHORT);
                //设置吐司持续时间
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.show();
                ispressed = true;
                firsttime = System.currentTimeMillis();
            } else {
                //说明时间差小于两秒，双击退出
                finish();
                //退出后进程依然存在，将参数重置,保证下次程序能正常双击退出
                ispressed = false;
                firsttime = 0;
                secondtime = 0;
            }
        } else {
            //执行这里说明是第一次按返回，给个土司提醒
            Toast toast = Toast.makeText(this, "双击退出", Toast.LENGTH_SHORT);
            //设置吐司持续时间
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
            ispressed = true;
            firsttime = System.currentTimeMillis();
        }
    }

    @Override
    public void onClick(View view) {

    }










}
