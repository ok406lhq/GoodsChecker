package com.wolf.zero.greenroad.tools;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wolf.zero.greenroad.R;

/**
 * Created by Administrator on 2017/6/23.
 */

public class ActionBarTool {


    private static AppCompatActivity mActivity;
    private TextView mTitle_text;
    private static ActionBarTool mActionBarTool;
    private static int mType;
    private View mTitleView;

    public static ActionBarTool getInstance(AppCompatActivity activity, int type) {
        mActivity = activity;
        mType = type;
        if (mActionBarTool == null) {
            mActionBarTool = new ActionBarTool();
        }
        return mActionBarTool;
    }

    public TextView getTitle_text_view() {
        ActionBar actionBar = mActivity.getSupportActionBar();
        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);
        LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (mType == 991) {
            //右侧没有menu
            mTitleView = inflater.inflate(R.layout.action_bar_title_photo_right, null);
        } else if (mType == 990){
            mTitleView = inflater.inflate(R.layout.action_bar_title_photo_center, null);
        }else if (mType == 992){
            mTitleView = inflater.inflate(R.layout.action_bar_title_photo_small, null);
        }


        actionBar.setCustomView(mTitleView, lp);

//       actionBar.setDisplayShowHomeEnabled(false);//去掉导航
        actionBar.setDisplayShowTitleEnabled(false);//去掉标题
//        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowCustomEnabled(true);
        mTitle_text = (TextView) mTitleView.findViewById(R.id.title_text);
        return mTitle_text;
    }
}
