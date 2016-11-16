package com.slr.slrapp.beans;

/**
 * Created by admin on 2016/8/14.
 */
public class WithCashInfoBean {


    /**
     * message : 获取用户信息成功！
     * code : 200
     * user : {"buyType":"支付宝","id":5,"buyer_email":"13766246410","remainMoney":"980.00"}
     */

    private String message;
    private int code;
    /**
     * buyType : 支付宝
     * id : 5
     * buyer_email : 13766246410
     * remainMoney : 980.00
     */

    private UserBean user;

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

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        private String buyType;
        private int id;
        private String buyer_email;
        private String remainMoney;

        public String getBuyType() {
            return buyType;
        }

        public void setBuyType(String buyType) {
            this.buyType = buyType;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBuyer_email() {
            return buyer_email;
        }

        public void setBuyer_email(String buyer_email) {
            this.buyer_email = buyer_email;
        }

        public String getRemainMoney() {
            return remainMoney;
        }

        public void setRemainMoney(String remainMoney) {
            this.remainMoney = remainMoney;
        }
    }
}
