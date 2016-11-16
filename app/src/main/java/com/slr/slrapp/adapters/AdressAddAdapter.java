package com.slr.slrapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.slr.slrapp.BaseApplication;
import com.slr.slrapp.R;
import com.slr.slrapp.activitys.AdressEditActivity;
import com.slr.slrapp.activitys.MyAdressAddActivity;
import com.slr.slrapp.beans.MyAdressBean;
import com.slr.slrapp.beans.ReturnCodeBean;
import com.slr.slrapp.gson.GsonRequest;
import com.slr.slrapp.utils.ContentValues;
import com.slr.slrapp.utils.ToastUtil;
import com.slr.slrapp.utils.UiUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UserInfoBean: Administrator
 * Time: 2016/7/20 0020
 * Description: ${todo}(收货地址列表)
 * Version V1.0
 */
public class AdressAddAdapter extends BaseAdapter {
    private Context context;
    private List<MyAdressBean.ListBean> list;

    public AdressAddAdapter(Context context, List<MyAdressBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    public void deleteItem(int i) {
        this.list.remove(i);
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
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = UiUtils.inflate(R.layout.list_item_adress_add);
            viewHolder.adress_add_name = (TextView) convertView.findViewById(R.id.adress_add_name);
            viewHolder.adress_add_phone = (TextView) convertView.findViewById(R.id.adress_add_phone);
            viewHolder.adress_add_adress = (TextView) convertView.findViewById(R.id.adress_add_adress);
            viewHolder.adress_add_adress_cb = (CheckBox) convertView.findViewById(R.id.adress_add_adress_cb);
            viewHolder.adress_add_adress_editor = (Button) convertView.findViewById(R.id.adress_add_adress_editor);
            viewHolder.adress_add_adress_delete = (Button) convertView.findViewById(R.id.adress_add_adress_delete);
            viewHolder.rl_address = (RelativeLayout) convertView.findViewById(R.id.rl_address);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final MyAdressBean.ListBean listBean = list.get(i);
        viewHolder.adress_add_name.setText(listBean.getReceiptName());
        viewHolder.adress_add_phone.setText(formatMobile(listBean.getReceiptTel()));
        String areaAndAdress = listBean.getReceiptAddress();
        viewHolder.adress_add_adress.setText(areaAndAdress.substring(0, areaAndAdress.indexOf(",")) + areaAndAdress.substring(areaAndAdress.indexOf(",") + 1));
        if (listBean.getIfAddress().equals("1")) {
            viewHolder.adress_add_adress_cb.setChecked(true);
        } else {
            viewHolder.adress_add_adress_cb.setChecked(false);
        }

        viewHolder.rl_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent();
                int request = intent.getIntExtra("order", 0);

//                if (request == 99) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(ContentValues.KEY_OF_GET_AREA, listBean);
                    intent.putExtras(bundle);
                    ((MyAdressAddActivity) context).setResult(ContentValues.SEND_A_RECEIVE_AREA, intent);
                    ((MyAdressAddActivity) context).finish();
//                }
            }
        });


        // 删除
        viewHolder.adress_add_adress_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String deleteUrl = ContentValues.DELETE_RECEIVE_AREA;
                Map<String, String> deleteMap = new HashMap<String, String>();
                deleteMap.put("id", String.valueOf(listBean.getId()));
                GsonRequest<ReturnCodeBean> deleteRequest = new GsonRequest<ReturnCodeBean>(deleteMap, deleteUrl, ReturnCodeBean.class,
                        new Response.Listener<ReturnCodeBean>() {
                            @Override
                            public void onResponse(ReturnCodeBean response) {
                                if (response.getCode() == 200) {
                                    deleteItem(i);
                                }
                                ToastUtil.showTextToast(response.getMessage());
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ToastUtil.showTextToast("请求网络失败");
                    }
                });
                BaseApplication.getInstance().getRequestQueue().add(deleteRequest);
            }
        });

        // 编辑
        viewHolder.adress_add_adress_editor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AdressEditActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("editAdress", listBean);
                intent.putExtras(bundle);
                ((MyAdressAddActivity) context).startActivityForResult(intent, 1000);
            }
        });

        final ViewHolder finalViewHolder1 = viewHolder;

        // 设为默认
        viewHolder.adress_add_adress_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("0".equals(listBean.getIfAddress())) {
                    finalViewHolder1.adress_add_adress_cb.setChecked(true);
                    listBean.setIfAddress("1");
                    defaultAddress(String.valueOf(listBean.getId()));
                    for (int j = 0; j < list.size(); j++) {
                        if (i != j) {
                            list.get(j).setIfAddress("0");
                        }
                    }
                } else {
                    finalViewHolder1.adress_add_adress_cb.setChecked(true);
                    listBean.setIfAddress("1");
                }
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    /**
     * 手机号加********
     *
     * @param pNumber
     * @return
     */
    private String formatMobile(String pNumber) {
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(pNumber) && pNumber.length() > 6) {
            for (int i = 0; i < pNumber.length(); i++) {
                char c = pNumber.charAt(i);
                if (i >= 3 && i <= 6) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

    /**
     * 添加默认
     *
     * @param id
     */
    private void defaultAddress(String id) {
        String userId=context.getSharedPreferences(ContentValues.SP_NAME,0).getString(ContentValues.USER_ID,null);
        String defaultUrl = ContentValues.CHANGE_DEFAULT_RECEIVE_AREA;
        Map<String, String> defaultMap = new HashMap<String, String>();
        defaultMap.put("id", id);
        defaultMap.put("userId", userId);
        GsonRequest<ReturnCodeBean> defaultRequest = new GsonRequest<ReturnCodeBean>(defaultMap, defaultUrl, ReturnCodeBean.class,
                new Response.Listener<ReturnCodeBean>() {
                    @Override
                    public void onResponse(ReturnCodeBean response) {
                        if (response.getCode() == 200) {
                            ToastUtil.showTextToast("默认地址设置成功");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtil.showTextToast("请求网络失败");
            }
        });
        BaseApplication.getInstance().getRequestQueue().add(defaultRequest);
    }

    // 优化adapter，避免重复加载，减少耗时
    private class ViewHolder {
        private RelativeLayout rl_address;
        private CheckBox adress_add_adress_cb;
        private Button adress_add_adress_editor, adress_add_adress_delete;
        private TextView adress_add_name, adress_add_phone, adress_add_adress;
    }

}
