package com.slr.slrapp.beans;

/**首页二级页面商品信息详情对象
 * Created by Administrator on 2016/8/2 0002.
 */
public class GoodsInfoBean {


    /**
     * message : 获取成功！
     * com : {"comStatus":"1","sort":1,"medicalHistory":"手足口病","storeName":"祖传秘制叫花鸡","QRCode":"你好","star":0.5,"wNumber":1,"spaceId":15,"recommend":"1","photo":"http://192.168.0.188:8080/slrkj/upload/cominfor/tujidandatu.png","comBirthday":"2016-08-01 16:59:04","id":1,"comPrice":0.01,"Growth":"色泽鲜艳","jianJie":"土鸡蛋 绿色营养 吃着放心","price":15,"smallPhoto":"http://192.168.0.188:8080/slrkj/upload/cominfor/2123_P_1428024360415.jpg","details":"色泽鲜艳，椭圆形色泽鲜艳，椭圆形","spaceName":"二号监控","comName":"土鸡蛋","brithCondition":"良好","storeId":29,"typeId":4,"maxCount":500}
     * code : 200
     */

    private String message;
    /**
     * comStatus : 1
     * sort : 1
     * medicalHistory : 手足口病
     * storeName : 祖传秘制叫花鸡
     * QRCode : 你好
     * star : 0.5
     * wNumber : 1
     * spaceId : 15
     * recommend : 1
     * photo : http://192.168.0.188:8080/slrkj/upload/cominfor/tujidandatu.png
     * comBirthday : 2016-08-01 16:59:04
     * id : 1
     * comPrice : 0.01
     * Growth : 色泽鲜艳
     * jianJie : 土鸡蛋 绿色营养 吃着放心
     * price : 15
     * smallPhoto : http://192.168.0.188:8080/slrkj/upload/cominfor/2123_P_1428024360415.jpg
     * details : 色泽鲜艳，椭圆形色泽鲜艳，椭圆形
     * spaceName : 二号监控
     * comName : 土鸡蛋
     * brithCondition : 良好
     * storeId : 29
     * typeId : 4
     * maxCount : 500
     */

    private ComBean com;
    private int code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ComBean getCom() {
        return com;
    }

    public void setCom(ComBean com) {
        this.com = com;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class ComBean {
        private String comStatus;
        private int sort;
        private String medicalHistory;
        private String storeName;
        private String QRCode;
        private double star;
        private int wNumber;
        private int spaceId;
        private String recommend;
        private String photo;
        private String comBirthday;
        private int id;
        private double comPrice;
        private String Growth;
        private String jianJie;
        private int price;
        private String smallPhoto;
        private String details;
        private String spaceName;
        private String comName;
        private String brithCondition;
        private int storeId;
        private int typeId;
        private int maxCount;

        public String getComStatus() {
            return comStatus;
        }

        public void setComStatus(String comStatus) {
            this.comStatus = comStatus;
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

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getQRCode() {
            return QRCode;
        }

        public void setQRCode(String QRCode) {
            this.QRCode = QRCode;
        }

        public double getStar() {
            return star;
        }

        public void setStar(double star) {
            this.star = star;
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

        public String getSpaceName() {
            return spaceName;
        }

        public void setSpaceName(String spaceName) {
            this.spaceName = spaceName;
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
    }
}
