package com.slr.slrapp.beans;

import java.util.List;

/**
 * 我的收藏
 * Created by Administrator on 2016/8/10 0010.
 */
public class MyCollectBean {


    /**
     * message : 获取成功！
     * list : [{"medicalHistory":"拉肚子","sort":3,"QRCode":"你好","wNumber":4,"recommend":"1","comBirthday":"2016-08-02 18:30:20","id":3,"Growth":"生长良好，四肢健全","details":"色泽鲜艳，椭圆形色泽鲜艳，椭圆形","comName":"土鸡","userId":"5","createDate":"2016-08-14 18:54:58","maxCount":40,"comStatus":"1","spaceId":11,"cid":26,"photo":"http://192.168.0.188:8080/service/upload/cominfor/tujidatu.png","comPrice":0.01,"jianJie":"天赐良鸡 生态散养 食补真品","price":20,"smallPhoto":"http://192.168.0.188:8080/service/upload/cominfor/tujis.jpg","comId":"3","brithCondition":"good","typeId":3,"storeId":28},{"medicalHistory":"手足口病","sort":1,"QRCode":"你好","wNumber":1,"recommend":"1","comBirthday":"2016-08-01 16:59:04","id":1,"Growth":"色泽鲜艳","details":"色泽鲜艳，椭圆形色泽鲜艳，椭圆形","comName":"土鸡蛋","userId":"5","createDate":"2016-08-15 21:52:39","maxCount":500,"comStatus":"1","spaceId":15,"cid":35,"photo":"http://192.168.0.188:8080/service/upload/cominfor/tujidandatu.png","comPrice":0.01,"jianJie":"土鸡蛋 绿色营养 吃着放心","price":15,"smallPhoto":"http://192.168.0.188:8080/service/upload/cominfor/2123_P_1428024360415.jpg","comId":"1","brithCondition":"良好","typeId":4,"storeId":29},{"medicalHistory":null,"sort":null,"QRCode":null,"wNumber":null,"recommend":null,"comBirthday":null,"id":null,"Growth":null,"details":null,"comName":null,"userId":"5","createDate":"2016-08-24 15:45:37","maxCount":null,"comStatus":null,"spaceId":null,"cid":45,"photo":"http://192.168.0.188:8080/servicenull","comPrice":null,"jianJie":null,"price":null,"smallPhoto":"http://192.168.0.188:8080/servicenull","comId":"10","brithCondition":null,"typeId":null,"storeId":null}]
     * code : 200
     */

    private String message;
    private int code;
    /**
     * medicalHistory : 拉肚子
     * sort : 3
     * QRCode : 你好
     * wNumber : 4
     * recommend : 1
     * comBirthday : 2016-08-02 18:30:20
     * id : 3
     * Growth : 生长良好，四肢健全
     * details : 色泽鲜艳，椭圆形色泽鲜艳，椭圆形
     * comName : 土鸡
     * userId : 5
     * createDate : 2016-08-14 18:54:58
     * maxCount : 40
     * comStatus : 1
     * spaceId : 11
     * cid : 26
     * photo : http://192.168.0.188:8080/service/upload/cominfor/tujidatu.png
     * comPrice : 0.01
     * jianJie : 天赐良鸡 生态散养 食补真品
     * price : 20
     * smallPhoto : http://192.168.0.188:8080/service/upload/cominfor/tujis.jpg
     * comId : 3
     * brithCondition : good
     * typeId : 3
     * storeId : 28
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
        private String medicalHistory;
        private int sort;
        private String QRCode;
        private int wNumber;
        private String recommend;
        private String comBirthday;
        private int id;
        private String Growth;
        private String details;
        private String comName;
        private String userId;
        private String createDate;
        private int maxCount;
        private String comStatus;
        private int spaceId;
        private int cid;
        private String photo;
        private double comPrice;
        private String jianJie;
        private int price;
        private String smallPhoto;
        private String comId;
        private String brithCondition;
        private int typeId;
        private int storeId;

        public String getMedicalHistory() {
            return medicalHistory;
        }

        public void setMedicalHistory(String medicalHistory) {
            this.medicalHistory = medicalHistory;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
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

        public String getRecommend() {
            return recommend;
        }

        public void setRecommend(String recommend) {
            this.recommend = recommend;
        }

        public String getComBirthday() {
            return comBirthday;
        }

        public void setComBirthday(String comBirthday) {
            this.comBirthday = comBirthday;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGrowth() {
            return Growth;
        }

        public void setGrowth(String Growth) {
            this.Growth = Growth;
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

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public int getMaxCount() {
            return maxCount;
        }

        public void setMaxCount(int maxCount) {
            this.maxCount = maxCount;
        }

        public String getComStatus() {
            return comStatus;
        }

        public void setComStatus(String comStatus) {
            this.comStatus = comStatus;
        }

        public int getSpaceId() {
            return spaceId;
        }

        public void setSpaceId(int spaceId) {
            this.spaceId = spaceId;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public double getComPrice() {
            return comPrice;
        }

        public void setComPrice(double comPrice) {
            this.comPrice = comPrice;
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

        public String getComId() {
            return comId;
        }

        public void setComId(String comId) {
            this.comId = comId;
        }

        public String getBrithCondition() {
            return brithCondition;
        }

        public void setBrithCondition(String brithCondition) {
            this.brithCondition = brithCondition;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }
    }
}
