package com.slr.slrapp.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.slr.slrapp.BaseApplication;
import com.slr.slrapp.R;
import com.slr.slrapp.beans.DefaultBean;
import com.slr.slrapp.beans.OrderBackBean;
import com.slr.slrapp.gson.ApiUtils;
import com.slr.slrapp.gson.GsonRequest;
import com.slr.slrapp.utils.ContentValues;
import com.slr.slrapp.utils.ToastUtil;
import com.slr.slrapp.utils.UiUtils;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2016/8/22.
 */
public class OrderBackAdapter extends BaseAdapter {

    private Context context;
    private OrderBackBean orderBean = null;
    private int y;
    private int mOrderState[];
    private Map<String, String> map = new HashMap<String, String>();

    public OrderBackAdapter(Context context, OrderBackBean orderBean){

        this.orderBean = orderBean;
        this.context = context;

    }
    @Override
    public int getCount() {
        return orderBean.getList().size();
    }

    @Override
    public Object getItem(int i) {
        return orderBean.getList().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View convertView, final ViewGroup viewGroup) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null){

            LayoutInflater inflate = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflate.inflate(R.layout.list_item_order, null);
            viewHolder.farm_name = (TextView) convertView.findViewById(R.id.item_order_fram_name);
            viewHolder.order_type = (TextView) convertView.findViewById(R.id.item_order_type);
            viewHolder.product_icon = (ImageView) convertView.findViewById(R.id.item_order_product_icon);
            viewHolder.product_name = (TextView) convertView.findViewById(R.id.item_order_product_name);
            viewHolder.product_price = (TextView) convertView.findViewById(R.id.item_order_product_price);
            viewHolder.order_price = (TextView) convertView.findViewById(R.id.item_order_order_bz);
            viewHolder.product_num = (TextView) convertView.findViewById(R.id.item_order_product_num);


            viewHolder.rl1 = (RelativeLayout) convertView.findViewById(R.id.list_item_order_rl1);
            viewHolder.ll2 = (LinearLayout) convertView.findViewById(R.id.list_item_order_rl2);

            viewHolder.bt1= (Button) convertView.findViewById(R.id.item_order_bt1); //评价
            viewHolder.bt2= (Button) convertView.findViewById(R.id.item_order_bt2);  //查看详情

            convertView.setTag(viewHolder);
        }else{

            viewHolder = (ViewHolder) convertView.getTag();

        }

        String OrderState = orderBean.getList().get(i).getRefundStatus();
        viewHolder.order_type.setText(OrderState);
        viewHolder.order_price.setText("共"+orderBean.getList().get(i).getComNumber()+ "件商品 合计：" + UiUtils.FormatMoneyStyle(
                orderBean.getList().get(i).getTotlePrice()+"")
                +"（含运费¥0.00）");

            viewHolder.rl1.setVisibility(View.VISIBLE);
            viewHolder.ll2.setVisibility(View.INVISIBLE);
            viewHolder.product_num.setText("x"+orderBean.getList().get(i).getComNumber());
            viewHolder.product_price.setText(UiUtils.FormatMoneyStyle(orderBean.getList().get(i).getPrice()+"")
            );
            viewHolder.farm_name.setText(orderBean.getList().get(i).getStoreName());
            Picasso.with(context).load(orderBean.getList().get(i).getComPhoto())
                    .error(R.mipmap.error_image2).into(viewHolder.product_icon);

            viewHolder.bt1.setText("删除");

        // 查看详情
        viewHolder.bt2.setVisibility(View.GONE);

        // 订单操作：删除
        viewHolder.bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userId = context.getSharedPreferences(ContentValues.SP_NAME, 0).getString(ContentValues.USER_ID, null);
                OrderDeleteDialog(userId, orderBean.getList().get(i).getId(), i);


            }
        });

        return convertView;
    }

    // 删除某个订单
    private void RemoveOneOrder(final String userid, final int oid, final int i) {

        GsonRequest<DefaultBean> gsonRequest = new GsonRequest<DefaultBean>(
                ApiUtils.RefundDelete(oid), DefaultBean.class,
                new Response.Listener<DefaultBean>() {
                    @Override
                    public void onResponse(DefaultBean response) {

                        ToastUtil.showTextToast(response.getMessage());
                        if (response.getCode() == 200){
                            if (orderBean.getList() != null && orderBean.getList().size() > 0){

                                orderBean.getList().remove(i);
                                notifyDataSetChanged();

                            }
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                ToastUtil.showTextToast("网络异常！");
            }
        });
        BaseApplication.getInstance().getRequestQueue().add(gsonRequest);



    }

    /**
     * 显示删除通知对话框
     */
    private void OrderDeleteDialog(final String userid, final int oid, final int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("删除订单");
        builder.setMessage("是否删除订单，删除后无法查看该订单！");
        builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                RemoveOneOrder(userid, oid, i);
            }
        });
        builder.setNegativeButton("返回", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();

    }


    // 优化adapter，避免重复加载，减少耗时
    private class ViewHolder {

        private TextView order_price;
        private ImageView product_icon;
        private Button bt1, bt2;
        private TextView farm_name, order_type, product_name, product_price, product_num, order_bz;
        private RelativeLayout rl1;
        private LinearLayout ll2;

    }
}
