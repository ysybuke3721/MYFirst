package com.slr.slrapp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;

/**
 * 自定义ScrollView,解决viewpage冲突
 * Created by admin on 2016/8/8.
 */
public class MyScrollView  extends HorizontalScrollView {
    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private int mLastY = 0;
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int y = (int) ev.getY();
        View childView = getChildAt(0);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                if (mLastY-y> 0&&childView != null && childView.getMeasuredHeight() <= getScrollY() + getHeight()) {
                    return false;
                }
                break;
        }
        return super.onTouchEvent(ev);
    }
}
