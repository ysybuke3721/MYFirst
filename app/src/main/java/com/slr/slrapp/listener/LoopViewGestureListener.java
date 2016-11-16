package com.slr.slrapp.listener;

import android.view.MotionEvent;

import com.slr.slrapp.widget.WheelView;

public class LoopViewGestureListener extends android.view.GestureDetector.SimpleOnGestureListener {

    final WheelView loopView;

    public LoopViewGestureListener(WheelView loopview) {
        loopView = loopview;
    }

    @Override
    public final boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        loopView.scrollBy(velocityY);
        return true;
    }
}
