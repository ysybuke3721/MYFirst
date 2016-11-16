package com.slr.slrapp.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/7/7 0007.
 */
public class SimpleImgTextAdapter<T> extends BaseBaseAdapter<T>{
    public SimpleImgTextAdapter(List<T> list, Context context) {
        super(list, context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
