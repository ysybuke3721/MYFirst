package com.slr.slrapp.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.slr.slrapp.R;
import com.slr.slrapp.beans.FarmFilterBean;
import com.slr.slrapp.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * //第一个筛选栏的adapter
 * Created by Administrator on 2016/8/13 0013.
 */
public class FirstFilterAdapter extends BaseAdapter {
    List<FarmFilterBean> data;

    public FirstFilterAdapter() {
        this.data = new ArrayList<>();
    }

    public FirstFilterAdapter(List<FarmFilterBean> data) {
        this.data = data;
    }

    public void setData(List<FarmFilterBean> data) {
        this.data = data;
        this.notifyDataSetChanged();
    }

    public void freshData(List<FarmFilterBean> data) {
        this.data.clear();
        this.data.addAll(data);
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
        tvIvHolder holder = null;
        if (holder == null) {
            holder = new tvIvHolder();
            view = UiUtils.inflate(R.layout.list_item_simple_img_tv);
            holder.img_list_item = (ImageView) view.findViewById(R.id.iv);
            holder.list_item_tv_name = (TextView) view.findViewById(R.id.tv);
            view.setTag(holder);
        } else {
            holder = (tvIvHolder) view.getTag();
        }
        FarmFilterBean bean = data.get(i);
//        Picasso.with(UiUtils.getContext()).load(bean.imgurl).into(holder.img_list_item);
        holder.list_item_tv_name.setText(bean.words);
        return view;
    }

    private class tvIvHolder {
        ImageView img_list_item;
        TextView list_item_tv_name;
    }
}
