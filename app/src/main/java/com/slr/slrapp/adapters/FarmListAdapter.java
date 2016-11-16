package com.slr.slrapp.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.slr.slrapp.R;
import com.slr.slrapp.beans.FarmerPageLIstBean;
import com.slr.slrapp.utils.UiUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/13 0013.
 */
public class FarmListAdapter extends BaseAdapter {
    List<FarmerPageLIstBean> datas;

    public FarmListAdapter (){
        this.datas=new ArrayList<>();
    }
    public void setDatas(List<FarmerPageLIstBean> datas){
        this.datas=datas;
        this.notifyDataSetChanged();
    }
    public void reFreshData(List<FarmerPageLIstBean> datas){
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
        ViewHolder holder=null;
        if (view==null){
            holder=new ViewHolder();
            view= UiUtils.inflate(R.layout.list_item_farmer_fragment);
            holder.img_list_item= (ImageView) view.findViewById(R.id.img_list_item);
            holder.list_item_tv_name= (TextView) view.findViewById(R.id.list_item_tv_name);
            holder.list_item_rbar= (RatingBar) view.findViewById(R.id.list_item_rbar);
            holder.list_item_tv_appraise= (TextView) view.findViewById(R.id.list_item_tv_appraise);
            holder.list_item_tv_goods= (TextView) view.findViewById(R.id.list_item_tv_goods);
            holder.list_item_tv_where= (TextView) view.findViewById(R.id.list_item_tv_where);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        FarmerPageLIstBean farmerPageLIstBean=datas.get(i);
        Picasso.with(UiUtils.getContext()).load(farmerPageLIstBean.imgUrl).error(R.mipmap.error_image2).into(holder.img_list_item);
        holder.list_item_tv_name.setText(farmerPageLIstBean.goodsArea);
        holder.list_item_rbar.setRating(farmerPageLIstBean.rBarCounts);
        holder.list_item_tv_appraise.setText(farmerPageLIstBean.appraiseCounts + "评价");
        holder.list_item_tv_goods.setText(farmerPageLIstBean.farmWhat);
        holder.list_item_tv_where.setText(farmerPageLIstBean.farmwhere);

        return view;
    }

    private class ViewHolder{
        ImageView img_list_item;
        TextView list_item_tv_name;
        RatingBar list_item_rbar;
        TextView list_item_tv_appraise;
        TextView list_item_tv_goods;
        TextView list_item_tv_where;
    }
}
