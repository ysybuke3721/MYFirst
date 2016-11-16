package com.slr.slrapp.beans;

/**
 * Created by Administrator on 2016/8/16 0016.
 */
public class DefaultAddressBean {
    /**
     * message : 获取默认收货地址成功
     * address : {"receiptAddress":"湖南省长沙市芙蓉区,芙蓉","id":47,"weidu":"28.203810552354743","jingdu":"113.02096885648916","userId":5,"receiptTel":"18755532146","ifAddress":"1","receiptName":"小明同学","delFlag":"1"}
     * code : 200
     */

    private String message;
    /**
     * receiptAddress : 湖南省长沙市芙蓉区,芙蓉
     * id : 47
     * weidu : 28.203810552354743
     * jingdu : 113.02096885648916
     * userId : 5
     * receiptTel : 18755532146
     * ifAddress : 1
     * receiptName : 小明同学
     * delFlag : 1
     */

    private AddressBean address;
    private int code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class AddressBean {
        private String receiptAddress;
        private int id;
        private String weidu;
        private String jingdu;
        private int userId;
        private String receiptTel;
        private String ifAddress;
        private String receiptName;
        private String delFlag;

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

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }
    }
}
