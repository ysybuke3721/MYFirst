package com.slr.slrapp.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.slr.slrapp.R;
import com.slr.slrapp.beans.UserCommountBean;
import com.slr.slrapp.utils.UiUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/14 0014.
 */
public class CameraListAdapter extends BaseAdapter {
    List<UserCommountBean> datas;
    public CameraListAdapter(List<UserCommountBean> datas){
        this.datas=datas;
    }
    public CameraListAdapter(){
        this.datas=new ArrayList<>();
    }
    public void addData(List<UserCommountBean> datas){
        this.datas=(datas);
        this.notifyDataSetChanged();
    }
    public void reFreshData(List<UserCommountBean> datas){
        this.datas.clear();
        this.datas.addAll(datas);
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyHolder holder = null;
        if (view == null) {
            holder = new MyHolder();
            view = UiUtils.inflate(R.layout.list_item_farm_camera);
            holder.iv_user_head = (ImageView) view.findViewById(R.id.iv_user_head);
            holder.tv_user_name = (TextView) view.findViewById(R.id.tv_user_name);
            holder.iv_user_degree = (ImageView) view.findViewById(R.id.iv_user_degree);
            holder.tv_discuss_time = (TextView) view.findViewById(R.id.tv_discuss_time);
            holder.tv_user_comment = (TextView) view.findViewById(R.id.tv_user_comment);
            view.setTag(holder);
        } else {
            holder = (MyHolder) view.getTag();
        }


        holder.tv_user_name.setText(datas.get(i).user_name);
//    holder.iv_user_degree.setImageResource(listbeans.get(i).user_level_img);
        Picasso.with(UiUtils.getContext()).load(datas.get(i).user_head).error(R.mipmap.error_image1).into(holder.iv_user_head);
        holder.tv_discuss_time.setText(UiUtils.FormatDate(datas.get(i).communt_time));
        holder.tv_user_comment.setText(datas.get(i).user_communt);
        //TODO  根据用户等级切换不同等级图片
        return view;
    }


    private class MyHolder {
        ImageView iv_user_head;
        TextView tv_user_name;
        ImageView iv_user_degree;
        TextView tv_discuss_time;
        TextView tv_user_comment;
    }

}
