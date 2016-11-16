package com.slr.slrapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.slr.slrapp.R;
import com.slr.slrapp.beans.MyWalletBean;
import com.slr.slrapp.utils.ToastUtil;
import com.slr.slrapp.utils.UiUtils;
import com.squareup.picasso.Picasso;

/**
 * User: Administrator
 * Time: 2016/7/20 0020
 * Description: ${todo}(我的钱包)
 * Version V1.0
 */
public class MyWalletAdapter extends BaseAdapter {
    private Context context;
    private ToastUtil toastUtil;
    private MyWalletBean myWalletBean;

    public MyWalletAdapter(Context context, MyWalletBean myWalletBean){

        this.myWalletBean = myWalletBean;
        this.context = context;

    }

    @Override
    public int getCount() {
        return myWalletBean.getList().size();
    }

    @Override
    public Object getItem(int i) {
        return myWalletBean.getList().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = new ViewHolder();
        toastUtil = new ToastUtil();
        if (convertView == null){

            LayoutInflater inflate = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflate.inflate(R.layout.list_item_wallet, null);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.my_wallet_icon_iv);
            viewHolder.name = (TextView) convertView.findViewById(R.id.my_wallet_name_tv);
            viewHolder.time = (TextView) convertView.findViewById(R.id.my_wallet_item);
            viewHolder.price = (TextView) convertView.findViewById(R.id.my_wallet_money_tv);

            Picasso.with(context).load(myWalletBean.getList().get(i).getHead())
                    .error(R.mipmap.icon_default)
                    .into(viewHolder.icon);
            viewHolder.name.setText(myWalletBean.getList().get(i).getNickName());
            viewHolder.time.setText(myWalletBean.getList().get(i).getName()+":"+myWalletBean.getList().get(i).getCreateDate());
            viewHolder.price.setText(UiUtils.FormatMoneyStyle(myWalletBean.getList().get(i).getPrice()));
        }else{

            viewHolder = (ViewHolder) convertView.getTag();

        }

        return convertView;
    }


    // 优化adapter，避免重复加载，减少耗时
    private class ViewHolder {

        private ImageView icon;
        private TextView name;
        private TextView time;
        private TextView price;

    }
}
