package com.slr.slrapp.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/8/17 0017.
 */
public class FarmLocationBean {

    /**
     * message : 获取成功！
     * store : [{"storeName":"正宗\u201c野鸡\u201d肉","openMeg":"1","wei":"34.698314139424168","regDate":"2016-08-01 16:47:59","star":"3.83","promise":"充满泥土的芳香","number":4987,"regState":"通过","modifyDate":"2016-08-09 20:34:52","openEva":"1","photo":"/upload/cominfor/2123_P_1428024360415.jpg","jing":"113.758314139424168","id":16,"message":"很不错","regName":"11","address":"科学大道144号","userName":"毛泽东","serviceCharge":"0.3","telephone":"15239999999","brief":"充满泥土的芳香","messageDate":"2016-08-09 20:34:25"},{"storeName":"土家鸡蛋","openMeg":"0","wei":"34.688314139424168","regDate":"2016-08-02 16:47:55","star":"5","promise":"物美价廉","number":7456,"regState":"通过","modifyDate":"2016-08-15 09:01:05","openEva":"0","photo":"/upload/cominfor/2123_P_1428024360415.jpg","jing":"113.808314139424168","id":17,"message":"值得买","regName":"12","address":"航海路中段","userName":"周恩来","serviceCharge":"0.2","telephone":"13876666666","brief":"秀色可餐，绝对绿色不含添加剂","messageDate":"2016-08-01 20:34:28"},{"storeName":"人人店","openMeg":"0","wei":"34.768314139424168","regDate":"2016-07-04 16:47:47","star":"3.99","promise":"物美价廉","number":632,"regState":"通过","modifyDate":"2016-08-10 20:34:55","openEva":"1","photo":"/upload/cominfor/2123_P_1428024360415.jpg","jing":"113.778314139424168","id":28,"message":"靠谱不靠谱","regName":"15","address":"大学南路","userName":"习近平","serviceCharge":"0.3","telephone":"17712345679","brief":"充满泥土的芳香","messageDate":"2016-08-02 20:34:32"},{"storeName":"祖传秘制叫花鸡","openMeg":"0","wei":"34.758314139424168","regDate":"2016-07-29 12:11:14","star":"4.33","promise":"充满泥土的芳香","number":5988,"regState":"通过","modifyDate":"2016-08-07 09:18:30","openEva":"0","photo":"/upload/cominfor/2123_P_1428024360415.jpg","jing":"113.768314139424168","id":29,"message":"有点贵","regName":"zhangsan","address":"新郑大南门","userName":"普京","serviceCharge":"0.35","telephone":"15200000000","brief":"正宗老牌店0000","messageDate":"2016-08-04 20:34:35"},{"storeName":"绝味鸡蛋","openMeg":"0","wei":"34.768318","regDate":"2016-08-02 09:56:41","star":"5","promise":"秀色可餐，绝对绿色不含添加剂","number":47,"regState":"通过","modifyDate":"2016-08-07 09:18:40","openEva":"0","photo":"/upload/cominfor/2123_P_1428024360415.jpg","jing":"113.758314139424168","id":32,"message":"买不买","regName":"123","address":"新郑市政府","userName":"奥巴马","serviceCharge":"0.1","telephone":"15939944444","brief":"肉质香甜 可口动人","messageDate":"2016-08-06 20:34:41"},{"storeName":"周黑鸭","openMeg":"0","wei":"34.778314139424168","regDate":"2016-08-04 16:51:11","star":"3.87","promise":"肉质香甜 可口动人","number":159,"regState":"通过","modifyDate":"2016-08-07 09:18:51","openEva":"1","photo":"/upload/cominfor/2123_P_1428024360415.jpg","jing":"113.748314139424168","id":33,"message":"不买算了","regName":"张昆明","address":"枫香街","userName":"金三胖","serviceCharge":"0.5","telephone":"13949667279","brief":"秀色可餐，绝对绿色不含添加剂","messageDate":"2016-08-05 20:34:37"},{"storeName":"123","openMeg":"0","wei":"34.788314139424168","regDate":"2016-08-18 20:46:13","star":"0","promise":null,"number":null,"regState":"通过","modifyDate":null,"openEva":"0","photo":null,"jing":"113.738314139424168","id":35,"message":null,"regName":"12345","address":"郑州市二七纪念塔","userName":"123","serviceCharge":"0.2","telephone":"12345678945","brief":null,"messageDate":null}]
     * code : 200
     */

    private String message;
    private int code;
    /**
     * storeName : 正宗“野鸡”肉
     * openMeg : 1
     * wei : 34.698314139424168
     * regDate : 2016-08-01 16:47:59
     * star : 3.83
     * promise : 充满泥土的芳香
     * number : 4987
     * regState : 通过
     * modifyDate : 2016-08-09 20:34:52
     * openEva : 1
     * photo : /upload/cominfor/2123_P_1428024360415.jpg
     * jing : 113.758314139424168
     * id : 16
     * message : 很不错
     * regName : 11
     * address : 科学大道144号
     * userName : 毛泽东
     * serviceCharge : 0.3
     * telephone : 15239999999
     * brief : 充满泥土的芳香
     * messageDate : 2016-08-09 20:34:25
     */

    private List<StoreBean> store;

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

    public List<StoreBean> getStore() {
        return store;
    }

    public void setStore(List<StoreBean> store) {
        this.store = store;
    }

    public static class StoreBean implements Serializable {
        private String storeName;
        private String openMeg;
        private String wei;
        private String regDate;
        private String star;
        private String promise;
        private int number;
        private String regState;
        private String modifyDate;
        private String openEva;
        private String photo;
        private String jing;
        private int id;
        private String message;
        private String regName;
        private String address;
        private String userName;
        private String serviceCharge;
        private String telephone;
        private String brief;
        private String messageDate;

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getOpenMeg() {
            return openMeg;
        }

        public void setOpenMeg(String openMeg) {
            this.openMeg = openMeg;
        }

        public String getWei() {
            return wei;
        }

        public void setWei(String wei) {
            this.wei = wei;
        }

        public String getRegDate() {
            return regDate;
        }

        public void setRegDate(String regDate) {
            this.regDate = regDate;
        }

        public String getStar() {
            return star;
        }

        public void setStar(String star) {
            this.star = star;
        }

        public String getPromise() {
            return promise;
        }

        public void setPromise(String promise) {
            this.promise = promise;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getRegState() {
            return regState;
        }

        public void setRegState(String regState) {
            this.regState = regState;
        }

        public String getModifyDate() {
            return modifyDate;
        }

        public void setModifyDate(String modifyDate) {
            this.modifyDate = modifyDate;
        }

        public String getOpenEva() {
            return openEva;
        }

        public void setOpenEva(String openEva) {
            this.openEva = openEva;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getJing() {
            return jing;
        }

        public void setJing(String jing) {
            this.jing = jing;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getRegName() {
            return regName;
        }

        public void setRegName(String regName) {
            this.regName = regName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getServiceCharge() {
            return serviceCharge;
        }

        public void setServiceCharge(String serviceCharge) {
            this.serviceCharge = serviceCharge;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public String getMessageDate() {
            return messageDate;
        }

        public void setMessageDate(String messageDate) {
            this.messageDate = messageDate;
        }
    }
}
