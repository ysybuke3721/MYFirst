package com.slr.slrapp.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/8/10 0010.
 *
 * 从服务器获取的json创建的首页的信息类
 */
public class FirstPageBean implements Serializable {

    /**
     * message : 获取成功！
     * list : [{"id":2,"jianJie":"色泽鲜艳，椭圆形","comName":"土鸭蛋","photo":"http://192.168.0.188:8080/service/upload/cominfor/2123_P_1428024360415.jpg"},{"id":3,"jianJie":"漂亮的羽毛","comName":"土鸡","photo":"http://192.168.0.188:8080/service/upload/cominfor/20160808170441.jpg"}]
     * code : 200
     */

    private String message;
    private int code;
    /**
     * id : 2
     * jianJie : 色泽鲜艳，椭圆形
     * comName : 土鸭蛋
     * photo : http://192.168.0.188:8080/service/upload/cominfor/2123_P_1428024360415.jpg
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
        private String jianJie;
        private String comName;
        private String photo;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getJianJie() {
            return jianJie;
        }

        public void setJianJie(String jianJie) {
            this.jianJie = jianJie;
        }

        public String getComName() {
            return comName;
        }

        public void setComName(String comName) {
            this.comName = comName;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }
    }
}
