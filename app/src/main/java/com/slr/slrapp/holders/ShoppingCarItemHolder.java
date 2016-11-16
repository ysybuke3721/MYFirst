package com.slr.slrapp.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.slr.slrapp.R;
import com.slr.slrapp.beans.ShoppingCarListBean;
import com.slr.slrapp.utils.UiUtils;

/**
 * User: Administrator
 * Time: 2016/7/4 0004
 * Description: ${todo}(用一句话描述该文件做什么)
 * Version V1.0
 */
public class ShoppingCarItemHolder extends BaseHolder<ShoppingCarListBean> {


    private ImageView item_shopping_car_farm_icon;
    private ImageView item_shopping_car_icon;
    private TextView item_shopping_car_name;
    private TextView item_shopping_car_content;
    private TextView item_shopping_car_price;
    private TextView item_shopping_car_farm_name;

    @Override
    protected View initView() {
        View view = UiUtils.inflate(R.layout.list_item_shopping_car);
        item_shopping_car_icon = (ImageView) view.findViewById(R.id.item_shopping_car_icon);
        item_shopping_car_name = (TextView) view.findViewById(R.id.item_shopping_car_name);
        item_shopping_car_content = (TextView) view.findViewById(R.id.item_shopping_car_content);
        item_shopping_car_price = (TextView) view.findViewById(R.id.item_shopping_car_price);
        return view;
    }

    @Override
    protected void refreshView(ShoppingCarListBean shoppingCarListBean) {
        item_shopping_car_farm_name.setText(shoppingCarListBean.getFarmName());

    }
}
