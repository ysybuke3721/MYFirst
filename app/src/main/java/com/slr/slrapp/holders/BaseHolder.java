package com.slr.slrapp.holders;

import android.view.View;

/**
 * Created by Administrator on 2016/7/2 0002.
 */
public abstract class BaseHolder<Data> {
    public View contentView;
    private Data data;

    public BaseHolder() {
        this.contentView = initView();
        contentView.setTag(this);
    }



    public void setData(Data data) {
        this.data = data;
        refreshView(data);
    }

    public View getContentView() {
        return contentView;
    }
    /**
     * view对象的创建 & 初始化操作
     * @return
     */
    protected abstract View initView();
    /**
     * 将数据适配到界面上
     * @param data
     */
    protected abstract void refreshView(Data data) ;


}
