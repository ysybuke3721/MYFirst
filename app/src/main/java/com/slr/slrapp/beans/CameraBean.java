package com.slr.slrapp.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/8/14 0014.
 */
public class CameraBean implements Serializable{


    /**
     * message : 获取成功！
     * comIdformation : {"sort":1,"medicalHistory":"手足口病","QRCode":"你好","wNumber":1,"recommend":"1","comBirthday":"2016-08-01 16:59:04","id":1,"Growth":"色泽鲜艳","details":"色泽鲜艳，椭圆形色泽鲜艳，椭圆形","spaceName":"二号监控","comName":"土鸡蛋","synopsis":"这是一家诚信经营的养殖厂 我们已经走过了十几个春秋 保证农产品的健康问题 客户可以放心食用","maxCount":500,"comStatus":"1","monitor":"http://vc7bd8aa.live.126.net/live/0c70e084aec84c348361ca59551e1480.flv","storeName":"祖传秘制叫花鸡","sphoto":"http://192.168.0.188:8080/service/upload/cominfor/2123_P_1428024360415.jpg","star":4.5,"promise":"充满泥土的芳香","spaceId":15,"photo":"http://192.168.0.188:8080/service/upload/cominfor/tujidandatu.png","message":[{"content":"直播","id":23,"userId":"5","comId":"1","userName":"123456","head":"http://192.168.0.188:8080/service/upload/20160823_040001.jpg","leaveDate":"2016-08-23 17:34:35"},{"content":"直播","id":24,"userId":"5","comId":"1","userName":"123456","head":"http://192.168.0.188:8080/service/upload/20160823_040001.jpg","leaveDate":"2016-08-23 17:34:35"},{"content":"哈佛商学院要求学生","id":21,"userId":"6","comId":"1","userName":"232","head":"http://192.168.0.188:8080/service/upload/internt_web_technology-13-128.png","leaveDate":"2016-08-17 23:03:48"},{"content":"哈佛商学院要求学生","id":20,"userId":"6","comId":"1","userName":"232","head":"http://192.168.0.188:8080/service/upload/internt_web_technology-13-128.png","leaveDate":"2016-08-17 23:03:43"},{"content":"好","id":11,"userId":"5","comId":"1","userName":"123456","head":"http://192.168.0.188:8080/service/upload/20160823_040001.jpg","leaveDate":"2016-08-14 18:53:57"}],"comPrice":0.01,"jianJie":"土鸡蛋 绿色营养 吃着放心","price":15,"smallPhoto":"http://192.168.0.188:8080/service/upload/cominfor/2123_P_1428024360415.jpg","brithCondition":"良好","storeId":29,"typeId":4,"brief":"正宗老牌店0000","collect":1}
     * code : 200
     */

    private String message;
    /**
     * sort : 1
     * medicalHistory : 手足口病
     * QRCode : 你好
     * wNumber : 1
     * recommend : 1
     * comBirthday : 2016-08-01 16:59:04
     * id : 1
     * Growth : 色泽鲜艳
     * details : 色泽鲜艳，椭圆形色泽鲜艳，椭圆形
     * spaceName : 二号监控
     * comName : 土鸡蛋
     * synopsis : 这是一家诚信经营的养殖厂 我们已经走过了十几个春秋 保证农产品的健康问题 客户可以放心食用
     * maxCount : 500
     * comStatus : 1
     * monitor : http://vc7bd8aa.live.126.net/live/0c70e084aec84c348361ca59551e1480.flv
     * storeName : 祖传秘制叫花鸡
     * sphoto : http://192.168.0.188:8080/service/upload/cominfor/2123_P_1428024360415.jpg
     * star : 4.5
     * promise : 充满泥土的芳香
     * spaceId : 15
     * photo : http://192.168.0.188:8080/service/upload/cominfor/tujidandatu.png
     * message : [{"content":"直播","id":23,"userId":"5","comId":"1","userName":"123456","head":"http://192.168.0.188:8080/service/upload/20160823_040001.jpg","leaveDate":"2016-08-23 17:34:35"},{"content":"直播","id":24,"userId":"5","comId":"1","userName":"123456","head":"http://192.168.0.188:8080/service/upload/20160823_040001.jpg","leaveDate":"2016-08-23 17:34:35"},{"content":"哈佛商学院要求学生","id":21,"userId":"6","comId":"1","userName":"232","head":"http://192.168.0.188:8080/service/upload/internt_web_technology-13-128.png","leaveDate":"2016-08-17 23:03:48"},{"content":"哈佛商学院要求学生","id":20,"userId":"6","comId":"1","userName":"232","head":"http://192.168.0.188:8080/service/upload/internt_web_technology-13-128.png","leaveDate":"2016-08-17 23:03:43"},{"content":"好","id":11,"userId":"5","comId":"1","userName":"123456","head":"http://192.168.0.188:8080/service/upload/20160823_040001.jpg","leaveDate":"2016-08-14 18:53:57"}]
     * comPrice : 0.01
     * jianJie : 土鸡蛋 绿色营养 吃着放心
     * price : 15
     * smallPhoto : http://192.168.0.188:8080/service/upload/cominfor/2123_P_1428024360415.jpg
     * brithCondition : 良好
     * storeId : 29
     * typeId : 4
     * brief : 正宗老牌店0000
     * collect : 1
     */

    private ComIdformationBean comIdformation;
    private int code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ComIdformationBean getComIdformation() {
        return comIdformation;
    }

    public void setComIdformation(ComIdformationBean comIdformation) {
        this.comIdformation = comIdformation;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class ComIdformationBean implements Serializable{
        private int sort;
        private String medicalHistory;
        private String QRCode;
        private int wNumber;
        private String recommend;
        private String comBirthday;
        private int id;
        private String Growth;
        private String details;
        private String spaceName;
        private String comName;
        private String synopsis;
        private int maxCount;
        private String comStatus;
        private String monitor;
        private String storeName;
        private String sphoto;
        private double star;
        private String promise;
        private int spaceId;
        private String photo;
        private double comPrice;
        private String jianJie;
        private int price;
        private String smallPhoto;
        private String brithCondition;
        private int storeId;
        private int typeId;
        private String brief;
        private int collect;
        /**
         * content : 直播
         * id : 23
         * userId : 5
         * comId : 1
         * userName : 123456
         * head : http://192.168.0.188:8080/service/upload/20160823_040001.jpg
         * leaveDate : 2016-08-23 17:34:35
         */

        private List<MessageBean> message;

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

        public String getSynopsis() {
            return synopsis;
        }

        public void setSynopsis(String synopsis) {
            this.synopsis = synopsis;
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

        public String getMonitor() {
            return monitor;
        }

        public void setMonitor(String monitor) {
            this.monitor = monitor;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getSphoto() {
            return sphoto;
        }

        public void setSphoto(String sphoto) {
            this.sphoto = sphoto;
        }

        public double getStar() {
            return star;
        }

        public void setStar(double star) {
            this.star = star;
        }

        public String getPromise() {
            return promise;
        }

        public void setPromise(String promise) {
            this.promise = promise;
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

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public int getCollect() {
            return collect;
        }

        public void setCollect(int collect) {
            this.collect = collect;
        }

        public List<MessageBean> getMessage() {
            return message;
        }

        public void setMessage(List<MessageBean> message) {
            this.message = message;
        }

        public static class MessageBean implements Serializable{
            private String content;
            private int id;
            private String userId;
            private String comId;
            private String userName;
            private String head;
            private String leaveDate;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getComId() {
                return comId;
            }

            public void setComId(String comId) {
                this.comId = comId;
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

            public String getLeaveDate() {
                return leaveDate;
            }

            public void setLeaveDate(String leaveDate) {
                this.leaveDate = leaveDate;
            }
        }
    }
}
