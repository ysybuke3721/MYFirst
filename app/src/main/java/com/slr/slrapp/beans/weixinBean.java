package com.slr.slrapp.beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/8/19 0019.
 */
public class weixinBean {


    /**
     * message : 保存订单成功！
     * payMode : 0
     * payInfo : {"timestamp":"1471591501","partnerid":"1371935402","signs":"D1178E5E2A6CD1357AD4FEA0B42D8410","prepay_id":"wx2016081915243350c31a66e60539053924","package":"Sign=wx2016081915243350c31a66e60539053924","appid":"wx70997146974e950c","nonce_str":"MTFF8oSbzzyWBGdH"}
     * code : 300
     */

    private String message;
    private String payMode;
    /**
     * timestamp : 1471591501
     * partnerid : 1371935402
     * signs : D1178E5E2A6CD1357AD4FEA0B42D8410
     * prepay_id : wx2016081915243350c31a66e60539053924
     * package : Sign=wx2016081915243350c31a66e60539053924
     * appid : wx70997146974e950c
     * nonce_str : MTFF8oSbzzyWBGdH
     */

    private PayInfoBean payInfo;
    private int code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }

    public PayInfoBean getPayInfo() {
        return payInfo;
    }

    public void setPayInfo(PayInfoBean payInfo) {
        this.payInfo = payInfo;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public class PayInfoBean {
        private String timestamp;
        private String partnerid;
        private String signs;
        private String prepay_id;
        @SerializedName("package")
        private String packageX;
        private String appid;
        private String nonce_str;

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getSigns() {
            return signs;
        }

        public void setSigns(String signs) {
            this.signs = signs;
        }

        public String getPrepay_id() {
            return prepay_id;
        }

        public void setPrepay_id(String prepay_id) {
            this.prepay_id = prepay_id;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getNonce_str() {
            return nonce_str;
        }

        public void setNonce_str(String nonce_str) {
            this.nonce_str = nonce_str;
        }
    }
}
