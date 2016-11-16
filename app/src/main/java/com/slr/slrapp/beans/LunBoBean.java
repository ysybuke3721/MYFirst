package com.slr.slrapp.beans;

import java.util.List;

/**
 * Created by Administrator on 2016/8/11 0011.
 */
public class LunBoBean {


    /**
     * message : 获取成功！
     * car : [{"content":"www.baidu.com","id":1,"title":"三级分销","arrangement":1,"cityId":"12","value":1,"cPhoto":"http://192.168.0.188:8080/service/upload/cominfor/0001.jpg"},{"content":"www.baidu.com","id":3,"title":"三级分销","arrangement":121,"cityId":"11","value":1,"cPhoto":"http://192.168.0.188:8080/service/upload/cominfor/0001.jpg"}]
     * code : 200
     */

    private String message;
    private int code;
    /**
     * content : www.baidu.com
     * id : 1
     * title : 三级分销
     * arrangement : 1
     * cityId : 12
     * value : 1
     * cPhoto : http://192.168.0.188:8080/service/upload/cominfor/0001.jpg
     */

    private List<CarBean> car;

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

    public List<CarBean> getCar() {
        return car;
    }

    public void setCar(List<CarBean> car) {
        this.car = car;
    }

    public static class CarBean {
        private String content;
        private int id;
        private String title;
        private int arrangement;
        private String cityId;
        private int value;
        private String cPhoto;

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getArrangement() {
            return arrangement;
        }

        public void setArrangement(int arrangement) {
            this.arrangement = arrangement;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getCPhoto() {
            return cPhoto;
        }

        public void setCPhoto(String cPhoto) {
            this.cPhoto = cPhoto;
        }
    }
}
