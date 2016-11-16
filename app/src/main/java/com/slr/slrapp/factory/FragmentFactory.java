package com.slr.slrapp.factory;

import android.support.v4.app.Fragment;

import com.slr.slrapp.beans.MyDistributorBean;
import com.slr.slrapp.fragments.DistributorsOneFragment;
import com.slr.slrapp.fragments.DistributorsThreeFragment;
import com.slr.slrapp.fragments.DistributorsTwoFragment;
import com.slr.slrapp.fragments.OrderAllFragment;
import com.slr.slrapp.fragments.OrderBackFragment;
import com.slr.slrapp.fragments.OrderEvalutaionFragment;
import com.slr.slrapp.fragments.OrderGetFragment;
import com.slr.slrapp.fragments.OrderSendFragment;


/**
 * Created by shan_yao on 2016/6/17.
 */
public class FragmentFactory {

    public static Fragment createForExpand(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new OrderAllFragment();
                break;
//            case 1:
//                fragment = new OrderPayFragment();
//                break;
            case 1:
                fragment = new OrderSendFragment();
                break;
            case 2:
                fragment = new OrderGetFragment();
                break;
            case 3:
                fragment = new OrderEvalutaionFragment();
                break;
            case 4:
                fragment = new OrderBackFragment();
                break;
        }
        return fragment;
    }

    public static Fragment createDistributors(int position, MyDistributorBean myDistributorBean) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = DistributorsOneFragment.getInstance(myDistributorBean);
                break;
            case 1:
                fragment = new DistributorsTwoFragment();
                break;
            case 2:
                fragment = new DistributorsThreeFragment();
                break;
        }
        return fragment;
    }
}
