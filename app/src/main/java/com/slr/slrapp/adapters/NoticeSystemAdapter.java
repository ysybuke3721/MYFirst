package com.slr.slrapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.slr.slrapp.R;
import com.slr.slrapp.activitys.WebViewActivity;
import com.slr.slrapp.beans.NoticeSystemBean;
import com.slr.slrapp.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * UserInfoBean: Administrator
 * Time: 2016/7/18 0018
 * Description: ${todo}(系统通知)
 * Version V1.0
 */
public class NoticeSystemAdapter extends BaseAdapter{
    private Context context;
    private List<NoticeSystemBean.ListBean> list;

    public NoticeSystemAdapter(Context context){
        this.context = context;
        list = new ArrayList<>();
    }

    public void refreshItem(List<NoticeSystemBean.ListBean> list) {
        this.list.clear();
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }

    public void addAllItem(List<NoticeSystemBean.ListBean> list) {
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
            convertView = UiUtils.inflate(R.layout.list_item_notice_system);
            viewHolder.notice_system_item = (RelativeLayout) convertView.findViewById(R.id.notice_system_item);
            viewHolder.notice_system_time = (TextView) convertView.findViewById(R.id.notice_system_time);
            viewHolder.notice_system_name = (TextView) convertView.findViewById(R.id.notice_system_name);
            viewHolder.notice_system_item_time = (TextView) convertView.findViewById(R.id.notice_system_item_time);
            viewHolder.notice_system_item_content = (TextView) convertView.findViewById(R.id.notice_system_item_content);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final NoticeSystemBean.ListBean listBean = list.get(i);
        viewHolder.notice_system_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WebViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("WEBVIEW", listBean.getDetails());
                bundle.putString("TITLE", listBean.getTitle());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        viewHolder.notice_system_time.setText(listBean.getCreateDate());
        viewHolder.notice_system_name.setText(listBean.getTitle());
        viewHolder.notice_system_item_time.setText(listBean.getCreateDate());
        viewHolder.notice_system_item_content.setText(listBean.getContent());
        return convertView;
    }


    // 优化adapter，避免重复加载，减少耗时
    private class ViewHolder {
        private RelativeLayout notice_system_item;
        private TextView notice_system_time,notice_system_name, notice_system_item_time, notice_system_item_content;
    }
}
