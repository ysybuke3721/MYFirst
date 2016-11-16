package com.slr.slrapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.slr.slrapp.R;

/**
 * Created by admin on 2016/8/10.
 */
public class OrderDefaultAdapter extends BaseAdapter{

    private Context context;
    private int pic;
    private String str;

    public OrderDefaultAdapter(Context context, int pic, String str){

        this.context = context;
        this.pic = pic;
        this.str = str;

    }


    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // view初始化
        ViewHolder viewHolder = new ViewHolder();
        if (view == null) {

            LayoutInflater inflate = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflate.inflate(R.layout.defaultlayout, null);
            viewHolder.tv = (TextView) view.findViewById(R.id.deaultlayout_tv);
            viewHolder.iv= (ImageView) view.findViewById(R.id.deaultlayout_iv);
            System.out.println("订单状态："+pic);
            if (pic == 0){
                viewHolder.iv.setImageResource(R.mipmap.dingdan);
            }else if (pic == 1){
                viewHolder.iv.setImageResource(R.mipmap.mshoucang);
            }else if (pic == 2){
                viewHolder.iv.setImageResource(R.mipmap.mpingjia);
            }else if (pic == 3){
                viewHolder.iv.setImageResource(R.mipmap.mdefault);
            }else if (pic == 4){
                viewHolder.iv.setImageResource(R.mipmap.pingjia);
            }else if (pic == 5){
                viewHolder.iv.setImageResource(R.mipmap.mxiaoxi);
            }
            viewHolder.tv.setText(str);
            view.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolder) view.getTag();

        }

        return view;
    }

    // 优化adapter，避免重复加载，减少耗时
    private class ViewHolder {

        private ImageView iv;
        private TextView tv;

    }
}
