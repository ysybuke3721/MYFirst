package com.slr.slrapp.beans;

/**
 * Created by Administrator on 2016/8/10 0010.
 */
public class FarmFilterBean {
    public String imgurl;
    public int imgSource;
    public String words;
    public boolean ifchecked;
    public int id;
    public String strId;

    @Override
    public String toString() {
        return "FarmFilterBean{" +
                "imgurl='" + imgurl + '\'' +
                ", imgSource=" + imgSource +
                ", words='" + words + '\'' +
                ", ifchecked=" + ifchecked +
                ", id=" + id +
                '}';
    }
}
