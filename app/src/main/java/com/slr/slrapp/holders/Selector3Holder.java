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
public class Selector3Holder extends BaseHolder<FarmFilterBean> {
    TextView tv;
    ImageView iv;
    @Override
    protected View initView() {
        View view= UiUtils.inflate(R.layout.grid_view_item);
        tv= (TextView) view.findViewById(R.id.tv);
        iv= (ImageView) view.findViewById(R.id.iv);
        return view;
    }

    @Override
    protected void refreshView(FarmFilterBean firstpage_list_bean) {
        tv.setText(firstpage_list_bean.words);
        iv.setImageResource(firstpage_list_bean.imgSource);
    }
}
