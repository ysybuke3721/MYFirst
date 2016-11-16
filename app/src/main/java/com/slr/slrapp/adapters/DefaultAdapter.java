package com.slr.slrapp.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.slr.slrapp.holders.BaseHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/7/1 0001.
 */
public abstract class DefaultAdapter<T> extends BaseAdapter {
    private List<T> data;

    public DefaultAdapter(List<T> data) {
        this.data = data;
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
        BaseHolder<T> holder;
        if (view == null) {
            holder = getHolder();
        } else {
            holder = (BaseHolder<T>) view.getTag();
        }
        T t = data.get(i);
        holder.setData(t);
        return holder.getContentView();
    }

    protected abstract BaseHolder<T> getHolder();
}
