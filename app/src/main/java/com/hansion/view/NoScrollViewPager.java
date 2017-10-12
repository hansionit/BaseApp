package com.hansion.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Description：可以禁止滑动的ViewPager
 *  使用方法：调用setScanScroll方法传入相应布尔值
 * Author: Hansion
 * Time: 2016/11/21 11:58
 */
public class NoScrollViewPager extends ViewPager {
    private boolean canScroll = true;


    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public NoScrollViewPager(Context context) {
        super(context);
    }


    public void setScanScroll(boolean canScroll) {
        this.canScroll = canScroll;
    }


    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }


    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        if (!canScroll)
            return false;
        else
            return super.onTouchEvent(arg0);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (!canScroll)
            return false;
        else
            return super.onInterceptTouchEvent(arg0);
    }


    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }


    @Override
    public void setCurrentItem(int item) {
        //表示切换的时候，不需要切换时间。
        super.setCurrentItem(item, false);
    }

}
