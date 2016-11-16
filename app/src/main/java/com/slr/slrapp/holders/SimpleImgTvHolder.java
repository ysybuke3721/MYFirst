package com.slr.slrapp.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.slr.slrapp.R;
import com.slr.slrapp.beans.FarmFilterBean;
import com.slr.slrapp.utils.UiUtils;

/**
 * Created by Administrator on 2016/7/8 0008.
 */
public class SimpleImgTvHolder extends BaseHolder<FarmFilterBean> {


    ImageView img_list_item;
    TextView list_item_tv_name;


    @Override
    protected View initView() {
        View view= UiUtils.inflate(R.layout.list_item_simple_img_tv);
        img_list_item= (ImageView) view.findViewById(R.id.iv);
        list_item_tv_name= (TextView) view.findViewById(R.id.tv);


        return view;
    }

    @Override
    protected void refreshView(FarmFilterBean firstpage_list_bean) {
        img_list_item.setImageResource(firstpage_list_bean.imgSource);
        list_item_tv_name.setText(firstpage_list_bean.words);

    }


}
