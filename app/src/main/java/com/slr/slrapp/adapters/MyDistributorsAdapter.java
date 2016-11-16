package com.slr.slrapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.slr.slrapp.R;
import com.slr.slrapp.beans.MyDistributorBean;
import com.slr.slrapp.beans.OrderBean;
import com.slr.slrapp.utils.UiUtils;

import java.util.List;

/**
 * User: Administrator
 * Time: 2016/7/19 0019
 * Description: ${todo}(我的分销商)
 * Version V1.0
 */
public class MyDistributorsAdapter extends BaseAdapter {
    private Context context;
    private MyDistributorBean myDistributorBean;

    public MyDistributorsAdapter(Context context, MyDistributorBean myDistributorBean) {

        this.myDistributorBean = myDistributorBean;
        this.context = context;

    }

    @Override
    public int getCount() {
        return myDistributorBean.getList().size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {

            LayoutInflater inflate = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflate.inflate(R.layout.list_item_distributors, null);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.distributors_icon_iv);
            viewHolder.name = (TextView) convertView.findViewById(R.id.distributors_name_tv);
            viewHolder.orderNum = (TextView) convertView.findViewById(R.id.distributors_order_num);
            viewHolder.date = (TextView) convertView.findViewById(R.id.distributors_time);
            viewHolder.price = (TextView) convertView.findViewById(R.id.distributors_totleprice);

        } else {

            viewHolder = (ViewHolder) convertView.getTag();

        }

        viewHolder.name.setText(myDistributorBean.getList().get(position).getNickName());
        viewHolder. orderNum.setText(myDistributorBean.getList().get(position).getOrderTotleNumber()+"个");
        viewHolder.date.setText(myDistributorBean.getList().get(position).getCreateDate());

        viewHolder. price.setText(UiUtils.FormatMoneyStyle(myDistributorBean.getList().get(position).getPrice()+""));

        return convertView;
    }

    // 优化adapter，避免重复加载，减少耗时
    private class ViewHolder {

        private ImageView icon;
        private TextView name, orderNum, date, price;

    }

    public void setOrdersList(List<OrderBean> ordersList) {

    }
}