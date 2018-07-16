package com.wolf.zero.greenroad.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by zerowolf on 2017/11/27.
 */

public class MylinearLayout extends LinearLayout {

    private InterceptText click;

    public MylinearLayout(Context context) {
        super(context);
    }

    public MylinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MylinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MylinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    //
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if (null != click) click.onClick();
        }
        return true;
    }

    public void setOnClick(InterceptText hc) {
        click = hc;

    }

    public interface InterceptText {
        void onClick();
    }
}

