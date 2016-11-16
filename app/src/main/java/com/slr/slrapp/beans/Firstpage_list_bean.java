package com.slr.slrapp.beans;

import java.io.Serializable;

/**
 * 首页商品列表的类(简单的图片，id类)
 * Created by Administrator on 2016/7/1 0001.
 */
public class Firstpage_list_bean implements Serializable {
    public int goodid;
    public String description;
    public String imgUrl;
    public boolean ifchecked;
    public int cityId;
    public String lunboUrl;
    public int arrangement;  //首页轮播的顺序

    public boolean ifFirst;

    @Override
    public String toString() {
        return "Firstpage_list_bean{" +
                "goodid=" + goodid +
                ", description='" + description + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", ifchecked=" + ifchecked +
                ", cityId=" + cityId +
                ", lunboUrl='" + lunboUrl + '\'' +
                ", arrangement=" + arrangement +
                ", ifFirst=" + ifFirst +
                '}';
    }
}
