package com.slr.slrapp.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.slr.slrapp.R;
import com.slr.slrapp.beans.NoticeLogisticesBean;
import com.slr.slrapp.utils.UiUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * UserInfoBean: Administrator
 * Time: 2016/7/18 0018
 * Description: ${todo}(物流通知)
 * Version V1.0
 */
public class NoticeLogisticesAdapter extends BaseAdapter {
    private Context context;
    private List<NoticeLogisticesBean.ListBean> list;

    public NoticeLogisticesAdapter(Context context){
        this.context = context;
        list = new ArrayList<>();
    }

    public void refreshItem(List<NoticeLogisticesBean.ListBean> list) {
        this.list.clear();
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }

    public void addAllItem(List<NoticeLogisticesBean.ListBean> list) {
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = UiUtils.inflate(R.layout.list_item_notice_logistices);
            viewHolder.notice_logistices_item = (RelativeLayout) convertView.findViewById(R.id.notice_logistices_item);
            viewHolder.notice_logistices_time = (TextView) convertView.findViewById(R.id.notice_logistices_time);
            viewHolder.notice_logistices_name = (TextView) convertView.findViewById(R.id.notice_logistices_name);
            viewHolder.notice_logistices_icon = (ImageView) convertView.findViewById(R.id.notice_logistices_icon);
            viewHolder.notice_logistices_item_content = (TextView) convertView.findViewById(R.id.notice_logistices_item_content);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        NoticeLogisticesBean.ListBean listBean = list.get(i);
        viewHolder.notice_logistices_time.setText(listBean.getSendDate());
        viewHolder.notice_logistices_name.setText(String.format(context.getString(R.string.notice_logistices_name),listBean.getComName(), listBean.getComStatus()));
        Picasso.with(context).load(listBean.getComPhoto())
                .error(R.mipmap.error_image2).into(viewHolder.notice_logistices_icon);
        viewHolder.notice_logistices_item_content.setText(String.format(context.getString(R.string.notice_logistices_item_content), listBean.getOrderNumber()));
        return convertView;
    }


    // 优化adapter，避免重复加载，减少耗时
    private class ViewHolder {
        private RelativeLayout notice_logistices_item;
        private TextView notice_logistices_time,notice_logistices_name,notice_logistices_item_content;
        private ImageView notice_logistices_icon;

    }
}
