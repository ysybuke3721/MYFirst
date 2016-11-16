package com.slr.slrapp.beans;

import java.util.List;

/**
 * Created by admin on 2016/8/11.
 */
public class OrderDetailBean {


    /**
     * receiptAddress : 河北省石家庄市长安区,长安
     * sendMode : 快递
     * amountMoney : 339
     * status : 退单
     * orderDate : 2016-08-03 17:19:02.0
     * list : [{"storeName":"祖传秘制叫花鸡","photo":"http://192.168.0.188:8080/service/upload/cominfor/tujidandatu.png","comNumber":2,"price":15,"smallPhoto":"http://192.168.0.188:8080/service/upload/cominfor/2123_P_1428024360415.jpg","sozbId":5,"deliver":"申通快递","comName":"土鸡蛋","deliverNum":"15366945562854"},{"storeName":null,"photo":"http://192.168.0.188:8080/servicenull","comNumber":3,"price":78,"smallPhoto":"http://192.168.0.188:8080/servicenull","sozbId":6,"deliver":"圆通快递","comName":null,"deliverNum":"15366945562855"},{"storeName":"人人店","photo":"http://192.168.0.188:8080/service/upload/cominfor/tujidatu.png","comNumber":1,"price":15,"smallPhoto":"http://192.168.0.188:8080/service/upload/cominfor/tujis.jpg","sozbId":7,"deliver":"顺丰快递","comName":"土鸡","deliverNum":"15366945562856"},{"storeName":null,"photo":"http://192.168.0.188:8080/servicenull","comNumber":1,"price":15,"smallPhoto":"http://192.168.0.188:8080/servicenull","sozbId":8,"deliver":"","comName":null,"deliverNum":""},{"storeName":null,"photo":"http://192.168.0.188:8080/servicenull","comNumber":1,"price":15,"smallPhoto":"http://192.168.0.188:8080/servicenull","sozbId":9,"deliver":"EMS","comName":null,"deliverNum":"15366945562859"},{"storeName":null,"photo":"http://192.168.0.188:8080/servicenull","comNumber":1,"price":15,"smallPhoto":"http://192.168.0.188:8080/servicenull","sozbId":10,"deliver":"京东","comName":null,"deliverNum":"15366945562812"},{"storeName":null,"photo":"http://192.168.0.188:8080/servicenull","comNumber":1,"price":15,"smallPhoto":"http://192.168.0.188:8080/servicenull","sozbId":11,"deliver":"平邮","comName":null,"deliverNum":"15366940000000"}]
     * code : 200
     * receiptTel : 16935486562
     * receiptName : 李强
     * freightMoney : 0
     * message : 获取成功！
     * payMode : 在线支付
     * totlePrice : 339
     * orderNumber : 20160803998728
     */

    private String receiptAddress;
    private String sendMode;
    private double amountMoney;
    private String status;
    private String orderDate;
    private int code;
    private String receiptTel;
    private String receiptName;
    private double freightMoney;
    private String message;
    private String payMode;
    private double totlePrice;
    private String orderNumber;
    /**
     * storeName : 祖传秘制叫花鸡
     * photo : http://192.168.0.188:8080/service/upload/cominfor/tujidandatu.png
     * comNumber : 2
     * price : 15
     * smallPhoto : http://192.168.0.188:8080/service/upload/cominfor/2123_P_1428024360415.jpg
     * sozbId : 5
     * deliver : 申通快递
     * comName : 土鸡蛋
     * deliverNum : 15366945562854
     */

    private List<ListBean> list;

    public String getReceiptAddress() {
        return receiptAddress;
    }

    public void setReceiptAddress(String receiptAddress) {
        this.receiptAddress = receiptAddress;
    }

    public String getSendMode() {
        return sendMode;
    }

    public void setSendMode(String sendMode) {
        this.sendMode = sendMode;
    }

    public double getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(int amountMoney) {
        this.amountMoney = amountMoney;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getReceiptTel() {
        return receiptTel;
    }

    public void setReceiptTel(String receiptTel) {
        this.receiptTel = receiptTel;
    }

    public String getReceiptName() {
        return receiptName;
    }

    public void setReceiptName(String receiptName) {
        this.receiptName = receiptName;
    }

    public double getFreightMoney() {
        return freightMoney;
    }

    public void setFreightMoney(int freightMoney) {
        this.freightMoney = freightMoney;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }

    public double getTotlePrice() {
        return totlePrice;
    }

    public void setTotlePrice(int totlePrice) {
        this.totlePrice = totlePrice;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private String storeName;
        private String photo;
        private int comNumber;
        private double price;
        private String smallPhoto;
        private int sozbId;
        private String deliver;
        private String comName;
        private String deliverNum;

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
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

        public String getSmallPhoto() {
            return smallPhoto;
        }

        public void setSmallPhoto(String smallPhoto) {
            this.smallPhoto = smallPhoto;
        }

        public int getSozbId() {
            return sozbId;
        }

        public void setSozbId(int sozbId) {
            this.sozbId = sozbId;
        }

        public String getDeliver() {
            return deliver;
        }

        public void setDeliver(String deliver) {
            this.deliver = deliver;
        }

        public String getComName() {
            return comName;
        }

        public void setComName(String comName) {
            this.comName = comName;
        }

        public String getDeliverNum() {
            return deliverNum;
        }

        public void setDeliverNum(String deliverNum) {
            this.deliverNum = deliverNum;
        }
    }
}
