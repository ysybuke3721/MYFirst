package com.slr.slrapp.activitys;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.slr.slrapp.R;
import com.slr.slrapp.adapters.MyDefaultAdapter;
import com.slr.slrapp.beans.ShoppingCarListBean;
import com.slr.slrapp.utils.ContentValues;
import com.slr.slrapp.utils.UiUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class OrderGoodsListActivity extends BaseActivity {

    ImageView back;
    TextView tvGoodsNum;
    ListView lvGoodsList;

    private int allCount;

    private List<ShoppingCarListBean.Goods> datas = new ArrayList<>();
    private MyDefaultAdapter<ShoppingCarListBean.Goods> adapter;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_order_goods_list;
    }

    @Override
    public void initView() {
//        ButterKnife.bind(this);
        back= (ImageView) findViewById(R.id.back);
        tvGoodsNum= (TextView) findViewById(R.id.tv_goods_num);
        lvGoodsList= (ListView) findViewById(R.id.lv_goods_list);


        datas = (List<ShoppingCarListBean.Goods>) getIntent().getExtras().getSerializable(ContentValues.TO_SEE_LIST);
        //从多订单页面穿过来的数量
        allCount=getIntent().getIntExtra(ContentValues.ALL_COUNT,0);

        tvGoodsNum.setText("共"+allCount+"件");

        if (datas.size() != 0) {
            adapter = new MyDefaultAdapter<ShoppingCarListBean.Goods>(datas) {
                @Override
                public View getAdapterView(int i, View v, ViewGroup viewGroup) {
                    GoodsHolder holder = null;
                    if (v == null) {
                        holder=new GoodsHolder();
                        v = UiUtils.inflate(R.layout.list_item_mutil_list);
                        holder.iv_goods_pic = (ImageView) v.findViewById(R.id.iv_goods_pic);
                        holder.tv_goods_count = (TextView) v.findViewById(R.id.tv_goods_count);
                        holder.tv_goods_name = (TextView) v.findViewById(R.id.tv_goods_name);
                        holder.tv_goods_price = (TextView) v.findViewById(R.id.tv_goods_price);
                        holder.tv_seller_commend = (TextView) v.findViewById(R.id.tv_seller_commend);
                        v.setTag(holder);
                    } else {
                        holder = (GoodsHolder) v.getTag();
                    }
                    ShoppingCarListBean.Goods goods = datas.get(i);
                    Picasso.with(context).load(goods.getGoodsImgUrl()).into(holder.iv_goods_pic);
                    holder.tv_goods_count.setText("x"+goods.getGoodsNum());
                    holder.tv_seller_commend.setText(goods.getPromise());
                    holder.tv_goods_name.setText(goods.getGoodsName());
                    holder.tv_goods_price.setText("￥"+UiUtils.FormatMoneyStyle(goods.getGoodsPrice()));
                    return v;
                }
            };
        }


        back.setOnClickListener(this);


    }

    @Override
    public void initListener() {
        if (adapter != null) {
            lvGoodsList.setAdapter(adapter);
        }


    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
        }

    }


    private class GoodsHolder {
        ImageView iv_goods_pic;
        TextView tv_goods_name, tv_seller_commend, tv_goods_price, tv_goods_count;
    }
}
