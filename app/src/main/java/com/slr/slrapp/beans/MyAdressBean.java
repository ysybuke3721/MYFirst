package com.slr.slrapp.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/8/11 0011.
 */
public class MyAdressBean implements Serializable{
    /**
     * list : [{"receiptAddress":"郑州市高新区郑州轻工业学院","id":1,"weidu":"55","jingdu":"112","userId":29,"receiptTel":"13949667279","ifAddress":"0","receiptName":"张三"},{"receiptAddress":"郑州市中原区郑州大学","id":2,"weidu":"67","jingdu":"113","userId":29,"receiptTel":"18237078079","ifAddress":"1","receiptName":"李四"},{"receiptAddress":"郑州市二七区滨湖国际城1805","id":3,"weidu":"85","jingdu":"114","userId":29,"receiptTel":"13303815712","ifAddress":"0","receiptName":"王五"},{"receiptAddress":"河南郑州","id":5,"weidu":"34.756610064140257","jingdu":"113.64964384986449","userId":29,"receiptTel":"15542658430","ifAddress":"0","receiptName":"刘峰"}]
     * code : 200
     */

    private int code;
    /**
     * receiptAddress : 郑州市高新区郑州轻工业学院
     * id : 1
     * weidu : 55
     * jingdu : 112
     * userId : 29
     * receiptTel : 13949667279
     * ifAddress : 0
     * receiptName : 张三
     */

    private List<ListBean> list;

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

    public static class ListBean implements Serializable {
        private String receiptAddress;
        private int id;
        private String weidu;
        private String jingdu;
        private int userId;
        private String receiptTel;
        private String ifAddress;
        private String receiptName;


        public String getReceiptAddress() {
            return receiptAddress;
        }

        public void setReceiptAddress(String receiptAddress) {
            this.receiptAddress = receiptAddress;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getWeidu() {
            return weidu;
        }

        public void setWeidu(String weidu) {
            this.weidu = weidu;
        }

        public String getJingdu() {
            return jingdu;
        }

        public void setJingdu(String jingdu) {
            this.jingdu = jingdu;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getReceiptTel() {
            return receiptTel;
        }

        public void setReceiptTel(String receiptTel) {
            this.receiptTel = receiptTel;
        }

        public String getIfAddress() {
            return ifAddress;
        }

        public void setIfAddress(String ifAddress) {
            this.ifAddress = ifAddress;
        }

        public String getReceiptName() {
            return receiptName;
        }

        public void setReceiptName(String receiptName) {
            this.receiptName = receiptName;
        }
    }
}
