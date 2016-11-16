package com.slr.slrapp.beans;

import java.util.List;

/**
 * Created by admin on 2016/8/7.
 */
public class MyOrderBean {


    /**
     * message : 获取成功！
     * cartList : [{"totleNumber":5,"totlePrice":75,"orderDate":"2016-08-03 17:19:02","oid":6,"orderNumber":"20160803998728","state":"已发货","list":[{"sort":1,"medicalHistory":"手足口病","QRCode":"你好","szprice":15,"wNumber":1,"recommend":"1","freightMoney":"0.00","comBirthday":"2016-08-01 16:59:04","id":1,"comNumber":2,"Growth":"色泽鲜艳","details":"色泽鲜艳，椭圆形色泽鲜艳，椭圆形","totlePrice":"30","comName":"土鸡蛋","szId":5,"maxCount":500,"comStatus":"1","storeName":"祖传秘制叫花鸡","status":"未付款","spaceId":15,"photo":"http://192.168.0.188:8080/service/upload/cominfor/tujidandatu.png","comPrice":0.01,"jianJie":"土鸡蛋 绿色营养 吃着放心","price":15,"smallPhoto":"http://192.168.0.188:8080/service/upload/cominfor/2123_P_1428024360415.jpg","brithCondition":"良好","typeId":4,"storeId":29},{"sort":3,"medicalHistory":"拉肚子","QRCode":"你好","szprice":15,"wNumber":4,"recommend":"1","freightMoney":"0.00","comBirthday":"2016-08-02 18:30:20","id":3,"comNumber":1,"Growth":"生长良好，四肢健全","details":"色泽鲜艳，椭圆形色泽鲜艳，椭圆形","totlePrice":"15","comName":"土鸡","szId":7,"maxCount":40,"comStatus":"1","storeName":"人人店","status":"待出库","spaceId":11,"photo":"http://192.168.0.188:8080/service/upload/cominfor/tujidatu.png","comPrice":0.01,"jianJie":"天赐良鸡 生态散养 食补真品","price":20,"smallPhoto":"http://192.168.0.188:8080/service/upload/cominfor/tujis.jpg","brithCondition":"good","typeId":3,"storeId":28},{"sort":null,"medicalHistory":null,"QRCode":null,"szprice":15,"wNumber":null,"recommend":null,"freightMoney":"0.00","comBirthday":null,"id":null,"comNumber":1,"Growth":null,"details":null,"totlePrice":"15","comName":null,"szId":8,"maxCount":null,"comStatus":null,"storeName":null,"status":"未付款","spaceId":null,"photo":"http://192.168.0.188:8080/servicenull","comPrice":null,"jianJie":null,"price":null,"smallPhoto":"http://192.168.0.188:8080/servicenull","brithCondition":null,"typeId":null,"storeId":null},{"sort":null,"medicalHistory":null,"QRCode":null,"szprice":15,"wNumber":null,"recommend":null,"freightMoney":"0.00","comBirthday":null,"id":null,"comNumber":1,"Growth":null,"details":null,"totlePrice":"15","comName":null,"szId":10,"maxCount":null,"comStatus":null,"storeName":null,"status":"已发货","spaceId":null,"photo":"http://192.168.0.188:8080/servicenull","comPrice":null,"jianJie":null,"price":null,"smallPhoto":"http://192.168.0.188:8080/servicenull","brithCondition":null,"typeId":null,"storeId":null}]}]
     * code : 200
     */

    private String message;
    private int code;
    /**
     * totleNumber : 5
     * totlePrice : 75
     * orderDate : 2016-08-03 17:19:02
     * oid : 6
     * orderNumber : 20160803998728
     * state : 已发货
     * list : [{"sort":1,"medicalHistory":"手足口病","QRCode":"你好","szprice":15,"wNumber":1,"recommend":"1","freightMoney":"0.00","comBirthday":"2016-08-01 16:59:04","id":1,"comNumber":2,"Growth":"色泽鲜艳","details":"色泽鲜艳，椭圆形色泽鲜艳，椭圆形","totlePrice":"30","comName":"土鸡蛋","szId":5,"maxCount":500,"comStatus":"1","storeName":"祖传秘制叫花鸡","status":"未付款","spaceId":15,"photo":"http://192.168.0.188:8080/service/upload/cominfor/tujidandatu.png","comPrice":0.01,"jianJie":"土鸡蛋 绿色营养 吃着放心","price":15,"smallPhoto":"http://192.168.0.188:8080/service/upload/cominfor/2123_P_1428024360415.jpg","brithCondition":"良好","typeId":4,"storeId":29},{"sort":3,"medicalHistory":"拉肚子","QRCode":"你好","szprice":15,"wNumber":4,"recommend":"1","freightMoney":"0.00","comBirthday":"2016-08-02 18:30:20","id":3,"comNumber":1,"Growth":"生长良好，四肢健全","details":"色泽鲜艳，椭圆形色泽鲜艳，椭圆形","totlePrice":"15","comName":"土鸡","szId":7,"maxCount":40,"comStatus":"1","storeName":"人人店","status":"待出库","spaceId":11,"photo":"http://192.168.0.188:8080/service/upload/cominfor/tujidatu.png","comPrice":0.01,"jianJie":"天赐良鸡 生态散养 食补真品","price":20,"smallPhoto":"http://192.168.0.188:8080/service/upload/cominfor/tujis.jpg","brithCondition":"good","typeId":3,"storeId":28},{"sort":null,"medicalHistory":null,"QRCode":null,"szprice":15,"wNumber":null,"recommend":null,"freightMoney":"0.00","comBirthday":null,"id":null,"comNumber":1,"Growth":null,"details":null,"totlePrice":"15","comName":null,"szId":8,"maxCount":null,"comStatus":null,"storeName":null,"status":"未付款","spaceId":null,"photo":"http://192.168.0.188:8080/servicenull","comPrice":null,"jianJie":null,"price":null,"smallPhoto":"http://192.168.0.188:8080/servicenull","brithCondition":null,"typeId":null,"storeId":null},{"sort":null,"medicalHistory":null,"QRCode":null,"szprice":15,"wNumber":null,"recommend":null,"freightMoney":"0.00","comBirthday":null,"id":null,"comNumber":1,"Growth":null,"details":null,"totlePrice":"15","comName":null,"szId":10,"maxCount":null,"comStatus":null,"storeName":null,"status":"已发货","spaceId":null,"photo":"http://192.168.0.188:8080/servicenull","comPrice":null,"jianJie":null,"price":null,"smallPhoto":"http://192.168.0.188:8080/servicenull","brithCondition":null,"typeId":null,"storeId":null}]
     */

    private List<CartListBean> cartList;

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

    public List<CartListBean> getCartList() {
        return cartList;
    }

    public void setCartList(List<CartListBean> cartList) {
        this.cartList = cartList;
    }

    public static class CartListBean {
        private int totleNumber;
        private double totlePrice;
        private String orderDate;
        private int oid;
        private String orderNumber;
        private String state;
        /**
         * sort : 1
         * medicalHistory : 手足口病
         * QRCode : 你好
         * szprice : 15
         * wNumber : 1
         * recommend : 1
         * freightMoney : 0.00
         * comBirthday : 2016-08-01 16:59:04
         * id : 1
         * comNumber : 2
         * Growth : 色泽鲜艳
         * details : 色泽鲜艳，椭圆形色泽鲜艳，椭圆形
         * totlePrice : 30
         * comName : 土鸡蛋
         * szId : 5
         * maxCount : 500
         * comStatus : 1
         * storeName : 祖传秘制叫花鸡
         * status : 未付款
         * spaceId : 15
         * photo : http://192.168.0.188:8080/service/upload/cominfor/tujidandatu.png
         * comPrice : 0.01
         * jianJie : 土鸡蛋 绿色营养 吃着放心
         * price : 15
         * smallPhoto : http://192.168.0.188:8080/service/upload/cominfor/2123_P_1428024360415.jpg
         * brithCondition : 良好
         * typeId : 4
         * storeId : 29
         */

        private List<ListBean> list;

        public int getTotleNumber() {
            return totleNumber;
        }

        public void setTotleNumber(int totleNumber) {
            this.totleNumber = totleNumber;
        }

        public double getTotlePrice() {
            return totlePrice;
        }

        public void setTotlePrice(int totlePrice) {
            this.totlePrice = totlePrice;
        }

        public String getOrderDate() {
            return orderDate;
        }

        public void setOrderDate(String orderDate) {
            this.orderDate = orderDate;
        }

        public int getOid() {
            return oid;
        }

        public void setOid(int oid) {
            this.oid = oid;
        }

        public String getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private int sort;
            private String medicalHistory;
            private String QRCode;
            private double szprice;
            private int wNumber;
            private String recommend;
            private String freightMoney;
            private String comBirthday;
            private int id;
            private int comNumber;
            private String Growth;
            private String details;
            private String totlePrice;
            private String comName;
            private int szId;
            private int maxCount;
            private String comStatus;
            private String storeName;
            private String status;
            private int spaceId;
            private String photo;
            private double comPrice;
            private String jianJie;
            private int price;
            private String smallPhoto;
            private String brithCondition;
            private int typeId;
            private int storeId;

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

            public double getSzprice() {
                return szprice;
            }

            public void setSzprice(int szprice) {
                this.szprice = szprice;
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

            public String getFreightMoney() {
                return freightMoney;
            }

            public void setFreightMoney(String freightMoney) {
                this.freightMoney = freightMoney;
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

            public int getComNumber() {
                return comNumber;
            }

            public void setComNumber(int comNumber) {
                this.comNumber = comNumber;
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

            public String getTotlePrice() {
                return totlePrice;
            }

            public void setTotlePrice(String totlePrice) {
                this.totlePrice = totlePrice;
            }

            public String getComName() {
                return comName;
            }

            public void setComName(String comName) {
                this.comName = comName;
            }

            public int getSzId() {
                return szId;
            }

            public void setSzId(int szId) {
                this.szId = szId;
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

            public String getStoreName() {
                return storeName;
            }

            public void setStoreName(String storeName) {
                this.storeName = storeName;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public int getSpaceId() {
                return spaceId;
            }

            public void setSpaceId(int spaceId) {
                this.spaceId = spaceId;
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
}
