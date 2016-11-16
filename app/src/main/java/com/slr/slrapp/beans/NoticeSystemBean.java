package com.slr.slrapp.beans;

import java.util.List;

/**
 * 系统通知
 * Created by Administrator on 2016/8/13 0013.
 */
public class NoticeSystemBean {

    /**
     * message : 获取成功！
     * list : [{"content":"三级分销让你躺着也能赚大钱","id":1,"title":"三级分销","details":"http://192.168.0.188:8080/service/distribution.html","userId":"1,2,3,4,5,6,7,8,9","modifyDate":"2016-08-10 14:18:46","createDate":"2016-08-02 10:20:15"},{"content":"可以看到土鸡和圈养鸡的区别","id":2,"title":"土鸡与圈养鸡对比","details":"http://192.168.0.188:8080/service/chicken.html","userId":"5","modifyDate":"2016-08-10 14:37:15","createDate":"2016-08-10 10:20:18"}]
     * code : 200
     */

    private String message;
    private int code;
    /**
     * content : 三级分销让你躺着也能赚大钱
     * id : 1
     * title : 三级分销
     * details : http://192.168.0.188:8080/service/distribution.html
     * userId : 1,2,3,4,5,6,7,8,9
     * modifyDate : 2016-08-10 14:18:46
     * createDate : 2016-08-02 10:20:15
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
        private String content;
        private int id;
        private String title;
        private String details;
        private String userId;
        private String modifyDate;
        private String createDate;

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

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getModifyDate() {
            return modifyDate;
        }

        public void setModifyDate(String modifyDate) {
            this.modifyDate = modifyDate;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }
}
