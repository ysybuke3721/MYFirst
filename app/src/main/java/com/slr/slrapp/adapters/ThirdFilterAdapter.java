package com.slr.slrapp.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.slr.slrapp.R;
import com.slr.slrapp.beans.FarmFilterBean;
import com.slr.slrapp.utils.ContentValues;
import com.slr.slrapp.utils.UiUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/13 0013.
 */
public class ThirdFilterAdapter extends BaseAdapter {
   private List<FarmFilterBean> datas;

    public ThirdFilterAdapter() {
        this.datas = new ArrayList<>();
    }

    public ThirdFilterAdapter(List<FarmFilterBean> datas) {
        this.datas = datas;
       this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (holder == null) {
            holder = new ViewHolder();
            view = UiUtils.inflate(R.layout.list_item_simple_img_tv);
            holder.linearLayout = (LinearLayout) view.findViewById(R.id.ll_item);
            holder.iv = (ImageView) view.findViewById(R.id.iv);
            holder.tv = (TextView) view.findViewById(R.id.tv);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        final FarmFilterBean bean = datas.get(i);
        holder.tv.setText(bean.words);
        if (bean.ifchecked) {
            Picasso.with(UiUtils.getContext()).load(R.mipmap.select).into(holder.iv);
        } else {
            Picasso.with(UiUtils.getContext()).load(R.mipmap.dt).into(holder.iv);
        }

        final ViewHolder finalHolder = holder;
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bean.ifchecked) {
                    //不做操作
                } else {
                    bean.ifchecked = true;
                    for (int j = 0; j < datas.size(); j++) {
                        FarmFilterBean fb = datas.get(j);
                        if (j != i) {
                            fb.ifchecked = false;
                        }
                    }
                }
                notifyDataSetChanged();
                UiUtils.getContext().getSharedPreferences(ContentValues.SP_NAME, 0).edit().putString(ContentValues.SELECTOR_THIRD_WORD, bean.words).apply();
            }
        });


        return view;
    }

    private class ViewHolder {
        TextView tv;
        ImageView iv;
        LinearLayout linearLayout;


    }
}
