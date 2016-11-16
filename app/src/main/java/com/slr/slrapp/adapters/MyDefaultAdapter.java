package com.slr.slrapp.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/6 0006.
 */
public abstract class MyDefaultAdapter <T> extends BaseAdapter {
    private List<T> data;

    public MyDefaultAdapter (){
        this.data=new ArrayList<>();
    }

    public MyDefaultAdapter(List<T> data) {
        this.data = data;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        return getAdapterView(i,view,viewGroup);
    }

    public  abstract  View getAdapterView(int i, View v,ViewGroup viewGroup);
}
