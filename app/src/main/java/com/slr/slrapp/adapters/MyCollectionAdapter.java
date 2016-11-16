package com.slr.slrapp.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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
import com.slr.slrapp.activitys.FarmCameraActivity;
import com.slr.slrapp.beans.DefaultBean;
import com.slr.slrapp.beans.MyCollectBean;
import com.slr.slrapp.gson.ApiUtils;
import com.slr.slrapp.gson.GsonRequest;
import com.slr.slrapp.utils.ContentValues;
import com.slr.slrapp.utils.ToastUtil;
import com.slr.slrapp.utils.UiUtils;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UserInfoBean: Administrator
 * Time: 2016/7/18 0018
 * Description: ${todo}(我的收藏)
 * Version V1.0
 */
public class MyCollectionAdapter extends BaseAdapter{
    private Context context;
    private List<MyCollectBean.ListBean> list;

    public MyCollectionAdapter(Context context, List<MyCollectBean.ListBean> list){
        this.context = context;
        this.list = list;
    }

    public void refreshItem(List<MyCollectBean.ListBean> list) {
        this.list.clear();
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }

    public void addAllItem(List<MyCollectBean.ListBean> list) {
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = UiUtils.inflate(R.layout.list_item_collection);
            viewHolder.my_collection_icon_iv = (ImageView) convertView.findViewById(R.id.my_collection_icon_iv);
            viewHolder.my_collection_name_tv = (TextView) convertView.findViewById(R.id.my_collection_name_tv);
            viewHolder.my_collection_price_tv = (TextView) convertView.findViewById(R.id.my_collection_price_tv);
            viewHolder.my_collection_bt = (Button) convertView.findViewById(R.id.my_collection_bt);
            viewHolder.delete = (Button) convertView.findViewById(R.id.my_collection_delete);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final MyCollectBean.ListBean listBean = list.get(i);
        Picasso.with(context).load(listBean.getSmallPhoto()).error(R.mipmap.error_image2).into(viewHolder.my_collection_icon_iv);
        viewHolder.my_collection_name_tv.setText(listBean.getComName());
        viewHolder.my_collection_price_tv.setText("¥" + listBean.getComPrice());
        viewHolder.my_collection_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listBean.getComStatus().equals("1")){
                    Intent intent = new Intent(context, FarmCameraActivity.class);
                    intent.putExtra("goodsId", Integer.parseInt(listBean.getComId()));
                    System.out.println("收藏的id"+Integer.parseInt(listBean.getComId()));
                    context.startActivity(intent);
                }else {
                    ToastUtil.showTextToast("该商品已下架！");
                }

            }
        });
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId = context.getSharedPreferences(ContentValues.SP_NAME, 0).getString(ContentValues.USER_ID, null);
                OrderDeleteDialog(userId, listBean.getCid()+"", i);
            }
        });
        return convertView;
    }

    /**
     * 显示删除通知对话框
     */
    private void OrderDeleteDialog(final String userid, final String id, final int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("取消收藏");
        builder.setMessage("是否确定取消收藏，取消后无法查看该收藏内容！");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                CollectionDelete(userid, id, i);
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

    //  取消收藏
    private void CollectionDelete(String userid, String id, final int i){

        Map<String, String> map = new HashMap<>();
        map.put("userId", userid);
        map.put("id", id);
        GsonRequest<DefaultBean> gsonRequest = new GsonRequest<>(map,
                ApiUtils.CollectDelete(),
                DefaultBean.class,
                new Response.Listener<DefaultBean>() {
                    @Override
                    public void onResponse(DefaultBean session) {

                        ToastUtil.showTextToast(session.getMessage());

                        if (session.getCode()==200) {

                            list.remove(i);
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

    // 优化adapter，避免重复加载，减少耗时
    private class ViewHolder {

        private ImageView my_collection_icon_iv;
        private Button my_collection_bt, delete;
        private TextView my_collection_name_tv, my_collection_price_tv;

    }
}
