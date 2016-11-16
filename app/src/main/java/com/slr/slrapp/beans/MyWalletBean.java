package com.slr.slrapp.beans;

import java.util.List;

/**
 * Created by admin on 2016/8/13.
 */
public class MyWalletBean {

    /**
     * message : 获取成功！
     * price : 980.00
     * list : [{"id":5,"price":"-0.0","nickName":"阿尔达速度","name":"提现时间","state":"未到账","userName":"12122","head":"http://192.168.0.188:8080/service/upload/internt_web_technology-13-128.png","createDate":"2016-08-12 19:54:27"},{"id":5,"price":"-0.0","nickName":"阿尔达速度","name":"提现时间","state":"已到账","userName":"12122","head":"http://192.168.0.188:8080/service/upload/internt_web_technology-13-128.png","createDate":"2016-08-11 11:38:33"},{"id":5,"price":"-10","nickName":"阿尔达速度","name":"提现时间","state":"已到账","userName":"12122","head":"http://192.168.0.188:8080/service/upload/internt_web_technology-13-128.png","createDate":"2016-08-10 11:47:46"},{"id":1,"price":"50","nickName":"张昆明","name":"购买时间","state":"二级经销商","userName":"zhang","head":"http://192.168.0.188:8080/service/upload/internt_web_technology-13-128.png","createDate":"2016-08-02 14:43:56"}]
     * code : 200
     */

    private String message;
    private String price;
    private int code;
    /**
     * id : 5
     * price : -0.0
     * nickName : 阿尔达速度
     * name : 提现时间
     * state : 未到账
     * userName : 12122
     * head : http://192.168.0.188:8080/service/upload/internt_web_technology-13-128.png
     * createDate : 2016-08-12 19:54:27
     */

    private List<ListBean> list;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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
        private String price;
        private String nickName;
        private String name;
        private String state;
        private String userName;
        private String head;
        private String createDate;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }
}
