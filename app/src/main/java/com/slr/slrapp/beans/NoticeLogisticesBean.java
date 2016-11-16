package com.slr.slrapp.beans;

import java.util.List;

/**
 * Created by Administrator on 2016/8/13 0013.
 */
public class NoticeLogisticesBean {

    /**
     * message : 获取成功！
     * list : [{"sendDate":"2016-08-01 17:57:27","id":1,"readStatus":"1","comStatus":"已发货","comName":"张二蛋","orderNumber":"20160803998728","userId":5,"comPhoto":"http://192.168.0.188:8080/service/upload/cominfor/2123_P_1428024360415.jpg"},{"sendDate":"2016-08-02 17:57:31","id":2,"readStatus":"1","comStatus":"已发货","comName":"土鸡","orderNumber":"20160803998728","userId":5,"comPhoto":"http://192.168.0.188:8080/service/upload/cominfor/2123_P_1428024360415.jpg"},{"sendDate":"2016-08-04 17:57:33","id":3,"readStatus":"1","comStatus":"已发货","comName":"土鸭蛋","orderNumber":"20160803998728","userId":5,"comPhoto":"http://192.168.0.188:8080/service/upload/cominfor/2123_P_1428024360415.jpg"},{"sendDate":"2016-08-13 17:57:40","id":4,"readStatus":"1","comStatus":"已发货","comName":"土鸭","orderNumber":"20160803998728","userId":5,"comPhoto":"http://192.168.0.188:8080/service/upload/cominfor/2123_P_1428024360415.jpg"},{"sendDate":"2016-08-13 17:58:04","id":5,"readStatus":"1","comStatus":"已发货","comName":"土鹅蛋","orderNumber":"20160803998728","userId":5,"comPhoto":"http://192.168.0.188:8080/service/upload/cominfor/2123_P_1428024360415.jpg"},{"sendDate":"2016-08-13 17:58:06","id":6,"readStatus":"1","comStatus":"已发货","comName":"土鹅","orderNumber":"20160803998728","userId":5,"comPhoto":"http://192.168.0.188:8080/service/upload/cominfor/2123_P_1428024360415.jpg"}]
     * code : 200
     */

    private String message;
    private int code;
    /**
     * sendDate : 2016-08-01 17:57:27
     * id : 1
     * readStatus : 1
     * comStatus : 已发货
     * comName : 张二蛋
     * orderNumber : 20160803998728
     * userId : 5
     * comPhoto : http://192.168.0.188:8080/service/upload/cominfor/2123_P_1428024360415.jpg
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
        private String sendDate;
        private int id;
        private String readStatus;
        private String comStatus;
        private String comName;
        private String orderNumber;
        private int userId;
        private String comPhoto;

        public String getSendDate() {
            return sendDate;
        }

        public void setSendDate(String sendDate) {
            this.sendDate = sendDate;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getReadStatus() {
            return readStatus;
        }

        public void setReadStatus(String readStatus) {
            this.readStatus = readStatus;
        }

        public String getComStatus() {
            return comStatus;
        }

        public void setComStatus(String comStatus) {
            this.comStatus = comStatus;
        }

        public String getComName() {
            return comName;
        }

        public void setComName(String comName) {
            this.comName = comName;
        }

        public String getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getComPhoto() {
            return comPhoto;
        }

        public void setComPhoto(String comPhoto) {
            this.comPhoto = comPhoto;
        }
    }
}
