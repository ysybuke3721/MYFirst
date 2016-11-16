package com.slr.slrapp.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.slr.slrapp.R;
import com.slr.slrapp.beans.MyEvalutaionsBean;
import com.slr.slrapp.utils.UiUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * UserInfoBean: Administrator
 * Time: 2016/7/19 0019
 * Description: ${todo}(我的评价)
 * Version V1.0
 */
public class MyEvalutaionsAdapter extends BaseAdapter {
    private Context context;
    private List<MyEvalutaionsBean.ListBean> items = new ArrayList<>();
    private MyEvalutaionsBean myEvalutaionsBean;

    public MyEvalutaionsAdapter(Context context, MyEvalutaionsBean myEvalutaionsBean) {
        this.myEvalutaionsBean = myEvalutaionsBean;
        this.context = context;
    }

    public void refreshItem(List<MyEvalutaionsBean.ListBean> items) {
        this.items.clear();
        this.items.addAll(items);
        this.notifyDataSetChanged();
    }

    public void addAllItem(List<MyEvalutaionsBean.ListBean> items) {
        this.items.addAll(items);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return myEvalutaionsBean.getList().size();
    }

    @Override
    public Object getItem(int i) {
        return myEvalutaionsBean.getList().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if (convertView==null){
            holder=new ViewHolder();
            convertView= UiUtils.inflate(R.layout.list_item_evalutaions);
            holder.my_evaluations = (RelativeLayout) convertView.findViewById(R.id.my_evaluations);
            holder.my_evaluation_content= (TextView) convertView.findViewById(R.id.my_evaluation_content);
            holder.my_evaluation_date= (TextView) convertView.findViewById(R.id.my_evaluation_date);
            holder.my_evaluations_name_tv= (TextView) convertView.findViewById(R.id.my_evaluations_name_tv);
            holder.my_evaluations_price_tv= (TextView) convertView.findViewById(R.id.my_evaluations_price_tv);
            holder.my_evaluations_icon_iv= (ImageView) convertView.findViewById(R.id.my_evaluations_icon_iv);
            convertView.setTag(holder);

            final MyEvalutaionsBean.ListBean bean=myEvalutaionsBean.getList().get(i);
            if (bean.getMessage()!=null){
                holder.my_evaluation_content.setText(bean.getMessage().toString());
            }else {
                holder.my_evaluation_content.setText("未填写评价内容！");
            }
            holder.my_evaluation_date.setText(bean.getMessageDate());
            holder.my_evaluations_name_tv.setText(bean.getStarNum());
            holder.my_evaluations_price_tv.setText(bean.getComName());
            Picasso.with(context).load(bean.getSmallPhoto()).error(R.mipmap.error_image2).into(holder.my_evaluations_icon_iv);
            holder.my_evaluations.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                ToastUtil.showTextToast(bean.getComName());
                }
            });
        }else {
            holder= (ViewHolder) convertView.getTag();
        }


        return convertView;
    }
    private class  ViewHolder{
        TextView my_evaluations_name_tv,my_evaluations_price_tv,my_evaluation_content,my_evaluation_date;
        ImageView my_evaluations_icon_iv;
        RelativeLayout my_evaluations;
    }
}
