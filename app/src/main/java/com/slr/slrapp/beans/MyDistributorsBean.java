package com.slr.slrapp.beans;

import java.util.List;

/**
 * 我的分销商
 * Created by admin on 2016/8/11.
 */
public class MyDistributorsBean {


    /**
     * firstTotlePrice : 2973.5
     * totlePrice : 3713.7
     * thirdSize : 1
     * secondSize : 4
     * list : [{"id":8,"orderTotlePrice":"865","price":432.5,"nickName":"张继吧","orderTotleNumber":6,"userName":"546456","createDate":"2016-08-06 11:48:31","head":"http://192.168.0.188:8080/service/upload/internt_web_technology-13-128.png"},{"id":7,"orderTotlePrice":"500","price":250,"nickName":"QWEQWE","orderTotleNumber":7,"userName":"123123","createDate":"2016-08-06 11:47:20","head":"http://192.168.0.188:8080/service/upload/internt_web_technology-13-128.png"},{"id":6,"orderTotlePrice":"4582","price":2291,"nickName":"阿尔达速度","orderTotleNumber":8,"userName":"232","createDate":"2016-08-06 11:36:58","head":"http://192.168.0.188:8080/service/upload/internt_web_technology-13-128.png"}]
     * code : 200
     * firstSize : 3
     */

    private double firstTotlePrice;
    private double totlePrice;
    private int thirdSize;
    private int secondSize;
    private int code;
    private int firstSize;
    /**
     * id : 8
     * orderTotlePrice : 865
     * price : 432.5
     * nickName : 张继吧
     * orderTotleNumber : 6
     * userName : 546456
     * createDate : 2016-08-06 11:48:31
     * head : http://192.168.0.188:8080/service/upload/internt_web_technology-13-128.png
     */

    private List<ListBean> list;

    public double getFirstTotlePrice() {
        return firstTotlePrice;
    }

    public void setFirstTotlePrice(double firstTotlePrice) {
        this.firstTotlePrice = firstTotlePrice;
    }

    public double getTotlePrice() {
        return totlePrice;
    }

    public void setTotlePrice(double totlePrice) {
        this.totlePrice = totlePrice;
    }

    public int getThirdSize() {
        return thirdSize;
    }

    public void setThirdSize(int thirdSize) {
        this.thirdSize = thirdSize;
    }

    public int getSecondSize() {
        return secondSize;
    }

    public void setSecondSize(int secondSize) {
        this.secondSize = secondSize;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getFirstSize() {
        return firstSize;
    }

    public void setFirstSize(int firstSize) {
        this.firstSize = firstSize;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private int id;
        private String orderTotlePrice;
        private double price;
        private String nickName;
        private int orderTotleNumber;
        private String userName;
        private String createDate;
        private String head;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOrderTotlePrice() {
            return orderTotlePrice;
        }

        public void setOrderTotlePrice(String orderTotlePrice) {
            this.orderTotlePrice = orderTotlePrice;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getOrderTotleNumber() {
            return orderTotleNumber;
        }

        public void setOrderTotleNumber(int orderTotleNumber) {
            this.orderTotleNumber = orderTotleNumber;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }
    }
}
