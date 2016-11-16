package com.slr.slrapp.beans;

/**
 * 我的界面获取用户信息
 * 收藏 我的消息
 */
public class UserInfoBean {

    /**
     * message : 获取成功
     * isMember : 1
     * code : 200
     * user : {"sex":"男","orderTotleNumber":20,"modifyDate":"2016-08-05 10:58:23","from":"app","transactionMessage":0,"messageNum":0,"id":5,"orderTotlePrice":"10000","nickName":"我去。","chiefs":null,"token":"918363","age":15,"noticeMessage":0,"userName":"12122","question":"小学班主任的名字","createDate":"2016-08-01 14:28:20","head":"http://192.168.0.188:8080/service/upload/internt_web_technology-13-128.png","remainMoney":"725.0","signature":"我去。","chief":"5","collectNum":3,"chiefss":null,"answer":"好难看","logisticsMessage":0,"address":"河南郑州","email":"79137@qq.com","defaultAddress":"山西省太原市小店区小店","lastOrderDate":"2016-08-10 14:43:43","telephone":"13949667279"}
     */

    private String message;
    private int isMember;
    private int code;
    /**
     * sex : 男
     * orderTotleNumber : 20
     * modifyDate : 2016-08-05 10:58:23
     * from : app
     * transactionMessage : 0
     * messageNum : 0
     * id : 5
     * orderTotlePrice : 10000
     * nickName : 我去。
     * chiefs : null
     * token : 918363
     * age : 15
     * noticeMessage : 0
     * userName : 12122
     * question : 小学班主任的名字
     * createDate : 2016-08-01 14:28:20
     * head : http://192.168.0.188:8080/service/upload/internt_web_technology-13-128.png
     * remainMoney : 725.0
     * signature : 我去。
     * chief : 5
     * collectNum : 3
     * chiefss : null
     * answer : 好难看
     * logisticsMessage : 0
     * address : 河南郑州
     * email : 79137@qq.com
     * defaultAddress : 山西省太原市小店区小店
     * lastOrderDate : 2016-08-10 14:43:43
     * telephone : 13949667279
     */

    private UserBean user;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getIsMember() {
        return isMember;
    }

    public void setIsMember(int isMember) {
        this.isMember = isMember;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        private String sex;
        private int orderTotleNumber;
        private String modifyDate;
        private String from;
        private int transactionMessage;
        private int messageNum;
        private int id;
        private String orderTotlePrice;
        private String nickName;
        private Object chiefs;
        private String token;
        private int age;
        private int noticeMessage;
        private String userName;
        private String question;
        private String createDate;
        private String head;
        private String remainMoney;
        private String signature;
        private String chief;
        private int collectNum;
        private Object chiefss;
        private String answer;
        private int logisticsMessage;
        private String address;
        private String email;
        private String defaultAddress;
        private String lastOrderDate;
        private String telephone;

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public int getOrderTotleNumber() {
            return orderTotleNumber;
        }

        public void setOrderTotleNumber(int orderTotleNumber) {
            this.orderTotleNumber = orderTotleNumber;
        }

        public String getModifyDate() {
            return modifyDate;
        }

        public void setModifyDate(String modifyDate) {
            this.modifyDate = modifyDate;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public int getTransactionMessage() {
            return transactionMessage;
        }

        public void setTransactionMessage(int transactionMessage) {
            this.transactionMessage = transactionMessage;
        }

        public int getMessageNum() {
            return messageNum;
        }

        public void setMessageNum(int messageNum) {
            this.messageNum = messageNum;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOrderTotlePrice() {
            return orderTotlePrice;
        }

        public void setOrderTotlePrice(String orderTotlePrice) {
            this.orderTotlePrice = orderTotlePrice;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public Object getChiefs() {
            return chiefs;
        }

        public void setChiefs(Object chiefs) {
            this.chiefs = chiefs;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getNoticeMessage() {
            return noticeMessage;
        }

        public void setNoticeMessage(int noticeMessage) {
            this.noticeMessage = noticeMessage;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getRemainMoney() {
            return remainMoney;
        }

        public void setRemainMoney(String remainMoney) {
            this.remainMoney = remainMoney;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getChief() {
            return chief;
        }

        public void setChief(String chief) {
            this.chief = chief;
        }

        public int getCollectNum() {
            return collectNum;
        }

        public void setCollectNum(int collectNum) {
            this.collectNum = collectNum;
        }

        public Object getChiefss() {
            return chiefss;
        }

        public void setChiefss(Object chiefss) {
            this.chiefss = chiefss;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public int getLogisticsMessage() {
            return logisticsMessage;
        }

        public void setLogisticsMessage(int logisticsMessage) {
            this.logisticsMessage = logisticsMessage;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getDefaultAddress() {
            return defaultAddress;
        }

        public void setDefaultAddress(String defaultAddress) {
            this.defaultAddress = defaultAddress;
        }

        public String getLastOrderDate() {
            return lastOrderDate;
        }

        public void setLastOrderDate(String lastOrderDate) {
            this.lastOrderDate = lastOrderDate;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }
    }
}
