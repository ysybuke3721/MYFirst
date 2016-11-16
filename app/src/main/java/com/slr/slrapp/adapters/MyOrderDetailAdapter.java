package com.slr.slrapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.slr.slrapp.BaseApplication;
import com.slr.slrapp.R;
import com.slr.slrapp.beans.DefaultBean;
import com.slr.slrapp.beans.OrderDetailBean;
import com.slr.slrapp.gson.ApiUtils;
import com.slr.slrapp.gson.GsonRequest;
import com.slr.slrapp.utils.ContentValues;
import com.slr.slrapp.utils.ToastUtil;
import com.slr.slrapp.utils.UiUtils;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

/**
 * 订单详情
 * Created by admin on 2016/8/11.
 */
public class MyOrderDetailAdapter extends BaseAdapter{

    private Context context;
    private OrderDetailBean order;
    private ViewHolder1 holder1;
    private ViewHolder2 holder2;
    private ViewHolder3 holder3;
    private ViewHolder4 holder4;
    private ViewHolder5 holder5;
    private ViewHolder6 holder6;
    LayoutInflater inflater;
    final int VIEW_TYPE = 3;
    final int TYPE_1 = 0;
    final int TYPE_2 = 1;
    final int TYPE_3 = 2;
    final int TYPE_4 = 3;
    final int TYPE_5 = 4;
    final int TYPE_6 = 5;
    private String userid;
    private String oid;

    public MyOrderDetailAdapter(Context context, OrderDetailBean order, String userid, String oid){

        this.userid = userid;
        this.context = context;
        this.order = order;
        this.oid = oid;
    }

    @Override
    public int getCount() {
        return 5 + order.getList().size();
    }

    //RemoveOneOrder(i);
    @Override
    public Object getItem(int i) {
        return order.getList().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int i) {
        int p = i;
        if (p == 0)
            return TYPE_1;
        else if (p == 1)
            return TYPE_2;
        else if (p == 2)
            return TYPE_3;
        else if (p >= 3&&p < 3 + order.getList().size())
            return TYPE_4;
        else if (p == 3 + order.getList().size())
            return TYPE_5;
        else
            return TYPE_6;
    }

    @Override
    public int getViewTypeCount() {
        return 6;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        int type = getItemViewType(i);
        if (convertView == null) {
            inflater = LayoutInflater.from(context);
            // 按当前所需的样式，确定new的布局
            switch (type) {
                case TYPE_1:
                    holder1 = new ViewHolder1();
                    convertView = inflater.inflate(R.layout.list_item_myorderdetail_id, null);
                    holder1.orderid = (TextView) convertView.findViewById(R.id.item_myorderdetailid);
                    holder1.status = (TextView) convertView.findViewById(R.id.item_myorderdetail_status);

                    convertView.setTag(holder1);

                    break;
                case TYPE_2:
                    holder2 = new ViewHolder2();
                    convertView = inflater.inflate(R.layout.list_item_orderdetail_userinfo, null);
                    holder2.name = (TextView) convertView.findViewById(R.id.item_orderdetail_name);
                    holder2.adress = (TextView) convertView.findViewById(R.id.item_orderdetail_adress);

                    convertView.setTag(holder2);
                    break;
                case TYPE_3:
                    holder3 = new ViewHolder3();
                    convertView = inflater.inflate(R.layout.list_item_myorderdetail_farm, null);
                    //holder3.icon = (ImageView) convertView.findViewById(R.id.item_myorderdetail_farm_icon);
                    holder3.name = (TextView) convertView.findViewById(R.id.item_myorderdetail_farm_name);

                    convertView.setTag(holder3);
                    break;
                case TYPE_4:
                    holder4 = new ViewHolder4();
                    convertView = inflater.inflate(R.layout.list_item_orderdetail_product, null);
                    holder4.icon = (ImageView) convertView.findViewById(R.id.item_orderdetail_product_icon);
                    holder4.name = (TextView) convertView.findViewById(R.id.item_orderdetail_product_name);
                    holder4.num = (TextView) convertView.findViewById(R.id.item_orderdetail_product_num);
                    holder4.price = (TextView) convertView.findViewById(R.id.item_orderdetail_product_price);
                    holder4.bt = (Button) convertView.findViewById(R.id.item_orderdetail_bt);
                    convertView.setTag(holder4);
                    break;
                case TYPE_5:
                    holder5 = new ViewHolder5();
                    convertView = inflater.inflate(R.layout.list_item_orderdetail_message, null);
                    holder5.payMode = (TextView) convertView.findViewById(R.id.item_myorderdetail_paymode);

                    convertView.setTag(holder5);
                    break;
                case TYPE_6:
                    holder6 = new ViewHolder6();
                    convertView = inflater.inflate(R.layout.list_item_orderdetail_total, null);
                    holder6.totlePrice = (TextView) convertView.findViewById(R.id.item_myorderdetail_totleprice);
                    holder6.freightPrice = (TextView) convertView.findViewById(R.id.item_myorderdetail_freightmoney);
                    holder6.orderDate = (TextView) convertView.findViewById(R.id.item_myorderdetail_orderdate);
                    holder6.bt = (Button) convertView.findViewById(R.id.order_delete);

                    convertView.setTag(holder6);
                    break;
                default:
                    break;
            }

        } else {
            switch (type) {
                case TYPE_1:
                    holder1 = (ViewHolder1) convertView.getTag();
                    break;
                case TYPE_2:
                    holder2 = (ViewHolder2) convertView.getTag();
                    break;
                case TYPE_3:
                    holder3 = (ViewHolder3) convertView.getTag();
                    break;
                case TYPE_4:
                    holder4 = (ViewHolder4) convertView.getTag();
                    break;
                case TYPE_5:
                    holder5 = (ViewHolder5) convertView.getTag();
                    break;
                case TYPE_6:
                    holder6 = (ViewHolder6) convertView.getTag();
                    break;
            }
        }

        // 设置资源
        switch (type) {
            case TYPE_1:

                holder1.orderid.setText("订单号："+order.getOrderNumber());
                holder1.status.setText(order.getStatus());
                break;
            case TYPE_2:

                holder2.name.setText(order.getReceiptName()+"    "+order.getReceiptTel());
                holder2.adress.setText(order.getReceiptAddress());

                break;
            case TYPE_3:


                if (order.getList().size()==1){

                    holder3.name.setText(order.getList().get(0).getStoreName());

                }

                break;
            case TYPE_4:

                holder4.name.setText(order.getList().get(i-3).getComName());
                holder4.price.setText( UiUtils.FormatMoneyStyle(order.getList().get(i-3).getPrice()+""));
                holder4.num.setText("x"+order.getList().get(i-3).getComNumber());
                Picasso.with(context).load(order.getList().get(i-3).getSmallPhoto())
                        .error(R.mipmap.error_image2).into(holder4.icon);
                holder4.bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String userId = context.getSharedPreferences(ContentValues.SP_NAME, 0).getString(ContentValues.USER_ID, null);
                        OrderBackDialog(userId, order.getList().get(i-3).getSozbId(), i-3);
                    }
                });

                break;
            case TYPE_5:
                holder5.payMode.setText(order.getPayMode());

                break;
            case TYPE_6:

                holder6.totlePrice.setText(UiUtils.FormatMoneyStyle(order.getTotlePrice()+""));
                holder6.freightPrice.setText(UiUtils.FormatMoneyStyle(order.getFreightMoney()+""));
                holder6.orderDate.setText(order.getOrderDate());
                holder6.bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        OrderDeleteDialog(userid, oid);
                    }
                });

                break;
        }

        return convertView;
    }


    // 退单
    private void OrderBack(String userid, int oid, final int i){

        Map<String, String> map = new HashMap<>();
        map.put("userId", userid);
        map.put("sozbId", oid+"");
        GsonRequest<DefaultBean> gsonRequest = new GsonRequest<>(map,
                ApiUtils.OrderBack(),
                DefaultBean.class,
                new Response.Listener<DefaultBean>() {
                    @Override
                    public void onResponse(DefaultBean session) {

                        ToastUtil.showTextToast(session.getMessage());

                        if (session.getCode()==200) {
                            order.getList().remove(i);
                            if (order.getList().size()==0||order.getList()==null){
                                ((Activity)context).finish();
                            }
                            notifyDataSetChanged();

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

    // 删除订单
    private void OrderDelete(String userid, String oid){

        Map<String, String> map = new HashMap<>();
        map.put("userId", userid);
        map.put("orderId", oid);
        GsonRequest<DefaultBean> gsonRequest = new GsonRequest<>(map,
                ApiUtils.OrderDelete(),
                DefaultBean.class,
                new Response.Listener<DefaultBean>() {
                    @Override
                    public void onResponse(DefaultBean session) {

                        ToastUtil.showTextToast(session.getMessage());

                        if (session.getCode()==200) {

                            Intent intent = new Intent();
                            intent.putExtra("flag",0);
                            ((Activity)context).setResult(0,intent);
                            ((Activity)context).finish();

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
     * 显示退单通知对话框
     */
    private void OrderBackDialog(final String userid, final int oid, final int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("是否退单？");
        builder.setMessage("退单会产生相应的违约金，具体比例请关注官网详情！");
        builder.setPositiveButton("退单", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                OrderBack(userid, oid, i);
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

    /**
     * 显示删除通知对话框
     */
    private void OrderDeleteDialog(final String userid, final String oid) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("删除订单");
        builder.setMessage("是否删除订单，删除后无法查看该订单！");
        builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                OrderDelete(userid, oid);
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


    class ViewHolder1{

        TextView orderid;
        TextView status;

    }

    class ViewHolder2{

        TextView name;
        TextView adress;

    }

    class ViewHolder3{

        //ImageView icon;
        TextView name;

    }

    class ViewHolder4{

        ImageView icon;
        TextView name;
        TextView price;
        TextView num;
        Button bt;

    }

    class ViewHolder5{

        TextView payMode;

    }

    class ViewHolder6{

        TextView totlePrice;
        TextView freightPrice;
        TextView orderDate;
        Button bt;

    }
}

