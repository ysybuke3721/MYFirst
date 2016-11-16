package com.slr.slrapp.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.slr.slrapp.R;
import com.slr.slrapp.beans.FarmerPageLIstBean;
import com.slr.slrapp.utils.UiUtils;
import com.squareup.picasso.Picasso;

/**
 * 填充第二个页面listview的holder
 * Created by Administrator on 2016/7/2 0002.
 */
public class FarmPageItemHolder extends BaseHolder<FarmerPageLIstBean> {


    ImageView img_list_item;
    TextView list_item_tv_name;
    RatingBar list_item_rbar;
    TextView list_item_tv_appraise;
    TextView list_item_tv_goods;
    TextView list_item_tv_where;

    @Override
    protected View initView() {
        View view=UiUtils.inflate(R.layout.list_item_farmer_fragment);
        img_list_item= (ImageView) view.findViewById(R.id.img_list_item);
        list_item_tv_name= (TextView) view.findViewById(R.id.list_item_tv_name);
        list_item_rbar= (RatingBar) view.findViewById(R.id.list_item_rbar);
        list_item_tv_appraise= (TextView) view.findViewById(R.id.list_item_tv_appraise);
        list_item_tv_goods= (TextView) view.findViewById(R.id.list_item_tv_goods);
        list_item_tv_where= (TextView) view.findViewById(R.id.list_item_tv_where);

        return view;
    }

    @Override
    protected void refreshView(FarmerPageLIstBean farmerPageLIstBean) {
//        img_list_item.setImageResource(farmerPageLIstBean.imgUrl);
        Picasso.with(UiUtils.getContext()).load(farmerPageLIstBean.imgUrl).into(img_list_item);
        list_item_tv_name.setText(farmerPageLIstBean.goodsArea);
        list_item_rbar.setRating(farmerPageLIstBean.rBarCounts);
        list_item_tv_appraise.setText(farmerPageLIstBean.appraiseCounts + "评价");
        list_item_tv_goods.setText(farmerPageLIstBean.farmWhat);
        list_item_tv_where.setText(farmerPageLIstBean.farmwhere);
    }
}
