package com.wolf.zero.greenroad.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by zerowolf on 2017/11/29.
 */

public class NoScrollViewPager extends ViewPager {
    public void setNeedScroll(boolean needScroll) {
        isNeedScroll = needScroll;
    }

    private boolean isNeedScroll=true;

    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(isNeedScroll){
            return super.onTouchEvent(ev);
        }else {
            return false;
        }
    }
}
