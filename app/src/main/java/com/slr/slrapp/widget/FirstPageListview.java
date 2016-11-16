package com.slr.slrapp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import com.slr.slrapp.interfaces.Pullable;

/**
 * Created by Administrator on 2016/8/11 0011.
 */
public class FirstPageListview extends ListView implements Pullable {
    public FirstPageListview(Context context) {
        super(context);
    }

    public FirstPageListview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FirstPageListview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean canPullDown() {
        if (getCount() == 0) {
            // 没有item的时候也可以下拉刷新
            return true;
        } else if (getFirstVisiblePosition() == 0
                && getChildAt(0).getTop() >= 0) {
            // 滑到ListView的顶部了
            return true;
        } else
            return false;
    }

    @Override
    public boolean canPullUp() {
        return false;
    }
}
