package com.slr.slrapp.holders;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.slr.slrapp.R;
import com.slr.slrapp.adapters.MyDefaultAdapter;
import com.slr.slrapp.beans.ShoppingCarListBean;
import com.slr.slrapp.utils.ToastUtil;
import com.slr.slrapp.utils.UiUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * 多商品支付要用到的holder
 * Created by Administrator on 2016/8/2 0002.
 */
public class MutilOrderPayHolder extends BaseHolder<ShoppingCarListBean> {
    ImageView iv_farm_logo;
    TextView tv_farm_name;
    com.slr.slrapp.widget.MyDoubleListview  ll_farm_goods;
    LinearLayout ll_item_head;
    LinearLayout ll_item_foot;
    RelativeLayout rl_delivery_style;
    TextView tv_delivery_style;
    RelativeLayout rl_receive_time;
    TextView tv_receive_time;
    RelativeLayout rl_pay_style;
    TextView tv_pay_style;
    TextView tv_all_prices;
    TextView tv_yunfei;
    EditText et_leave_message;
    ImageView iv_go;
    ImageView iv_go_1;
    ImageView iv_go_2;
    //四个固定的字
    TextView tv_delivery;
    TextView tv_receive;
    TextView tv_pay_how;
    TextView tv_total_goods;
    TextView tv_yun_fei;

    @Override
    protected View initView() {
        View view = UiUtils.inflate(R.layout.item_ensure_order);

        iv_farm_logo = (ImageView) view.findViewById(R.id.iv_farm_logo);
        tv_farm_name = (TextView) view.findViewById(R.id.tv_farm_name);
        ll_farm_goods = (com.slr.slrapp.widget.MyDoubleListview) view.findViewById(R.id.ll_item_item);
        ll_item_head = (LinearLayout) view.findViewById(R.id.ll_item_head);
        ll_item_foot = (LinearLayout) view.findViewById(R.id.ll_item_foot);
        rl_delivery_style = (RelativeLayout) view.findViewById(R.id.rl_delivery_style);
        tv_delivery_style = (TextView) view.findViewById(R.id.tv_delivery_style);
        rl_receive_time = (RelativeLayout) view.findViewById(R.id.rl_receive_time);
        tv_receive_time = (TextView) view.findViewById(R.id.tv_receive_time);
        rl_pay_style = (RelativeLayout) view.findViewById(R.id.rl_pay_style);
        tv_pay_style = (TextView) view.findViewById(R.id.tv_pay_style);
        tv_all_prices = (TextView) view.findViewById(R.id.tv_all_prices);
        tv_yunfei = (TextView) view.findViewById(R.id.tv_yunfei);
        et_leave_message = (EditText) view.findViewById(R.id.et_leave_message);
        iv_go = (ImageView) view.findViewById(R.id.iv_go);
        iv_go_1 = (ImageView) view.findViewById(R.id.iv_go_1);
        iv_go_2 = (ImageView) view.findViewById(R.id.iv_go_2);

        tv_delivery = (TextView) view.findViewById(R.id.tv_delivery);
        tv_receive = (TextView) view.findViewById(R.id.tv_receive);
        tv_pay_how = (TextView) view.findViewById(R.id.tv_pay_how);
        tv_total_goods = (TextView) view.findViewById(R.id.tv_total_goods);
        tv_yun_fei = (TextView) view.findViewById(R.id.tv_yun_fei);
        //添加头布局和脚布局
//        ll_farm_goods.addHeaderView(ll_item_head);
//        ll_farm_goods.addFooterView(ll_item_foot);
        return view;
    }

    @Override
    protected void refreshView(ShoppingCarListBean shoppingCarListBean) {
        if (shoppingCarListBean != null && shoppingCarListBean.getmGoods().size() != 0) {

            final List<ShoppingCarListBean.Goods> goodses = shoppingCarListBean.getmGoods();
            //固定的数据
            iv_go.setImageResource(R.mipmap.jinru);
            iv_go_1.setImageResource(R.mipmap.jinru);
            iv_go_2.setImageResource(R.mipmap.jinru);
            et_leave_message.setHint(UiUtils.getResources().getString(R.string.leave_message));
            tv_delivery.setText(UiUtils.getResources().getString(R.string.devliver_style));
            tv_receive.setText(UiUtils.getResources().getString(R.string.receive_time));
            tv_pay_how.setText(UiUtils.getResources().getString(R.string.pay_style));
            tv_total_goods.setText(UiUtils.getResources().getString(R.string.all_goods_prices));
            tv_yun_fei.setText(UiUtils.getResources().getString(R.string.yun_fei));
            //要改变的文字的默认值
            tv_delivery_style.setText(R.string.word_deliver);
            tv_receive_time.setText("必填的哦");
            tv_pay_style.setText("在线支付");
            tv_all_prices.setText("￥0.0");
            tv_yunfei.setText("+￥0.00");
            //监听
            rl_delivery_style.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastUtil.showTextToast("送货方式");
                }
            });

            rl_receive_time.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastUtil.showTextToast("收货时间");
                }
            });
            rl_pay_style.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastUtil.showTextToast("支付方式");
                }
            });
            //设置适配器
            ll_farm_goods.setAdapter(new MyDefaultAdapter<ShoppingCarListBean.Goods>(goodses) {
                @Override
                public View getAdapterView(int i, View v, ViewGroup viewGroup) {
                    ListItemHolder holder = null;
                    if (v == null) {
                        holder = new ListItemHolder();
                        v = UiUtils.inflate(R.layout.list_item_inner_item);
                        holder.iv_goods_pic = (ImageView) v.findViewById(R.id.iv_goods_pic);
                        holder.tv_goods_name = (TextView) v.findViewById(R.id.tv_goods_name);
                        holder.tv_seller_commend = (TextView) v.findViewById(R.id.tv_seller_commend);
                        holder.tv_goods_price = (TextView) v.findViewById(R.id.tv_goods_price);
                        holder.tv_goods_count = (TextView) v.findViewById(R.id.tv_goods_count);
                        v.setTag(holder);
                    } else {
                        holder = (ListItemHolder) v.getTag();
                    }
                    ShoppingCarListBean.Goods goods = goodses.get(i);
//                    holder.iv_goods_pic.setImageResource(goods.getGoodsImgUrl());
                    Picasso.with(UiUtils.getContext()).load(goods.getGoodsImgUrl()).into(holder.iv_goods_pic);
                    holder.tv_goods_name.setText(goods.getGoodsName());

                    holder.tv_goods_count.setText("x" + goods.getGoodsNum());
                    holder.tv_seller_commend.setText("商家承诺两小时内发货");
                    holder.tv_goods_price.setText("￥"+goods.getGoodsPrice());
                    return v;
                }
            });
            //条目点击事件
            ll_farm_goods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    //TODO  根据商品id进入商品页面
                    ToastUtil.showTextToast("进入条目"+i);

                }
            });
            //输入框的监听
            et_leave_message.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void afterTextChanged(Editable editable) {
                        //TODO  获取输入的内容
                }
            });


        }

    }

    private class ListItemHolder {
        ImageView iv_goods_pic;
        TextView tv_goods_name;
        TextView tv_seller_commend;
        TextView tv_goods_price;
        TextView tv_goods_count;

    }
}
