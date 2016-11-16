package com.slr.slrapp.beans;

import java.io.Serializable;
import java.util.List;

/**
 * User: Administrator
 * Time: 2016/7/4 0004
 * Description: ${todo}(购物车列表)
 * Version V1.0
 */
public class ShoppingCarListBean implements Serializable{

    // 养殖场id
    private String farmId;
    // 养殖场名称
    private String farmName;
    // 养殖场图片地址
    private String farmImgUrl;
    // 养殖场下所属商品
    private List<Goods> mGoods;


    //    //是否选中
      private boolean isChecked;




    public String getFarmId() {
        return farmId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public String getFarmImgUrl() {
        return farmImgUrl;
    }

    public void setFarmImgUrl(String farmImgUrl) {
        this.farmImgUrl = farmImgUrl;
    }

    public List<Goods> getmGoods() {
        return mGoods;
    }
    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public void setmGoods(List<Goods> mGoods) {
        this.mGoods = mGoods;
    }

    @Override
    public String toString() {
        return "ShoppingCarListBean{" +
                "farmId='" + farmId + '\'' +
                ", farmName='" + farmName + '\'' +
                ", farmImgUrl='" + farmImgUrl + '\'' +
                ", mGoods=" + mGoods +
                ", isChecked=" + isChecked +
                '}';
    }

    // 商品信息
    public static class Goods implements Serializable{

        // 商品id
        private String goodsId;

        public int getDeleteId() {
            return deleteId;
        }

        public void setDeleteId(int deleteId) {
            this.deleteId = deleteId;
        }

        //购物车删除是用的id
        private int deleteId;
        // 商品名称
        private String goodsName;
        // 图片地址
        private String goodsImgUrl;
        // 购买数量
        private int goodsNum;
        // 变动前价格
        private double goodsPrice;



        //结算价格
        private String payPrice;
        //商家承诺
        private String promise;
        //购买最大数量
        private int maxBuyNum;


        //是否打折
        private boolean ifOnsale;
//        /** 是否被选中 */
       private boolean isChecked;
//        /** 是否是编辑状态 */
//        private boolean isEditing;



        public boolean isIfOnsale() {
            return ifOnsale;
        }

        public void setIfOnsale(boolean ifOnsale) {
            this.ifOnsale = ifOnsale;
        }

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public double getGoodsPrice() {
            return goodsPrice;
        }

        public void setGoodsPrice(double goodsPrice) {
            this.goodsPrice = goodsPrice;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getGoodsImgUrl() {
            return goodsImgUrl;
        }

        public void setGoodsImgUrl(String goodsImgUrl) {
            this.goodsImgUrl = goodsImgUrl;
        }

        public int getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(int goodsNum) {
            this.goodsNum = goodsNum;
        }
        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }
        public String getPayPrice() {
            return payPrice;
        }

        public void setPayPrice(String payPrice) {
            this.payPrice = payPrice;
        }

        public String getPromise() {
            return promise;
        }

        public void setPromise(String promise) {
            this.promise = promise;
        }

        public int getMaxBuyNum() {
            return maxBuyNum;
        }

        public void setMaxBuyNum(int maxBuyNum) {
            this.maxBuyNum = maxBuyNum;
        }

        @Override
        public String toString() {
            return "Goods{" +
                    "goodsId='" + goodsId + '\'' +
                    ", goodsName='" + goodsName + '\'' +
                    ", goodsImgUrl='" + goodsImgUrl + '\'' +
                    ", goodsNum=" + goodsNum +
                    ", goodsPrice='" + goodsPrice + '\'' +
                    ", payPrice='" + payPrice + '\'' +
                    ", promise='" + promise + '\'' +
                    ", maxBuyNum=" + maxBuyNum +
                    ", ifOnsale=" + ifOnsale +
                    ", isChecked=" + isChecked +
                    '}';
        }
    }



}
