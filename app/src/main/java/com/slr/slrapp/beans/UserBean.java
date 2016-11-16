package com.slr.slrapp.beans;

/**
 * User: Administrator
 * Time: 2016/7/19 0019
 * Description: ${todo}(用一句话描述该文件做什么)
 * Version V1.0
 */
public class UserBean {
    //用户名
    private String name;
    //头像
    private String face;
    //订单数
    private String orderNum;
    //
    private String register;
    private String money;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "name='" + name + '\'' +
                ", face='" + face + '\'' +
                ", orderNum='" + orderNum + '\'' +
                ", register='" + register + '\'' +
                ", money='" + money + '\'' +
                '}';
    }
}
