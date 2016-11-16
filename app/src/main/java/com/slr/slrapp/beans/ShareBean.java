package com.slr.slrapp.beans;

/**
 * Created by admin on 2016/8/24.
 */
public class ShareBean {


    /**
     * message : 分享成功！
     * content : g  邀请您使用山里人人，尽情使用吧！
     * code : 200
     * url : http://192.168.0.188:8080/service/appApi/weiRegister?recommends=5
     */

    private String message;
    private String content;
    private int code;
    private String url;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
