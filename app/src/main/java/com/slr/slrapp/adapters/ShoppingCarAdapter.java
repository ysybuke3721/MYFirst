package com.slr.slrapp.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.slr.slrapp.BaseApplication;
import com.slr.slrapp.R;
import com.slr.slrapp.beans.ShoppingCarListBean;
import com.slr.slrapp.beans.simpleNetBean;
import com.slr.slrapp.gson.GsonRequest;
import com.slr.slrapp.utils.ContentValues;
import com.slr.slrapp.utils.ToastUtil;
import com.slr.slrapp.utils.UiUtils;
import com.slr.slrapp.widget.MyNumberButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Administrator
 * Time: 2016/7/11 0011
 * Description: ${todo}(购物车列表adapter)
 * Version V1.0
 */
public class ShoppingCarAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<ShoppingCarListBean> mListGoods;
    private ToastUtil toastUtil;

    int totalCount = 0;
    double totalPrice = 0.00;

    OnGoodsCheckedChangeListener onGoodsCheckedChangeListener;
    OnCheckHasGoodsListener onCheckHasGoodsListener;
    OnAllCheckedBoxNeedChangeListener onAllCheckedBoxNeedChangeListener;

    OnCountChangeListener onCountChangeListener;

    public void setOnCountChangeListener(OnCountChangeListener onCountChangeListener) {
        this.onCountChangeListener = onCountChangeListener;
    }

    public void setOnGoodsCheckedChangeListener(OnGoodsCheckedChangeListener onGoodsCheckedChangeListener) {
        this.onGoodsCheckedChangeListener = onGoodsCheckedChangeListener;
    }

    public void setOnCheckHasGoodsListener(OnCheckHasGoodsListener onCheckHasGoodsListener) {
        this.onCheckHasGoodsListener = onCheckHasGoodsListener;
    }

    public void setOnAllCheckedBoxNeedChangeListener(OnAllCheckedBoxNeedChangeListener onAllCheckedBoxNeedChangeListener) {
        this.onAllCheckedBoxNeedChangeListener = onAllCheckedBoxNeedChangeListener;
    }

    public ShoppingCarAdapter(Context context, List<ShoppingCarListBean> mListGoods) {

        this.context = context;
        this.mListGoods = mListGoods;


    }

    public ShoppingCarAdapter(Context context) {
        this.context = context;
        this.mListGoods = new ArrayList<>();
    }

    public void setData(List<ShoppingCarListBean> mListGoods) {
        this.mListGoods = mListGoods;
        this.notifyDataSetChanged();
    }

    //获取当前父item的数据数量
    @Override
    public int getGroupCount() {
        //牧场的个数
        return mListGoods.size();

    }

    //获取当前父item下的子item的个数
    @Override
    public int getChildrenCount(int i) {
        //每个牧场下购买的品种数量
        return mListGoods.get(i).getmGoods().size();

    }

    //获取当前父item的数据
    @Override
    public Object getGroup(int i) {
        return mListGoods.get(i);
    }

    //得到子item需要关联的数据
    @Override
    public Object getChild(int i, int i1) {
        return mListGoods.get(i).getmGoods().get(i1);
    }

    //得到父item的ID
    @Override
    public long getGroupId(int i) {
        return i;
    }

    //得到子item的ID
    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
//        return true;
    }

    @Override
    public View getGroupView(final int i, final boolean b, View view, ViewGroup viewGroup) {
        GroupViewHolder holder = null;
        if (view == null) {
            holder = new GroupViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_elv_group_farm, null);
            holder.tvGroup = (TextView) view.findViewById(R.id.tvShopNameGroup);
            holder.ivGroup = (ImageView) view.findViewById(R.id.ivGroup);
            holder.ivCheckGroup = (CheckBox) view.findViewById(R.id.ivCheckGroup);
            view.setTag(holder);
        } else {
            holder = (GroupViewHolder) view.getTag();
        }

        //获取数据源填充头布局
        final ShoppingCarListBean bean = mListGoods.get(i);
        //牧场名字
        holder.tvGroup.setText(bean.getFarmName());

        //加载牧场图标
        Picasso.with(UiUtils.getContext()).load(bean.getFarmImgUrl()).into(holder.ivGroup);
        //点击是否选中（点击圆圈）
        holder.ivCheckGroup.setChecked(bean.isChecked());
        holder.ivCheckGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //让自己的圆圈选中  让子view的圆圈也选中
                setupChecked(i);
                //控制总checkedbox 接口
                onAllCheckedBoxNeedChangeListener.onCheckedBoxNeedChange(dealAllParentIsChecked());
            }
        });
        //覆盖原有收起展开事件
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "店铺：" + bean.getFarmName(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    //让小标题自己的圆圈选中  让子view的圆圈也选中
    private void setupChecked(int i) {
        ShoppingCarListBean bean = mListGoods.get(i);
        boolean isChecked = !bean.isChecked();
        bean.setChecked(isChecked);
        List<ShoppingCarListBean.Goods> childsBeans = bean.getmGoods();
        for (int j = 0; j < childsBeans.size(); j++) {
            ShoppingCarListBean.Goods child = childsBeans.get(j);
            child.setChecked(isChecked);
        }
        notifyDataSetChanged();

        dealPrice();
    }

    //处理如果所有条目选中了，标题也要被选中的情况
    public boolean dealOneParentAllChildIsChecked(int groupPosition) {
        List<ShoppingCarListBean.Goods> childMapList = mListGoods.get(groupPosition).getmGoods();
        for (int j = 0; j < childMapList.size(); j++) {
            ShoppingCarListBean.Goods goodsBean = childMapList.get(j);
            if (!goodsBean.isChecked()) {
                return false;//如果有一个没选择  就false
            }
        }
        return true;
    }


    //处理所有标题被选中的情况
    public boolean dealAllParentIsChecked() {
        for (int i = 0; i < mListGoods.size(); i++) {
            ShoppingCarListBean storeBean = mListGoods.get(i);
            if (!storeBean.isChecked()) {
                return false;//如果有一个没选择  就false
            }
        }
        return true;
    }

    //供全选按钮调用
    public void setupAllChecked(boolean isChecked) {


        for (int i = 0; i < mListGoods.size(); i++) {
            ShoppingCarListBean storeBean = mListGoods.get(i);
            storeBean.setChecked(isChecked);

            List<ShoppingCarListBean.Goods> childMapList = mListGoods.get(i).getmGoods();
            for (int j = 0; j < childMapList.size(); j++) {
                ShoppingCarListBean.Goods goodsBean = childMapList.get(j);
                goodsBean.setChecked(isChecked);
            }
        }
        notifyDataSetChanged();
        dealPrice();
    }


    /*    i 是父标题的序号，i1是子条目的序号*/
    @Override
    public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
        ChildViewHolder holder = null;
        if (view == null) {
            holder = new ChildViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.list_item_shopping_car, viewGroup, false);
            holder.tvChild = (TextView) view.findViewById(R.id.item_shopping_car_name);
            holder.ivChild = (ImageView) view.findViewById(R.id.item_shopping_car_icon);
            holder.tvPrice = (TextView) view.findViewById(R.id.item_shopping_car_price);
            holder.tv_promise = (TextView) view.findViewById(R.id.item_shopping_car_content);
//            holder.ll_add_sub_btn = (LinearLayout) view.findViewById(R.id.ll_add_sub_btn);
//            holder.ll_add_sub_btn.addView(new AddAndSubView(UiUtils.getContext()));
            holder.number_button = (MyNumberButton) view.findViewById(R.id.number_button);
            holder.iv_trash = (ImageView) view.findViewById(R.id.iv_trash);
            holder.product_check = (CheckBox) view.findViewById(R.id.product_check);
            view.setTag(holder);

        } else {

            holder = (ChildViewHolder) view.getTag();
        }

        //TODO　 数据源
        final ShoppingCarListBean.Goods childbeans = mListGoods.get(i).getmGoods().get(i1);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUtil.showTextToast(childbeans.getGoodsName());
            }
        });


        //TODO   设置最大购买数量和库存
        holder.number_button.setBuyMax(childbeans.getMaxBuyNum());//.setInventory(10000);

        //单独加tag解决数据复用
        holder.number_button.setTag(childbeans);

        //数量增加按钮(设置当前的数量)
        holder.number_button.setCurrentNumber(childbeans.getGoodsNum());

        holder.tv_promise.setText(childbeans.getPromise());

        final ChildViewHolder finalHolder = holder;
        holder.number_button.setOnNumChangeListener(new MyNumberButton.OnNumChangeListener() {
            @Override
            public void onNumChange(View view, int num) {
                ShoppingCarListBean.Goods goods = (ShoppingCarListBean.Goods) finalHolder.number_button.getTag();  //获取tag
                //网络请求改变数量，然后改变价格
                changeNetGoosNum(goods, num);

            }
        });

        //数量增加按钮的保险监听
        holder.number_button.setOnWarnListener(new MyNumberButton.OnWarnListener() {
            @Override
            public void onWarningForInventory(int inventory) {
                //到了最大库存
            }

            @Override
            public void onWarningForBuyMax(int max) {
                //到了最大购买量
            }
        });
        //点击checkbox
        holder.product_check.setChecked(childbeans.isChecked());
        holder.product_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final boolean nowBeanChecked = childbeans.isChecked();
                //更新数据
                childbeans.setChecked(!nowBeanChecked);
                boolean isOneParentAllChildIsChecked = dealOneParentAllChildIsChecked(i);
                ShoppingCarListBean storeBean = mListGoods.get(i);
                storeBean.setChecked(isOneParentAllChildIsChecked);


                notifyDataSetChanged();
                //控制总checkedbox 接口
                onAllCheckedBoxNeedChangeListener.onCheckedBoxNeedChange(dealAllParentIsChecked());
                dealPrice();
            }
        });

        holder.tvChild.setText(childbeans.getGoodsName());

        Picasso.with(UiUtils.getContext()).load(childbeans.getGoodsImgUrl())
                .error(R.mipmap.error_image2).into(holder.ivChild);

        holder.tvPrice.setText("￥" + UiUtils.FormatMoneyStyle(childbeans.getGoodsPrice()));


        //点击了删除
        holder.iv_trash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                removeOneGood(i, i1);
                OrderDeleteDialog(i, i1);

            }
        });

        return view;
    }


    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }







    //删除某个商品
    public void removeOneGood(int groupPosition, int childPosition) {
        if (mListGoods != null && mListGoods.size() > 0) {

            List<ShoppingCarListBean.Goods> childesBeans = mListGoods.get(groupPosition).getmGoods();
            //商品删除的监听
            if (childesBeans.size() != 0) {
                onCountChangeListener.onCountChange(childesBeans.get(childPosition), mListGoods.get(groupPosition).getFarmId());
            }
            //删除的逻辑放到购物车对象管理类中去处理
            childesBeans.remove(childPosition);
            //通过子项
            if (childesBeans != null && childesBeans.size() > 0) {

            } else {
//            childesBeans.remove(groupPosition);
                mListGoods.remove(groupPosition);
            }
            notifyDataSetChanged();

            if (mListGoods != null && mListGoods.size() > 0) {
                onCheckHasGoodsListener.onCheckHasGoods(true);//
            } else {
                onCheckHasGoodsListener.onCheckHasGoods(false);//
            }
            notifyDataSetChanged();
            dealPrice();
        }
    }

    //删除所有商品
    public void removeGoods() {


        for (int i = mListGoods.size() - 1; i >= 0; i--) {//倒过来遍历  remove
            ShoppingCarListBean storeBean = mListGoods.get(i);
            if (storeBean.isChecked()) {
                mListGoods.remove(i);
//                childMapList_list.remove(i);
            } else {
                List<ShoppingCarListBean.Goods> childMapList = mListGoods.get(i).getmGoods();
                for (int j = childMapList.size() - 1; j >= 0; j--) {//倒过来遍历  remove
                    ShoppingCarListBean.Goods goodsBean = childMapList.get(j);
                    if (goodsBean.isChecked()) {
                        childMapList.remove(j);
                    }
                }
            }

        }

        //!!!!!!!!!!!!!!!删除完 状态需要重置   待思考  why？
        if (mListGoods != null && mListGoods.size() > 0) {
            onCheckHasGoodsListener.onCheckHasGoods(true);//
        } else {
            onCheckHasGoodsListener.onCheckHasGoods(false);//
        }
        notifyDataSetChanged();//
        dealPrice();//重新计算
    }

    /**
     * 显示删除通知对话框
     */
    private void OrderDeleteDialog(final int i, final int i1) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("删除商品");
        builder.setMessage("是否确定删除，删除后无法在购物车直接查看购买！");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
//                deleteNetGood(String.valueOf(goods.getDeleteId()), goods, farm_id);
//                adapter.notifyDataSetChanged();
                removeOneGood(i, i1);
            }
        });
        builder.setNegativeButton("返回", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
//        builder.show();
    }


    //处理价钱
    public void dealPrice() {
        // showList();
        totalCount = 0;
        totalPrice = 0.00;
        for (int i = 0; i < mListGoods.size(); i++) {

            List<ShoppingCarListBean.Goods> childMapList = mListGoods.get(i).getmGoods();
            for (int j = 0; j < childMapList.size(); j++) {
                ShoppingCarListBean.Goods goodsBean = childMapList.get(j);
                int count = goodsBean.getGoodsNum();
                double discountPrice = goodsBean.getGoodsPrice();
                if (goodsBean.isChecked()) {
                    totalCount++;//单品多数量只记1
                    totalPrice += discountPrice * count;
                }

            }
        }
        //计算回调
        onGoodsCheckedChangeListener.onGoodsCheckedChange(totalCount, totalPrice);
    }

    //
    public interface OnCheckHasGoodsListener {
        void onCheckHasGoods(boolean isHasGoods);
    }

    /*当删除了商品时候的监听*/
    public interface OnCountChangeListener {
        void onCountChange(ShoppingCarListBean.Goods goods, String farm_id);
    }

    /*当商品选中状态改变之后的监听*/
    public interface OnGoodsCheckedChangeListener {
        void onGoodsCheckedChange(int totalCount, double totalPrice);
    }

    /*点击全选按钮所调用的接口*/
    public interface OnAllCheckedBoxNeedChangeListener {
        void onCheckedBoxNeedChange(boolean allParentIsChecked);
    }


    //改变数量网络请求
    private void changeNetGoosNum(final ShoppingCarListBean.Goods comid, final int comnum) {
        String url = ContentValues.CHANGE_SHOP_CAR_GOODS_COUNT_URL;
        String userId = UiUtils.getContext().getSharedPreferences(ContentValues.SP_NAME, 0).getString(ContentValues.USER_ID, "");
        Map<String, String> map = new HashMap<>();
        map.put("comid", comid.getGoodsId()); //商品id
        map.put("comnum", String.valueOf(comnum));
        map.put("userId", userId);
        GsonRequest<simpleNetBean> changeNum = new GsonRequest<simpleNetBean>(map, url, simpleNetBean.class, new Response.Listener<simpleNetBean>() {
            @Override
            public void onResponse(simpleNetBean response) {

                if (response != null) {
                    int code = response.getCode();
                    if (code == 101) {
                        ToastUtil.showTextToast("已达到库存上限，无法添加");
                        return;
                    }
                    if (code == 200) {
                        //添加成功
                        comid.setGoodsNum(comnum);
                        dealPrice();
                    }
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtil.showTextToast("网络请求出现异常");
            }
        });

        BaseApplication.getInstance().getRequestQueue().add(changeNum);
    }

    private static class GroupViewHolder {
        TextView tvGroup;
        ImageView ivGroup;
        CheckBox ivCheckGroup;
    }

    class ChildViewHolder {
        /*增加数量按钮*/
        MyNumberButton number_button;

        /**
         * 商品图片
         */
        ImageView ivChild;
        /**
         * 商品名称
         */
        TextView tvChild;
        /*商家承诺*/
        TextView tv_promise;

        /**
         * 商品规格
         */
        TextView tvGoodsParam;
        /**
         * 选中
         */
        CheckBox product_check;
        /**
         * 非编辑状态
         */
        LinearLayout llGoodInfo;
        /**
         * 编辑状态
         */
        RelativeLayout rlEditStatus;
        /**
         * +1
         */
        ImageView ivAdd;
        /**
         * -1
         */
        ImageView ivReduce;
        /**
         * 删除
         */
        TextView tvDel;
        /**
         * 价格
         */
        TextView tvPrice;

        /**
         * 编辑状态的数量
         */
        TextView tvNum2;


        /*删除图标*/
        ImageView iv_trash;


    }


}
