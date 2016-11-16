package com.slr.slrapp.beans;

/**
 * User: Administrator
 * Time: 2016/7/8 0008
 * Description: ${todo}(用一句话描述该文件做什么)
 * Version V1.0
 */
public class OrderBean {

    private String FarmName;// 养殖场名字
    private String OrderType;// 订单类型
    private String ProductIcon;// 购买产品图片地址
    private String ProductName;// 购买产品名字
    private String ProductPrice;// 购买产品价格
    private String ProductNum;// 购买产品数量
    private String PostScript;// 补充说明

    public String getFarmName() {
        return FarmName;
    }

    public void setFarmName(String farmName) {
        FarmName = farmName;
    }

    public String getOrderType() {
        return OrderType;
    }

    public void setOrderType(String orderType) {
        OrderType = orderType;
    }

    public String getProductIcon() {
        return ProductIcon;
    }

    public void setProductIcon(String productIcon) {
        ProductIcon = productIcon;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(String productPrice) {
        ProductPrice = productPrice;
    }

    public String getProductNum() {
        return ProductNum;
    }

    public void setProductNum(String productNum) {
        ProductNum = productNum;
    }

    public String getPostScript() {
        return PostScript;
    }

    public void setPostScript(String postScript) {
        PostScript = postScript;
    }

    @Override
    public String toString() {
        return "OrderBean{" +
                "FarmName='" + FarmName + '\'' +
                ", OrderType='" + OrderType + '\'' +
                ", ProductIcon='" + ProductIcon + '\'' +
                ", ProductName='" + ProductName + '\'' +
                ", ProductPrice='" + ProductPrice + '\'' +
                ", ProductNum='" + ProductNum + '\'' +
                ", PostScript='" + PostScript + '\'' +
                '}';
    }
}
