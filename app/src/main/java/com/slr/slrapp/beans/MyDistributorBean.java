package com.slr.slrapp.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2016/8/12.
 */
public class MyDistributorBean implements Serializable {


    /**
     * message : 获取成功！
     * price : 273
     * totlePrice : 273
     * thirdSize : 0
     * secondSize : 0
     * isMember : 1
     * list : [{"id":8,"orderTotlePrice":"865","price":173,"nickName":"张继吧","orderTotleNumber":6,"userName":"546456","createDate":"2016-08-06 11:48:31","head":"http://192.168.0.188:8080/slrkj/upload/icon_default.png"},{"id":7,"orderTotlePrice":"500","price":100,"nickName":"QWEQWE","orderTotleNumber":7,"userName":"123123","createDate":"2016-08-06 11:47:20","head":"http://192.168.0.188:8080/slrkj/upload/icon_default.png"}]
     * code : 200
     * firstSize : 2
     */

    private String message;
    private int price;
    private int totlePrice;
    private int thirdSize;
    private int secondSize;
    private int isMember;
    private int code;
    private int firstSize;
    /**
     * id : 8
     * orderTotlePrice : 865
     * price : 173
     * nickName : 张继吧
     * orderTotleNumber : 6
     * userName : 546456
     * createDate : 2016-08-06 11:48:31
     * head : http://192.168.0.188:8080/slrkj/upload/icon_default.png
     */

    private List<ListBean> list;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotlePrice() {
        return totlePrice;
    }

    public void setTotlePrice(int totlePrice) {
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

    public int getIsMember() {
        return isMember;
    }

    public void setIsMember(int isMember) {
        this.isMember = isMember;
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
        private int price;
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

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
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
