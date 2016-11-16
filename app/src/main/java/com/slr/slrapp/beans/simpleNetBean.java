package com.slr.slrapp.beans;

/**
 * Created by Administrator on 2016/8/14 0014.
 */
public class simpleNetBean {
    private String message;

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "simpleNetBean{" +
                "message='" + message + '\'' +
                ", code=" + code +
                '}';
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private int code;
}
