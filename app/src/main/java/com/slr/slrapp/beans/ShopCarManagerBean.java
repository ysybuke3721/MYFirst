package com.slr.slrapp.beans;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.slr.slrapp.utils.ContentValues;
import com.slr.slrapp.utils.ToastUtil;
import com.slr.slrapp.utils.UiUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 掌柜购物车数据的类
 * Created by Administrator on 2016/8/1 0001.
 */
public class ShopCarManagerBean {

    //用单例模式创建对象
    SharedPreferences share = UiUtils.getContext().getSharedPreferences(ContentValues.SP_NAME, 0);

    private static ShopCarManagerBean shopCarManagerBean;
    private List<ShoppingCarListBean> shopCarDatas = new ArrayList<>() ;//getNetDatas(String.valueOf(share.getInt(ContentValues.USER_ID, 5)));
//    private List

    private ShopCarManagerBean(Context context) {
//        shopCarDatas = new ArrayList<>();

    }

    public static synchronized ShopCarManagerBean getIntence(Context context) {
        if (shopCarManagerBean == null) {
            shopCarManagerBean = new ShopCarManagerBean(context);
        }
        return shopCarManagerBean;
    }


    /*增加某个牧场数据的方法*/
    public void addData(ShoppingCarListBean bean) {
        shopCarDatas.add(bean);
    }

    /*增加某个牧场某个商品的数量*/
    public void addData(ShoppingCarListBean.Goods goods, String farm_id) {
        if (shopCarDatas.size() != 0) {
            for (ShoppingCarListBean bean : shopCarDatas) {
                if (bean.getFarmId().equals(farm_id)) {
                    List<ShoppingCarListBean.Goods> innerBeans = bean.getmGoods();
                    innerBeans.add(goods);
                }
            }
        }
    }


    /*清空购物车的操作*/
    public void subAllData() {
        if (shopCarDatas.size() != 0) {
            shopCarDatas.clear();
        }
    }

    /*删除数据的方法*/
    public void subData(ShoppingCarListBean bean) {
        if (shopCarDatas.size() != 0) {
            if (shopCarDatas.contains(bean)) {
                shopCarDatas.remove(bean);
            }
        }
    }

    /*删除某个牧场某个商品的方法*/
    public void subData(ShoppingCarListBean.Goods goods, String farm_id) {
        List<ShoppingCarListBean> shopCarDatas = getShopCarData();
        List<ShoppingCarListBean> datas = shopCarDatas;
        if (shopCarDatas.size() != 0) {
            for (int i = 0; i < shopCarDatas.size(); i++) {
                ShoppingCarListBean bean = shopCarDatas.get(i);
                if (bean.getFarmId().equals(farm_id)) {
                    List<ShoppingCarListBean.Goods> innerBeans = bean.getmGoods();
                    if (innerBeans.size() != 0) {
                        if (innerBeans.contains(goods)) {
                            datas.get(i).getmGoods().remove(goods);
                        }
                    }
                }

            }
        }
        shopCarDatas = datas;
    }

    /*获取数据的方法*/
//    private List<ShoppingCarListBean> getDatas() {
//        List<ShoppingCarListBean> goodsList = new ArrayList<>();
//
//        ShoppingCarListBean bean = null;
//
//        for (int i = 0; i < 4; i++) {
//            bean = new ShoppingCarListBean();
//            bean.setChecked(false);
//            bean.setFarmId(i + "");
//          //  bean.setFarmImgUrl(R.mipmap.farm_icon);
//            bean.setFarmName("第" + i + "牧场");
//            List<ShoppingCarListBean.Goods> goods = new ArrayList<>();
//
//            ShoppingCarListBean.Goods goodsBean = null;
//            for (int j = 0; j < 3; j++) {
//                goodsBean = new ShoppingCarListBean.Goods();
//                goodsBean.setChecked(false);
//                goodsBean.setGoodsNum(j + 1);
//                goodsBean.setGoodsId(j + "");
//                //goodsBean.setGoodsImgUrl(R.mipmap.list_img_1);
//                goodsBean.setGoodsName("第" + i + "牧场的产品");
//                goodsBean.setGoodsPrice("" + 150);
//                goods.add(goodsBean);
//            }
//
//            bean.setmGoods(goods);
//
//            goodsList.add(bean);
//        }
//
//
//        return goodsList;
//    }



    public synchronized List<ShoppingCarListBean> getShopCarData() {

        return shopCarDatas;

    }
    public void  setShopCarDatas (List<ShoppingCarListBean>  datas){
        this.shopCarDatas=datas;
    }




    private List<ShoppingCarListBean> getNetDatas(String userId) {
        String url = ContentValues.SHOP_CAR_URL;
        final List<ShoppingCarListBean> goodsList = new ArrayList<>();
        OkHttpUtils.get().url(url).addParams("userId", userId).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtil.showTextToast("请求网络失败");
            }

            @Override
            public void onResponse(String response, int id) {
                if (!TextUtils.isEmpty(response)) {
                    Gson gson = new Gson();
                    NetShopCarBean netShopCarBean = gson.fromJson(response, NetShopCarBean.class);
                    int code = netShopCarBean.getCode();
                    if (code == 101) {
                        ToastUtil.showTextToast("获取数据失败");
                        return;
                    }
                    if (code == 200) {
                        List<NetShopCarBean.ListBean> farms = netShopCarBean.getList();
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
                                        good.setGoodsNum(cart.getQuantity());
                                        good.setGoodsPrice(UiUtils.FormatMoneyStyle(cart.getPrice()));
                                        good.setGoodsImgUrl(cart.getImagePath());
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
                    }
                }
            }
        });

        return goodsList;
    }



}
