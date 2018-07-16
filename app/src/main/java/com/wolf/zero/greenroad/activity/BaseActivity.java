package com.wolf.zero.greenroad.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.wolf.zero.greenroad.GreenRoadApplication;
import com.wolf.zero.greenroad.R;
import com.wolf.zero.greenroad.interfacy.ThemeChangeObserver;
import com.wolf.zero.greenroad.tools.ActivityCollector;
import com.wolf.zero.greenroad.tools.SPUtils;

/**
 * @author sineom
 * @version 1.0
 * @time 2017/6/25 16:18
 * @des ${TODO}
 * @updateAuthor ${Author}
 * @updataTIme 2017/6/25
 * @updataDes ${描述更新内容}
 */

public  class BaseActivity extends AppCompatActivity implements ThemeChangeObserver {
    private static final String TAG = "GreenRoadApp";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setupActivityBeforeCreate();
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏

        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            // Activity was brought to front and not created,
            // Thus finishing this will get us to the last viewed activity
            finish();
            return;
        }
        ActivityCollector.addActivity(this);

        Logger.init(TAG).logLevel(LogLevel.FULL);
//        Logger.init(TAG).logLevel(LogLevel.NONE);

    }

    /**
     * */
    private void setupActivityBeforeCreate() {
        ((GreenRoadApplication) getApplication()).registerObserver(this);
        loadingCurrentTheme();
    }


    public Context getContext() {
        return BaseActivity.this;
    }

    /**
     * */
    public void switchCurrentThemeTag() {
        setThemeTag(0 - getThemeTag());
        loadingCurrentTheme();
    }


    @Override
    public void loadingCurrentTheme() {
        switch (getThemeTag()) {
            case  1:
                setTheme(R.style.GreenRoadTheme_Day);
                break;
            case -1:
                setTheme(R.style.GreenRoadTheme_Night);
                break;
        }
    }

    @Override
    public void notifyByThemeChanged() {

    }


    /**
     * 得到当前主题标签
     * */
    protected int getThemeTag() {
        return (int) SPUtils.get(this, SPUtils.KEY_THEME_TAG, 1);
    }

    /**
     * 设置主题标签并记录下来
     * */
    protected void setThemeTag(int tag) {
        SPUtils.putAndApply(this, SPUtils.KEY_THEME_TAG, tag);
    }



    /**
     * 得到状态栏的高度
     * */
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getContext().getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    protected void onDestroy() {
        ((GreenRoadApplication) getApplication()).unregisterObserver(this);
        super.onDestroy();
//        Logger.i("销毁BaseActivity");
//        SPUtils.putAndApply(this, SPUtils.CURRENT_SHIFT, "");
        ActivityCollector.removeActivity(this);

    }
}
