package com.slr.slrapp.gson;

/**
 * 接口类
 * Created by hkf on 2016/1/31 08:43
 */
public class ApiUtils {

//    // 接口公共地址
//    public final static String HOST = "http://192.168.0.188:8080";
    // 接口公共地址
    public final static String HOST = "http://115.28.184.116";

    // 移动端接口
    public final static String APP_API = HOST + "/slrkj/appApi";


    // 服务协议
    public final static String FWXY = HOST + "/slrkj/provision.html";

    // 官网
    public static String About() {
        return HOST + "/slrkj/indexApp.html";
    }

    // 登录
    public static String getLoginURL() {
        return APP_API + "/login";
    }

    // 检查更新
    public static String getUpdateURL() {
        return APP_API + "/checkVersion";
    }

    // 获取消息
    public static String getNoticeURL() {
        return APP_API + "/getNotice";
    }

    // 下载
    public static String getUploadProtrait() {
        return APP_API + "/api/updatePortrait";
    }

    // 获取经销商信息
    public static String getMyDistributor(){
        return APP_API + "/myDistributor" ;
    }

    // 获取我的余额信息
    public static String getMyWallet(String startDate, String userId, int num){
        System.out.println("我的余额："+APP_API + "/walletList?"
                +"&startDate="+startDate
                +"&userId="+userId
                +"&num="+num);
        return APP_API + "/walletList?"
                +"&startDate="+startDate
                +"&userId="+userId
                +"&num="+num;
    }

    // 用户提现对账信息
    public static String getWithDraw(int userId){
        System.out.println("提现信息："+ APP_API + "/userBuyEmail?"
                +"&userId="+userId);
        return APP_API + "/userBuyEmail?"
                +"&userId="+userId;
    }

    // 用户提现
    public static String WithDraw(int userId, double price){
        System.out.println("提现："+ APP_API + "/withdraw?"
                +"&userId="+userId
                +"&price="+price);
        return APP_API + "/withdraw?"
                +"&userId="+userId
                +"&price="+price;
    }

    // 检查更新
    public static String Update(){
        System.out.println("检查更新："+ APP_API + "/updateEdition");
        return APP_API + "/updateEdition";
    }

    // 反馈
    public static String FeedBack(){
        System.out.println("反馈："+ APP_API + "/feedback");
        return APP_API + "/feedback";
    }

    // 确认收货
    public static String ConfirmReceipt(int oid){
        System.out.println("反馈："+APP_API + "/recipient?oid="
                +oid);
        return APP_API + "/recipient?oid="
                +oid;
    }

    // 评价
    public static String Comment(){
        System.out.println("评价："+ APP_API + "/addEvaluate");
        return APP_API + "/addEvaluate";
    }

    // 退单
    public static String OrderBack(){
        System.out.println("退单："+ APP_API + "/refundList");
        return APP_API + "/refundList";
    }

    // 删除订单
    public static String OrderDelete(){
        System.out.println("删除订单："+ APP_API +"/deleteOrder");
        return APP_API + "/deleteOrder";
    }

    // 新建收货地址
    public static String AddAdrsee(){
        System.out.println("新建收货地址："+ APP_API +"/addressSave");
        return APP_API + "/addressSave";
    }

    // 编辑更新收货地址
    public static String UpdataAdrsee(){
        System.out.println("编辑更新收货地址："+ APP_API +"/addressUpdate");
        return APP_API + "/addressUpdate";
    }

    // 收藏列表
    public static String SearchCollect(){
        System.out.println("我的收藏："+ APP_API +"/searchCollect");
        return APP_API + "/searchCollect";
    }

    // 删除收藏
    public static String CollectDelete(){
        System.out.println("删除收藏："+ APP_API +"/deleteCollect");
        return APP_API + "/deleteCollect";
    }

    // 我的评价列表
    public static String MyEvaluate(){
        System.out.println("我的评价列表："+ APP_API +"/evaluate");
        return APP_API + "/evaluate";
    }

    // 物流通知列表
    public static String LogisticsList(){
        return APP_API + "/logisticsList";
    }

    // 平台消息列表
    public static String TransactionList(){
        System.out.println("平台消息列表："+ APP_API +"/transactionList");
        return APP_API + "/transactionList";
    }

    // 系统消息列表
    public static String NoticeList(){
        System.out.println("系统消息列表："+ APP_API +"/noticeList");
        return APP_API + "/noticeList";
    }

    // 用户详情
    public static String GetUser(String userId){
        System.out.println("用户详情："+ APP_API +"/getUser?userId="+userId);
        return APP_API + "/getUser?userId="+userId;
    }

    // 更新用户信息
    public static String UpdataUser(){
        System.out.println("更新用户信息："+ APP_API +"/updata");
        return APP_API + "/updata";
    }

    // 上传用户头像
    public static String UpdataHead(){
        System.out.println("上传用户头像："+ APP_API +"/updataHead");
        return APP_API + "/updataHead";
    }


    // 退单列表
    public static String Refund(String userId, int number){
        System.out.println("退单："+ APP_API + "/refund?userId="+userId+"&number="+number);
        return APP_API + "/refund?userId="+userId+"&number="+number;
    }

    // 退单列表删除
    public static String RefundDelete(int id){
        System.out.println("退单："+ APP_API + "/refundDelete?id="+id);
        return APP_API + "/refundDelete?id="+id;
    }

    // 修改收货地址
    public static String AddressUpdate(){
        System.out.println("退单："+ APP_API + "/addressUpdate");
        return APP_API + "/addressUpdate";
    }
}
