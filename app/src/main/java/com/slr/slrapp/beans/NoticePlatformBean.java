package com.slr.slrapp.beans;

import java.util.List;

/**
 * Created by Administrator on 2016/8/13 0013.
 */
public class NoticePlatformBean {
    /**
     * message : 获取成功！
     * list : [{"dealerType":"二级经销商","id":1,"price":"50","nickName":"张昆明","userName":"zhang","lastOrderDate":"2016-08-02 14:43:56","head":"http://192.168.0.188:8080/service/upload/internt_web_technology-13-128.png"}]
     * code : 200
     */

    private String message;
    private int code;
    /**
     * dealerType : 二级经销商
     * id : 1
     * price : 50
     * nickName : 张昆明
     * userName : zhang
     * lastOrderDate : 2016-08-02 14:43:56
     * head : http://192.168.0.188:8080/service/upload/internt_web_technology-13-128.png
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
        private String dealerType;
        private int id;
        private String price;
        private String nickName;
        private String userName;
        private String lastOrderDate;
        private String head;

        public String getDealerType() {
            return dealerType;
        }

        public void setDealerType(String dealerType) {
            this.dealerType = dealerType;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getLastOrderDate() {
            return lastOrderDate;
        }

        public void setLastOrderDate(String lastOrderDate) {
            this.lastOrderDate = lastOrderDate;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }
    }
}
