package com.slr.slrapp.beans;

/**
 * Created by Administrator on 2016/7/2 0002.
 */
public class FarmerPageLIstBean {
    //图片地址（id）
    public String imgUrl;
    //商品id
    public int id;
    //养殖场所在区域
    public String goodsArea;
    //评价星星的数量(每次0.5个)
    public float rBarCounts;
    //评价数量
    public int appraiseCounts;
    //养殖场养殖品种
    public String farmWhat;
    //具体位置
    public String farmwhere;


    @Override
    public String toString() {
        return "FarmerPageLIstBean{" +
                "imgUrl=" + imgUrl +
                ", goodsArea='" + goodsArea + '\'' +
                ", rBarCounts=" + rBarCounts +
                ", appraiseCounts='" + appraiseCounts + '\'' +
                ", farmWhat='" + farmWhat + '\'' +
                ", farmLoction='" + farmwhere + '\'' +
                '}';
    }

    public FarmerPageLIstBean() {
    }
}
