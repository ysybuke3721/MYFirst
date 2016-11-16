package com.slr.slrapp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 *
 * 这是一个用来嵌套Listview的Listview
 * Created by Administrator on 2016/8/3 0003.
 */
public class MyDoubleListview extends ListView {
    public MyDoubleListview(Context context) {
        super(context);
    }

    public MyDoubleListview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyDoubleListview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
