package com.slr.slrapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.slr.slrapp.BaseApplication;
import com.slr.slrapp.R;
import com.slr.slrapp.activitys.MutilOrderActivity;
import com.slr.slrapp.adapters.ShoppingCarAdapter;
import com.slr.slrapp.adapters.ShoppingCarDefautAdapter;
import com.slr.slrapp.beans.NetShopCarBean;
import com.slr.slrapp.beans.ShopCarManagerBean;
import com.slr.slrapp.beans.ShoppingCarListBean;
import com.slr.slrapp.beans.simpleNetBean;
import com.slr.slrapp.gson.GsonRequest;
import com.slr.slrapp.utils.ContentValues;
import com.slr.slrapp.utils.ToastUtil;
import com.slr.slrapp.utils.UiUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * 这是购物车fragment
 */
public class ShoppingCarFragment extends BaseFragment {

    private List<ShoppingCarListBean> mListGoods;
    private ShoppingCarAdapter adapter;
    private TextView pay;
    private TextView payNum;
    private RelativeLayout payLayout;
    //    private TextView editor;
    private Context context;
    private CheckBox shopping_car_cb;
    private ExpandableListView Elistview;
    private TextView tv_include_mail;
    private int flag1, flag2;
    private int allCount;
    private double allPrice;

    private ShoppingCarDefautAdapter defautAdapter;


    @Override
    public int getLayoutResID() {
        return R.layout.fragment_shopping_car;
    }


    /**
     * Time: 2016/7/4 0004 上午 10:57
     * Description: ${todo}(初始化view)
     * param: ${tags}
     * return: ${return_type}
     */
    @Override
    public void initView(View view) {
        tv_include_mail = (TextView) view.findViewById(R.id.tv_include_mail);
        pay = (TextView) view.findViewById(R.id.shopping_car_bt_pay);
        payNum = (TextView) view.findViewById(R.id.shopping_car_paynum);
        shopping_car_cb = (CheckBox) view.findViewById(R.id.shopping_car_cb);
        payLayout = (RelativeLayout) view.findViewById(R.id.shopping_car_pay);
        Elistview = (ExpandableListView) view.findViewById(R.id.shopping_car_lv);
        // editor = (TextView)view.findViewById(R.id.shopping_car_bt_editor);
        //设置全选按钮为选中状态
        shopping_car_cb.setChecked(false);

        context = UiUtils.getContext();
        //ShopCarManagerBean.getIntence(context).getShopCarData();

        adapter = new ShoppingCarAdapter(getActivity());
        defautAdapter = new ShoppingCarDefautAdapter(this.getActivity(), "fragment");
        // 给Listview填充数据
        Elistview.setAdapter(adapter);


    }

    @Override
    public void onResume() {

        super.onResume();
        if (adapter == null) {
            adapter = new ShoppingCarAdapter(context);
        }
        if (defautAdapter == null) {
            defautAdapter = new ShoppingCarDefautAdapter(this.getActivity(), "fragment");
        }
        // 给Listview填充数据
        Elistview.setAdapter(adapter);
        getNetDatas(sharedPreferences.getString(ContentValues.USER_ID, ""));

    }

    /**
     * Time: 2016/7/4 0004 上午 10:58
     * Description: ${todo}(初始化监听)
     * param: ${tags}
     * return: ${return_type}
     */
    @Override
    public void initListener() {

        // 购物车结算
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO 得到选中的条目的数据集合，然后传递给结算页面
                List<ShoppingCarListBean> lists = getCheckedList();
                if (lists.size() == 0) {
                    ToastUtil.showTextToast("您还没选择任何商品");
                } else {
//                    ToastUtil.showTextToast("可以去结算");
                    Intent intent = new Intent(context, MutilOrderActivity.class);
                    intent.putExtra(ContentValues.TOTAL_COUNT, allCount);
                    intent.putExtra(ContentValues.TOTAL_PRICE, allPrice);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(ContentValues.TO_SUBMIT_ORDER, (Serializable) lists);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }
            }
        });

        //点击全选按钮
        shopping_car_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view instanceof CheckBox) {
                    CheckBox checkBox = (CheckBox) view;
                    adapter.setupAllChecked(checkBox.isChecked());
                }
            }
        });
        //设置全选按钮的状态
        adapter.setOnAllCheckedBoxNeedChangeListener(new ShoppingCarAdapter.OnAllCheckedBoxNeedChangeListener() {
            @Override
            public void onCheckedBoxNeedChange(boolean allParentIsChecked) {
                shopping_car_cb.setChecked(allParentIsChecked);
            }
        });

        //设置商品全部删除后的操作
        adapter.setOnCheckHasGoodsListener(new ShoppingCarAdapter.OnCheckHasGoodsListener() {
            @Override
            public void onCheckHasGoods(boolean isHasGoods) {
                // 购物车没有内容的默认布局
                if (!isHasGoods) {
                    ShoppingCarDefaut();
                }

            }
        });


        //商品选中后的监听
        adapter.setOnGoodsCheckedChangeListener(new ShoppingCarAdapter.OnGoodsCheckedChangeListener() {
            @Override
            public void onGoodsCheckedChange(int totalCount, double totalPrice) {
                allCount = totalCount;
                allPrice = totalPrice;

                //TODO 显示总金额
                payNum.setText(String.valueOf(totalPrice));
                //如果没有选中的商品，不能结算
                if (totalCount == 0) {
                    pay.setEnabled(false);
                } else {
                    pay.setEnabled(true);
                }


            }
        });
        //商品删除的监听
        adapter.setOnCountChangeListener(new ShoppingCarAdapter.OnCountChangeListener() {
            @Override
            public void onCountChange(ShoppingCarListBean.Goods goods, String farm_id) {

//                OrderDeleteDialog(goods, farm_id);
                deleteNetGood(String.valueOf(goods.getDeleteId()), goods, farm_id);
            }
        });


    }

//    /**
//     * 显示删除通知对话框
//     */
//    private void OrderDeleteDialog(final ShoppingCarListBean.Goods goods, final String farm_id) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setTitle("删除商品");
//        builder.setMessage("是否确定删除，删除后无法在购物车直接查看购买！");
//        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//                deleteNetGood(String.valueOf(goods.getDeleteId()), goods, farm_id);
//                adapter.notifyDataSetChanged();
//            }
//        });
//        builder.setNegativeButton("返回", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        builder.create().show();
//
//    }

    /**
     * Time: 2016/7/4 0004 上午 10:59
     * Description: ${todo}(初始化数据)
     * param: ${tags}
     * return: ${return_type}
     */
    @Override
    public void initData() {
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //点击结算
            case R.id.shopping_car_bt_pay:
                //TODO 得到选中的条目的数据集合，然后传递给结算页面
                List<ShoppingCarListBean> lists = getCheckedList();
                if (lists.size() == 0) {
                    ToastUtil.showTextToast("您还没选择任何商品");
                } else {
//                    ToastUtil.showTextToast("可以去结算");
                    Intent intent = new Intent(context, MutilOrderActivity.class);

                    intent.putExtra("allCount", allCount);
                    intent.putExtra("allPrice", allPrice);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable(ContentValues.TO_SUBMIT_ORDER, (Serializable) lists);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;

        }
    }

    /**
     * Time: 2016/7/4 0004 上午 11:28
     * Description: ${todo}(网络请求获取用户购物车列表)
     * param: ${tags}
     * return: ${return_type}
     */

    private void GetNetData(final int i) {


        mListGoods = ShopCarManagerBean.getIntence(context).getShopCarData();

    }

    /**
     * Time: 2016/7/4 0004 下午 2:02
     * Description: ${todo}(购物车的默认布局)
     * param: ${tags}
     * return: ${return_type}
     */
    private void ShoppingCarDefaut() {

        // 隐藏列表分割线
        Elistview.setDividerHeight(0);
        Elistview.setAdapter(defautAdapter);
        // 编辑隐藏
        //editor.setVisibility(View.INVISIBLE);
        payLayout.setVisibility(View.INVISIBLE);
    }


    //得到选中的条目的数据集合，然后传递给结算页面
    private List<ShoppingCarListBean> getCheckedList() {
        List<ShoppingCarListBean> list = new ArrayList<>();
        ShoppingCarListBean shopBean;
        ShoppingCarListBean tempBean = null;
        if (mListGoods.size() != 0 && mListGoods != null) {
            for (int i = 0; i < mListGoods.size(); i++) {
                tempBean = new ShoppingCarListBean();
                shopBean = mListGoods.get(i);
                List<ShoppingCarListBean.Goods> goods = shopBean.getmGoods();
                List<ShoppingCarListBean.Goods> tempGoods = new ArrayList<>();
                if (goods.size() != 0) {
                    ShoppingCarListBean.Goods good = null;
                    for (int j = 0; j < goods.size(); j++) {
                        good = goods.get(j);
                        if (good.isChecked()) {
                            tempGoods.add(good);
                        }
                    }
                    tempBean.setmGoods(tempGoods);
                }
                list.add(tempBean);
            }

        }
        return list;
    }


    private List<ShoppingCarListBean> getNetDatas(String userId) {
        String url = ContentValues.SHOP_CAR_URL;
        final List<ShoppingCarListBean> goodsList = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        GsonRequest<NetShopCarBean> netbean = new GsonRequest<NetShopCarBean>(map, url, NetShopCarBean.class, new Response.Listener<NetShopCarBean>() {
            @Override
            public void onResponse(NetShopCarBean response) {
                if (response != null) {
                    int code = response.getCode();
                    if (code == 101) {
                        //    ToastUtil.showTextToast("请添加商品到购物车！");
                        ShoppingCarDefaut();
                        return;
                    }
                    if (code == 200) {
                        List<NetShopCarBean.ListBean> farms = response.getList();
                        if (farms.size() > 0) {
                            for (int i = 0; i < farms.size(); i++) {
                                ShoppingCarListBean bean = new ShoppingCarListBean();
                                NetShopCarBean.ListBean netlistbea = farms.get(i);
                                if (netlistbea.getIsSelected() == 0) {
                                    bean.setChecked(false);
                                } else {
                                    bean.setChecked(true);
                                }
                                bean.setFarmId(String.valueOf(netlistbea.getMerchantId()));
                                bean.setFarmName(netlistbea.getMerchantName());
                                bean.setFarmImgUrl(String.valueOf(netlistbea.getMerchanPhoto()));
                                List<NetShopCarBean.ListBean.CartVoListBean> carts = netlistbea.getCartVoList();
                                if (carts.size() > 0) {
                                    List<ShoppingCarListBean.Goods> goods = new ArrayList<ShoppingCarListBean.Goods>();
                                    for (int j = 0; j < carts.size(); j++) {
                                        ShoppingCarListBean.Goods good = new ShoppingCarListBean.Goods();
                                        NetShopCarBean.ListBean.CartVoListBean cart = carts.get(j);
                                        if (cart.getSelected() == 0) {
                                            good.setChecked(false);
                                        } else {
                                            good.setChecked(true);
                                        }
                                        if ("0".equals(cart.getIsSale())) {
                                            good.setIfOnsale(false);
                                        } else {
                                            good.setIfOnsale(true);
                                        }
                                        good.setPromise(cart.getPromise());
                                        good.setDeleteId(cart.getId());
                                        good.setGoodsNum(cart.getQuantity());
                                        good.setGoodsPrice(UiUtils.FormatMoneyStyle(cart.getReaPrice()));
                                        good.setGoodsImgUrl(cart.getSmallPhoto());
                                        good.setPayPrice(UiUtils.FormatMoneyStyle(String.valueOf(cart.getReaPrice())));
                                        good.setMaxBuyNum(cart.getMaxCount());
                                        good.setGoodsId(String.valueOf(cart.getProductId()));
                                        good.setGoodsName(cart.getProduntName());
                                        goods.add(good);
                                    }
                                    bean.setmGoods(goods);
                                }
                                goodsList.add(bean);

                            }
                        }

                        mListGoods = goodsList;
//                        ShopCarManagerBean.getIntence(context).setShopCarDatas(mListGoods);

                        if (goodsList.size() > 0) {
                            adapter.setData(goodsList);
                            Elistview.setVisibility(View.VISIBLE);
                            payLayout.setVisibility(View.VISIBLE);

                            for (int i = 0; i < goodsList.size(); i++) {
                                Elistview.expandGroup(i);
                            }
                        } else {
                            ShoppingCarDefaut();
                        }
                        //加入购物车管理类
                        //  ShopCarManagerBean.getIntence(context).setShopCarDatas(goodsList);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtil.showTextToast("网络请求出现异常");
                ShoppingCarDefaut();

            }
        });
        BaseApplication.getInstance().getRequestQueue().add(netbean);
        return goodsList;
    }


    //删除购物车数据
    private void deleteNetGood(String goodsId, final ShoppingCarListBean.Goods goods, final String farmid) {
        String url = ContentValues.DELETE_FROM_SHOP_CAR;
//        String userid=sharedPreferences.getString(ContentValues.USER_ID,"");
        Map<String, String> map = new HashMap<>();
        map.put("id", goodsId);
        GsonRequest<simpleNetBean> netrequest = new GsonRequest<simpleNetBean>(map, url, simpleNetBean.class, new Response.Listener<simpleNetBean>() {
            @Override
            public void onResponse(simpleNetBean response) {
                if (response != null) {
                    int code = response.getCode();
                    if (code == 101) {
                        ToastUtil.showTextToast(response.getMessage());
                        return;
                    }
                    if (code == 200) {
                        ToastUtil.showTextToast(response.getMessage());
                        ShopCarManagerBean.getIntence(context).subData(goods, farmid);
                        adapter.notifyDataSetChanged();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtil.showTextToast("请求网络错误");
            }
        });
        BaseApplication.getInstance().getRequestQueue().add(netrequest);


    }
}
