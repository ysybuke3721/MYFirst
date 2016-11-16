package com.slr.slrapp.beans;

import java.util.List;

/**
 * 提交订单时生成json用
 * Created by Administrator on 2016/8/16 0016.
 */
public class SubmitOrderBean {



    private   List<ListBean>  list;
    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class  ListBean{
        private int id;
        private int num;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getNum() {
            return num;
        }


        public void setNum(int num) {
            this.num = num;
        }


    }


}
