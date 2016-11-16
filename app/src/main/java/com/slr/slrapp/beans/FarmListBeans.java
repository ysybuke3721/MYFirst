package com.slr.slrapp.beans;

import java.util.List;

/**
 * Created by Administrator on 2016/8/11 0011.
 */
public class FarmListBeans {


    /**
     * message : 获取成功！
     * list : [{"id":1,"sid":29,"storeName":"祖传秘制叫花鸡","smallPhoto":"http://192.168.0.188:8080/service/upload/cominfor/2123_P_1428024360415.jpg","evaluateQuantity":3,"address":"上海外滩","spaceName":"二号监控","name":"土鸡蛋","star":4.5,"photo":"http://192.168.0.188:8080/service/upload/cominfor/tujidandatu.png"},{"id":3,"sid":28,"storeName":"人人店","smallPhoto":"http://192.168.0.188:8080/service/upload/cominfor/tujis.jpg","evaluateQuantity":1,"address":"郑州市高新区郑州大学","spaceName":"二号监控","name":"土鸡","star":3.5,"photo":"http://192.168.0.188:8080/service/upload/cominfor/tujidatu.png"}]
     * code : 200
     */

    private String message;
    private int code;
    /**
     * id : 1
     * sid : 29
     * storeName : 祖传秘制叫花鸡
     * smallPhoto : http://192.168.0.188:8080/service/upload/cominfor/2123_P_1428024360415.jpg
     * evaluateQuantity : 3
     * address : 上海外滩
     * spaceName : 二号监控
     * name : 土鸡蛋
     * star : 4.5
     * photo : http://192.168.0.188:8080/service/upload/cominfor/tujidandatu.png
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
        private int sid;
        private String storeName;
        private String smallPhoto;
        private int evaluateQuantity;
        private String address;
        private String spaceName;
        private String name;
        private double star;
        private String photo;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSid() {
            return sid;
        }

        public void setSid(int sid) {
            this.sid = sid;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getSmallPhoto() {
            return smallPhoto;
        }

        public void setSmallPhoto(String smallPhoto) {
            this.smallPhoto = smallPhoto;
        }

        public int getEvaluateQuantity() {
            return evaluateQuantity;
        }

        public void setEvaluateQuantity(int evaluateQuantity) {
            this.evaluateQuantity = evaluateQuantity;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getSpaceName() {
            return spaceName;
        }

        public void setSpaceName(String spaceName) {
            this.spaceName = spaceName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getStar() {
            return star;
        }

        public void setStar(double star) {
            this.star = star;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }
    }
}
