package com.slr.slrapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.slr.slrapp.BaseApplication;
import com.slr.slrapp.R;
import com.slr.slrapp.activitys.GoToCommentActivity;
import com.slr.slrapp.activitys.MyOrderDetailActivity;
import com.slr.slrapp.activitys.OrderDetailsActivity;
import com.slr.slrapp.beans.DefaultBean;
import com.slr.slrapp.beans.MyOrderBean;
import com.slr.slrapp.gson.ApiUtils;
import com.slr.slrapp.gson.GsonRequest;
import com.slr.slrapp.utils.ContentValues;
import com.slr.slrapp.utils.ToastUtil;
import com.slr.slrapp.utils.UiUtils;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 我的订单
 * Created by admin on 2016/8/8.
 */
public class MyOrderAdapter extends BaseAdapter{

    private Context context;
    private MyOrderBean orderBean = null;
    private int y;
    private int mOrderState[];
    private Map<String, String> map = new HashMap<String, String>();

    public MyOrderAdapter(Context context, MyOrderBean orderBean){

        this.orderBean = orderBean;
        this.context = context;
        mOrderState = new int[orderBean.getCartList().size()];

    }
    @Override
    public int getCount() {
        return orderBean.getCartList().size();
    }

    @Override
    public Object getItem(int i) {
        return orderBean.getCartList().get(i);
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

        String OrderState = orderBean.getCartList().get(i).getState();
        viewHolder.order_type.setText(OrderState);
        viewHolder.order_price.setText("共"+orderBean.getCartList().get(i).getTotleNumber()+ "件商品 合计：" + UiUtils.FormatMoneyStyle(
                orderBean.getCartList().get(i).getTotlePrice()+"")
                +"（含运费¥0.00）");
        // 获取订单id和对应号
        map.put(i+"", orderBean.getCartList().get(i).getOrderNumber());


        y = orderBean.getCartList().get(i).getList().size();

        if (y > 1){

            viewHolder.rl1.setVisibility(View.INVISIBLE);
            viewHolder.ll2.setVisibility(View.VISIBLE);
            viewHolder.farm_name.setText("原生态养殖");

            for (int n = 0; n < y; n++) {
                ImageView iv = new ImageView(context);
                Picasso.with(context).load(orderBean.getCartList().get(i).getList().get(n).getSmallPhoto())
                        .error(R.mipmap.error_image2).into(iv);
                iv.setPadding(10,0,0,0);
                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                iv.setLayoutParams(new Gallery.LayoutParams(180, 180));
                viewHolder.ll2.addView(iv);
            }

        }else{
            viewHolder.rl1.setVisibility(View.VISIBLE);
            viewHolder.ll2.setVisibility(View.INVISIBLE);
            viewHolder.product_num.setText("x"+orderBean.getCartList().get(i).getList().get(0).getComNumber());
            viewHolder.product_price.setText(UiUtils.FormatMoneyStyle(orderBean.getCartList().get(i).getList().get(0).getSzprice()+"")
            );
            viewHolder.farm_name.setText(orderBean.getCartList().get(i).getList().get(0).getStoreName());
            Picasso.with(context).load(orderBean.getCartList().get(i).getList().get(0).getSmallPhoto()).into(viewHolder.product_icon);

        }


        if (OrderState.equals("未付款")){
            mOrderState[i] = 1;
            viewHolder.bt1.setText("去付款");

        }else if (OrderState.equals("待出库")){

            mOrderState[i] = 2;
            viewHolder.bt1.setVisibility(View.INVISIBLE);

        }else if (OrderState.equals("已发货")){

            mOrderState[i] = 3;
            viewHolder.bt1.setText("确定收货");

        }else if (OrderState.equals("已收货")){

            mOrderState[i] = 4;
            viewHolder.bt1.setText("去评价");

        }else{

//            mOrderState[i] = 5;
//            viewHolder.bt1.setText("再次购买");
            viewHolder.bt1.setVisibility(View.GONE);

        }
        // 查看详情
        viewHolder.bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, MyOrderDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("OrderDetail", orderBean.getCartList().get(i).getOid()+"");
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        // 订单操作：付款、收货、评价、再次购买
        viewHolder.bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mOrderState[i] == 1){

                    if (orderBean.getCartList().get(i).getList().size()==1){
                        ToastUtil.showTextToast("单个订单支付");
                       // payOneOrder();
                    }else{
                        ToastUtil.showTextToast("多个订单支付");
                    }

                }else if (mOrderState[i] == 2||mOrderState[i] == 3){

                    ConfirmReceipt(orderBean.getCartList().get(i).getOid());

                }else if (mOrderState[i] == 4){

                    Intent intent =new Intent(context, GoToCommentActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("oid", orderBean.getCartList().get(i).getOid()+"");
                    intent.putExtras(bundle);
                    ((Activity)context).startActivityForResult(intent, 0);

                }
//                else {
//
//                    ToastUtil.showTextToast("再次购买");
//                    Intent intent = new Intent(context, FarmCameraActivity.class);
//                    intent.putExtra("goodsId", orderBean.getCartList().get(i).get);
//                    context.startActivity(intent);
//
//                }


            }
        });

        return convertView;
    }

    // 删除某个订单
    private void RemoveOneOrder(int i) {

        if (orderBean.getCartList() != null && orderBean.getCartList().size() > 0){

            this.orderBean.getCartList().remove(i);
            notifyDataSetChanged();

        }

    }

    // 确定收货
    private void ConfirmReceipt(int oid){

        GsonRequest<DefaultBean> getData = new GsonRequest<DefaultBean>(
                ApiUtils.ConfirmReceipt(oid),
                DefaultBean.class,
                new Response.Listener<DefaultBean>() {
                    @Override
                    public void onResponse(DefaultBean response) {

                        ToastUtil.showTextToast(response.getMessage());

                        if (response.getCode()==200){


                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                ToastUtil.showTextToast("");
            }
        });
        BaseApplication.getInstance().getRequestQueue().add(getData);

    }

    // 单个订单的支付跳转
    private void payOneOrder(){

        //商品详情  用于提交订单使用
        MyOrderBean.CartListBean cartListBean = orderBean.getCartList().get(0);

        if (cartListBean != null) {
            Intent intent2 = new Intent(context, OrderDetailsActivity.class);
            int count = cartListBean.getTotleNumber();
            // 提交路径 0 单个订单直接提交
            intent2.putExtra(ContentValues.ORDER_PATH, 2);
            Bundle bundle = new Bundle();
            bundle.putSerializable(ContentValues.GOODS_JSON, (Serializable) cartListBean);
            intent2.putExtras(bundle);
            context.startActivity(intent2);
        }
    }

    //得到选中的条目的数据集合，然后传递给结算页面
//    private List<ShoppingCarListBean> getCheckedList() {
//        List<ShoppingCarListBean> list = new ArrayList<>();
//        ShoppingCarListBean shopBean;
//        ShoppingCarListBean tempBean = null;
//        if (orderBean.getCartList().size() != 0 && orderBean.getCartList() != null) {
//            for (int i = 0; i < orderBean.getCartList().size(); i++) {
//                tempBean = new ShoppingCarListBean();
//                shopBean.setChecked()
//                shopBean.setChecked()
//                shopBean.setChecked()
//                shopBean.setChecked()
//                shopBean.setChecked(); = orderBean.getCartList().get(i);
//                List<ShoppingCarListBean.Goods> goods = shopBean.getmGoods();
//                List<ShoppingCarListBean.Goods> tempGoods = new ArrayList<>();
//                if (goods.size() != 0) {
//                    ShoppingCarListBean.Goods good = null;
//                    for (int j = 0; j < goods.size(); j++) {
//                        good = goods.get(j);
//                        if (good.isChecked()) {
//                            tempGoods.add(good);
//                        }
//                    }
//                    tempBean.setmGoods(tempGoods);
//                }
//                list.add(tempBean);
//            }
//
//        }
//
//        return list;
//
//    }

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
