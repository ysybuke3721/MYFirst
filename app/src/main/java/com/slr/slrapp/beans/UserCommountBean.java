package com.slr.slrapp.beans;

/**
 *
 *
 * 用户评论的消息对象
 * Created by Administrator on 2016/7/18 0018.
 */
public class UserCommountBean {
    //用户头像
    public  String user_head;
    //用户名
    public  String user_name;
    //用户等级,根据等级去加载图片
    public String user_level_img;
    //评论的时间
    public String communt_time;
    //用户的评论
    public  String user_communt;
    //用户id；
    public String user_id;

    @Override
    public String toString() {
        return "UserCommountBean{" +
                "user_head='" + user_head + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_level_img=" + user_level_img +
                ", communt_time='" + communt_time + '\'' +
                ", user_communt='" + user_communt + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
