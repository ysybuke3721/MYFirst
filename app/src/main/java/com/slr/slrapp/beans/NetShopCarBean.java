package com.slr.slrapp.beans;

import java.util.List;

/**
 * Created by Administrator on 2016/8/11 0011.
 */
public class NetShopCarBean {

    /**
     * message : 获取成功！
     * list : [{"price":0,"merchanPhoto":"/upload/cominfor/2123_P_1428024360415.jpg","isSelected":0,"cartVoList":[{"id":1,"isSale":"1","selected":0,"comStatus":"1","price":15,"smallPhoto":"http://192.168.0.188:8080/service/upload/cominfor/2123_P_1428024360415.jpg","imagePath":"http://192.168.0.188:8080/service/upload/cominfor/tujidandatu.png","reaPrice":0.01,"promise":"充满泥土的芳香","produntName":"土鸡蛋","quantity":1,"maxCount":500,"productId":1}],"merchantName":"祖传秘制叫花鸡","merchantId":29,"isChoosed":0}]
     * code : 200
     */

    private String message;
    private int code;
    /**
     * price : 0
     * merchanPhoto : /upload/cominfor/2123_P_1428024360415.jpg
     * isSelected : 0
     * cartVoList : [{"id":1,"isSale":"1","selected":0,"comStatus":"1","price":15,"smallPhoto":"http://192.168.0.188:8080/service/upload/cominfor/2123_P_1428024360415.jpg","imagePath":"http://192.168.0.188:8080/service/upload/cominfor/tujidandatu.png","reaPrice":0.01,"promise":"充满泥土的芳香","produntName":"土鸡蛋","quantity":1,"maxCount":500,"productId":1}]
     * merchantName : 祖传秘制叫花鸡
     * merchantId : 29
     * isChoosed : 0
     */

    private List<ListBean> list;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private int price;
        private String merchanPhoto;
        private int isSelected;
        private String merchantName;
        private int merchantId;
        private int isChoosed;
        /**
         * id : 1
         * isSale : 1
         * selected : 0
         * comStatus : 1
         * price : 15
         * smallPhoto : http://192.168.0.188:8080/service/upload/cominfor/2123_P_1428024360415.jpg
         * imagePath : http://192.168.0.188:8080/service/upload/cominfor/tujidandatu.png
         * reaPrice : 0.01
         * promise : 充满泥土的芳香
         * produntName : 土鸡蛋
         * quantity : 1
         * maxCount : 500
         * productId : 1
         */

        private List<CartVoListBean> cartVoList;

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getMerchanPhoto() {
            return merchanPhoto;
        }

        public void setMerchanPhoto(String merchanPhoto) {
            this.merchanPhoto = merchanPhoto;
        }

        public int getIsSelected() {
            return isSelected;
        }

        public void setIsSelected(int isSelected) {
            this.isSelected = isSelected;
        }

        public String getMerchantName() {
            return merchantName;
        }

        public void setMerchantName(String merchantName) {
            this.merchantName = merchantName;
        }

        public int getMerchantId() {
            return merchantId;
        }

        public void setMerchantId(int merchantId) {
            this.merchantId = merchantId;
        }

        public int getIsChoosed() {
            return isChoosed;
        }

        public void setIsChoosed(int isChoosed) {
            this.isChoosed = isChoosed;
        }

        public List<CartVoListBean> getCartVoList() {
            return cartVoList;
        }

        public void setCartVoList(List<CartVoListBean> cartVoList) {
            this.cartVoList = cartVoList;
        }

        public static class CartVoListBean {
            private int id;
            private String isSale;
            private int selected;
            private String comStatus;
            private int price;
            private String smallPhoto;
            private String imagePath;
            private double reaPrice;
            private String promise;
            private String produntName;
            private int quantity;
            private int maxCount;
            private int productId;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getIsSale() {
                return isSale;
            }

            public void setIsSale(String isSale) {
                this.isSale = isSale;
            }

            public int getSelected() {
                return selected;
            }

            public void setSelected(int selected) {
                this.selected = selected;
            }

            public String getComStatus() {
                return comStatus;
            }

            public void setComStatus(String comStatus) {
                this.comStatus = comStatus;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public String getSmallPhoto() {
                return smallPhoto;
            }

            public void setSmallPhoto(String smallPhoto) {
                this.smallPhoto = smallPhoto;
            }

            public String getImagePath() {
                return imagePath;
            }

            public void setImagePath(String imagePath) {
                this.imagePath = imagePath;
            }

            public double getReaPrice() {
                return reaPrice;
            }

            public void setReaPrice(double reaPrice) {
                this.reaPrice = reaPrice;
            }

            public String getPromise() {
                return promise;
            }

            public void setPromise(String promise) {
                this.promise = promise;
            }

            public String getProduntName() {
                return produntName;
            }

            public void setProduntName(String produntName) {
                this.produntName = produntName;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public int getMaxCount() {
                return maxCount;
            }

            public void setMaxCount(int maxCount) {
                this.maxCount = maxCount;
            }

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }
        }
    }
}
