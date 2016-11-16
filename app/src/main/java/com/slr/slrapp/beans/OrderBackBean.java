package com.slr.slrapp.beans;

import java.util.List;

/**
 * Created by admin on 2016/8/22.
 */
public class OrderBackBean {


    /**
     * message : 退单显示成功！
     * list : [{"id":31,"refundMoney":1.8,"comNumber":1,"price":15,"storeName":"人人店","refundStatus":"未到账","totlePrice":"15","comName":"土鸡","comPhoto":"http://192.168.0.188:8080/service/upload/cominfor/20160808170441.jpg","storePhoto":"http://192.168.0.188:8080/service/upload/cominfor/2123_P_1428024360415.jpg"},{"id":33,"refundMoney":3.75,"comNumber":1,"price":15,"storeName":"正宗\u201c野鸡\u201d肉","refundStatus":"未到账","totlePrice":"15","comName":"土鸭","comPhoto":"http://192.168.0.188:8080/service/upload/cominfor/14066215925582.jpg","storePhoto":"http://192.168.0.188:8080/service/upload/cominfor/2123_P_1428024360415.jpg"}]
     * code : 200
     */

    private String message;
    private int code;
    /**
     * id : 31
     * refundMoney : 1.8
     * comNumber : 1
     * price : 15
     * storeName : 人人店
     * refundStatus : 未到账
     * totlePrice : 15
     * comName : 土鸡
     * comPhoto : http://192.168.0.188:8080/service/upload/cominfor/20160808170441.jpg
     * storePhoto : http://192.168.0.188:8080/service/upload/cominfor/2123_P_1428024360415.jpg
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
        private int id;
        private double refundMoney;
        private int comNumber;
        private double price;
        private String storeName;
        private String refundStatus;
        private String totlePrice;
        private String comName;
        private String comPhoto;
        private String storePhoto;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getRefundMoney() {
            return refundMoney;
        }

        public void setRefundMoney(double refundMoney) {
            this.refundMoney = refundMoney;
        }

        public int getComNumber() {
            return comNumber;
        }

        public void setComNumber(int comNumber) {
            this.comNumber = comNumber;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getRefundStatus() {
            return refundStatus;
        }

        public void setRefundStatus(String refundStatus) {
            this.refundStatus = refundStatus;
        }

        public String getTotlePrice() {
            return totlePrice;
        }

        public void setTotlePrice(String totlePrice) {
            this.totlePrice = totlePrice;
        }

        public String getComName() {
            return comName;
        }

        public void setComName(String comName) {
            this.comName = comName;
        }

        public String getComPhoto() {
            return comPhoto;
        }

        public void setComPhoto(String comPhoto) {
            this.comPhoto = comPhoto;
        }

        public String getStorePhoto() {
            return storePhoto;
        }

        public void setStorePhoto(String storePhoto) {
            this.storePhoto = storePhoto;
        }
    }
}
