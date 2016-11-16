package com.slr.slrapp.beans;

import java.util.List;

/**
 * 我的评价界面
 * Created by Administrator on 2016/8/9 0009.
 */
public class MyEvalutaionsBean {


    /**
     * message : 获取成功！
     * list : [{"comStatus":"1","starNum":"中评","sort":1,"medicalHistory":"手足口病","QRCode":"你好","wNumber":1,"spaceId":15,"recommend":"1","photo":"http://192.168.0.188:8080/service/upload/cominfor/tujidandatu.png","comBirthday":"2016-08-01 16:59:04","message":"哦哦","id":1,"comPrice":0.01,"Growth":"色泽鲜艳","jianJie":"土鸡蛋 绿色营养 吃着放心","price":15,"smallPhoto":"/upload/cominfor/2123_P_1428024360415.jpg","details":"色泽鲜艳，椭圆形色泽鲜艳，椭圆形","comName":"土鸡蛋","brithCondition":"良好","storeId":29,"typeId":4,"maxCount":500,"messageDate":"2016-08-15 17:33:18"},{"comStatus":"1","starNum":"好评","sort":1,"medicalHistory":"手足口病","QRCode":"你好","wNumber":1,"spaceId":15,"recommend":"1","photo":"http://192.168.0.188:8080/service/upload/cominfor/tujidandatu.png","comBirthday":"2016-08-01 16:59:04","message":"看看","id":1,"comPrice":0.01,"Growth":"色泽鲜艳","jianJie":"土鸡蛋 绿色营养 吃着放心","price":15,"smallPhoto":"/upload/cominfor/2123_P_1428024360415.jpg","details":"色泽鲜艳，椭圆形色泽鲜艳，椭圆形","comName":"土鸡蛋","brithCondition":"良好","storeId":29,"typeId":4,"maxCount":500,"messageDate":"2016-08-15 17:02:03"},{"comStatus":"1","starNum":"差评","sort":1,"medicalHistory":"手足口病","QRCode":"你好","wNumber":1,"spaceId":15,"recommend":"1","photo":"http://192.168.0.188:8080/service/upload/cominfor/tujidandatu.png","comBirthday":"2016-08-01 16:59:04","message":null,"id":1,"comPrice":0.01,"Growth":"色泽鲜艳","jianJie":"土鸡蛋 绿色营养 吃着放心","price":15,"smallPhoto":"/upload/cominfor/2123_P_1428024360415.jpg","details":"色泽鲜艳，椭圆形色泽鲜艳，椭圆形","comName":"土鸡蛋","brithCondition":"良好","storeId":29,"typeId":4,"maxCount":500,"messageDate":null},{"comStatus":"1","starNum":"差评","sort":1,"medicalHistory":"手足口病","QRCode":"你好","wNumber":1,"spaceId":15,"recommend":"1","photo":"http://192.168.0.188:8080/service/upload/cominfor/tujidandatu.png","comBirthday":"2016-08-01 16:59:04","message":null,"id":1,"comPrice":0.01,"Growth":"色泽鲜艳","jianJie":"土鸡蛋 绿色营养 吃着放心","price":15,"smallPhoto":"/upload/cominfor/2123_P_1428024360415.jpg","details":"色泽鲜艳，椭圆形色泽鲜艳，椭圆形","comName":"土鸡蛋","brithCondition":"良好","storeId":29,"typeId":4,"maxCount":500,"messageDate":null},{"comStatus":"1","starNum":"差评","sort":1,"medicalHistory":"手足口病","QRCode":"你好","wNumber":1,"spaceId":15,"recommend":"1","photo":"http://192.168.0.188:8080/service/upload/cominfor/tujidandatu.png","comBirthday":"2016-08-01 16:59:04","message":null,"id":1,"comPrice":0.01,"Growth":"色泽鲜艳","jianJie":"土鸡蛋 绿色营养 吃着放心","price":15,"smallPhoto":"/upload/cominfor/2123_P_1428024360415.jpg","details":"色泽鲜艳，椭圆形色泽鲜艳，椭圆形","comName":"土鸡蛋","brithCondition":"良好","storeId":29,"typeId":4,"maxCount":500,"messageDate":null},{"comStatus":"1","starNum":"差评","sort":1,"medicalHistory":"手足口病","QRCode":"你好","wNumber":1,"spaceId":15,"recommend":"1","photo":"http://192.168.0.188:8080/service/upload/cominfor/tujidandatu.png","comBirthday":"2016-08-01 16:59:04","message":null,"id":1,"comPrice":0.01,"Growth":"色泽鲜艳","jianJie":"土鸡蛋 绿色营养 吃着放心","price":15,"smallPhoto":"/upload/cominfor/2123_P_1428024360415.jpg","details":"色泽鲜艳，椭圆形色泽鲜艳，椭圆形","comName":"土鸡蛋","brithCondition":"良好","storeId":29,"typeId":4,"maxCount":500,"messageDate":null},{"comStatus":"1","starNum":"差评","sort":1,"medicalHistory":"手足口病","QRCode":"你好","wNumber":1,"spaceId":15,"recommend":"1","photo":"http://192.168.0.188:8080/service/upload/cominfor/tujidandatu.png","comBirthday":"2016-08-01 16:59:04","message":null,"id":1,"comPrice":0.01,"Growth":"色泽鲜艳","jianJie":"土鸡蛋 绿色营养 吃着放心","price":15,"smallPhoto":"/upload/cominfor/2123_P_1428024360415.jpg","details":"色泽鲜艳，椭圆形色泽鲜艳，椭圆形","comName":"土鸡蛋","brithCondition":"良好","storeId":29,"typeId":4,"maxCount":500,"messageDate":null},{"comStatus":"1","starNum":"中评","sort":3,"medicalHistory":"拉肚子","QRCode":"你好","wNumber":4,"spaceId":11,"recommend":"1","photo":"http://192.168.0.188:8080/service/upload/cominfor/tujidatu.png","comBirthday":"2016-08-02 18:30:20","message":"哦哦","id":3,"comPrice":0.01,"Growth":"生长良好，四肢健全","jianJie":"天赐良鸡 生态散养 食补真品","price":20,"smallPhoto":"/upload/cominfor/tujis.jpg","details":"色泽鲜艳，椭圆形色泽鲜艳，椭圆形","comName":"土鸡","brithCondition":"good","storeId":28,"typeId":3,"maxCount":40,"messageDate":"2016-08-15 17:33:18"},{"comStatus":"1","starNum":"差评","sort":3,"medicalHistory":"拉肚子","QRCode":"你好","wNumber":4,"spaceId":11,"recommend":"1","photo":"http://192.168.0.188:8080/service/upload/cominfor/tujidatu.png","comBirthday":"2016-08-02 18:30:20","message":null,"id":3,"comPrice":0.01,"Growth":"生长良好，四肢健全","jianJie":"天赐良鸡 生态散养 食补真品","price":20,"smallPhoto":"/upload/cominfor/tujis.jpg","details":"色泽鲜艳，椭圆形色泽鲜艳，椭圆形","comName":"土鸡","brithCondition":"good","storeId":28,"typeId":3,"maxCount":40,"messageDate":null},{"comStatus":"1","starNum":"差评","sort":3,"medicalHistory":"拉肚子","QRCode":"你好","wNumber":4,"spaceId":11,"recommend":"1","photo":"http://192.168.0.188:8080/service/upload/cominfor/tujidatu.png","comBirthday":"2016-08-02 18:30:20","message":null,"id":3,"comPrice":0.01,"Growth":"生长良好，四肢健全","jianJie":"天赐良鸡 生态散养 食补真品","price":20,"smallPhoto":"/upload/cominfor/tujis.jpg","details":"色泽鲜艳，椭圆形色泽鲜艳，椭圆形","comName":"土鸡","brithCondition":"good","storeId":28,"typeId":3,"maxCount":40,"messageDate":null}]
     * code : 200
     */

    private String message;
    private int code;
    /**
     * comStatus : 1
     * starNum : 中评
     * sort : 1
     * medicalHistory : 手足口病
     * QRCode : 你好
     * wNumber : 1
     * spaceId : 15
     * recommend : 1
     * photo : http://192.168.0.188:8080/service/upload/cominfor/tujidandatu.png
     * comBirthday : 2016-08-01 16:59:04
     * message : 哦哦
     * id : 1
     * comPrice : 0.01
     * Growth : 色泽鲜艳
     * jianJie : 土鸡蛋 绿色营养 吃着放心
     * price : 15
     * smallPhoto : /upload/cominfor/2123_P_1428024360415.jpg
     * details : 色泽鲜艳，椭圆形色泽鲜艳，椭圆形
     * comName : 土鸡蛋
     * brithCondition : 良好
     * storeId : 29
     * typeId : 4
     * maxCount : 500
     * messageDate : 2016-08-15 17:33:18
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
        private String comStatus;
        private String starNum;
        private int sort;
        private String medicalHistory;
        private String QRCode;
        private int wNumber;
        private int spaceId;
        private String recommend;
        private String photo;
        private String comBirthday;
        private String message;
        private int id;
        private double comPrice;
        private String Growth;
        private String jianJie;
        private int price;
        private String smallPhoto;
        private String details;
        private String comName;
        private String brithCondition;
        private int storeId;
        private int typeId;
        private int maxCount;
        private String messageDate;

        public String getComStatus() {
            return comStatus;
        }

        public void setComStatus(String comStatus) {
            this.comStatus = comStatus;
        }

        public String getStarNum() {
            return starNum;
        }

        public void setStarNum(String starNum) {
            this.starNum = starNum;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getMedicalHistory() {
            return medicalHistory;
        }

        public void setMedicalHistory(String medicalHistory) {
            this.medicalHistory = medicalHistory;
        }

        public String getQRCode() {
            return QRCode;
        }

        public void setQRCode(String QRCode) {
            this.QRCode = QRCode;
        }

        public int getWNumber() {
            return wNumber;
        }

        public void setWNumber(int wNumber) {
            this.wNumber = wNumber;
        }

        public int getSpaceId() {
            return spaceId;
        }

        public void setSpaceId(int spaceId) {
            this.spaceId = spaceId;
        }

        public String getRecommend() {
            return recommend;
        }

        public void setRecommend(String recommend) {
            this.recommend = recommend;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getComBirthday() {
            return comBirthday;
        }

        public void setComBirthday(String comBirthday) {
            this.comBirthday = comBirthday;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getComPrice() {
            return comPrice;
        }

        public void setComPrice(double comPrice) {
            this.comPrice = comPrice;
        }

        public String getGrowth() {
            return Growth;
        }

        public void setGrowth(String Growth) {
            this.Growth = Growth;
        }

        public String getJianJie() {
            return jianJie;
        }

        public void setJianJie(String jianJie) {
            this.jianJie = jianJie;
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

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getComName() {
            return comName;
        }

        public void setComName(String comName) {
            this.comName = comName;
        }

        public String getBrithCondition() {
            return brithCondition;
        }

        public void setBrithCondition(String brithCondition) {
            this.brithCondition = brithCondition;
        }

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public int getMaxCount() {
            return maxCount;
        }

        public void setMaxCount(int maxCount) {
            this.maxCount = maxCount;
        }

        public String getMessageDate() {
            return messageDate;
        }

        public void setMessageDate(String messageDate) {
            this.messageDate = messageDate;
        }
    }
}
