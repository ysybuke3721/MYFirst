package com.slr.slrapp.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.slr.slrapp.R;
import com.slr.slrapp.beans.NoticePlatformBean;
import com.slr.slrapp.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * UserInfoBean: Administrator
 * Time: 2016/7/18 0018
 * Description: ${todo}(交易通知)
 * Version V1.0
 */
public class NoticePlatformAdapter extends BaseAdapter {
    private Context context;
    private List<NoticePlatformBean.ListBean> list;

    public NoticePlatformAdapter(Context context){
        this.context = context;
        list = new ArrayList<>();
    }

    public void refreshItem(List<NoticePlatformBean.ListBean> list) {
        this.list.clear();
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }

    public void addAllItem(List<NoticePlatformBean.ListBean> list) {
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
            convertView = UiUtils.inflate(R.layout.list_item_notice_platform);
            viewHolder.notice_platform_name = (TextView) convertView.findViewById(R.id.notice_platform_name);
            viewHolder.tv = (TextView) convertView.findViewById(R.id.notice_platform_name_t);
            viewHolder.notice_platform_content = (TextView) convertView.findViewById(R.id.notice_platform_content);
            viewHolder.notice_platform_time = (TextView) convertView.findViewById(R.id.notice_platform_time);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        NoticePlatformBean.ListBean listBean = list.get(i);
        if (listBean.getPrice().equals("zhuce")){

            viewHolder.tv.setText(listBean.getNickName()+"注册成功！");
            viewHolder.notice_platform_name.setVisibility(View.INVISIBLE);
            viewHolder.notice_platform_content.setText("您的"+listBean.getDealerType()+ listBean.getNickName() + ",注册成功!");

        }else{
            viewHolder.notice_platform_name.setText("￥"+listBean.getPrice());
            String str = listBean.getPrice().substring(0,1);
            if (str.equals("-")){
                viewHolder.notice_platform_content.setText("您的"+listBean.getDealerType()+ listBean.getNickName() + ",退单成功!");
            }else {
                viewHolder.notice_platform_content.setText("您的"+listBean.getDealerType()+ listBean.getNickName() + ",下单成功!");
            }
        }


        if (listBean.getLastOrderDate()!=null){

            viewHolder.notice_platform_time.setText(listBean.getLastOrderDate().substring(0,10));
        }else {
            viewHolder.notice_platform_time.setText("未知");
        }
        return convertView;
    }

    // 优化adapter，避免重复加载，减少耗时
    private class ViewHolder {
        private TextView notice_platform_name, notice_platform_content,notice_platform_time,tv;

    }
}
