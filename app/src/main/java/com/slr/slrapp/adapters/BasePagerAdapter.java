package com.slr.slrapp.adapters;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/7/4 0004.
 */
public abstract class BasePagerAdapter<T> extends PagerAdapter{
    //数据源
    public List<T> datas;
    public BasePagerAdapter(List<T> datas){
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public abstract Object instantiateItem(ViewGroup container, int position) ;
}
