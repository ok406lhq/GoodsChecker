package com.wolf.zero.greenroad.tools;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/7.
 */

public class ActivityCollector {
    public static List<AppCompatActivity> sActivities = new ArrayList<>();

    public static void addActivity(AppCompatActivity activity) {
        sActivities.add(activity);
    }

    public static void removeActivity(AppCompatActivity activity) {
        sActivities.remove(activity);
    }

    public static void finishAll() {
        for (AppCompatActivity activity : sActivities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        sActivities.clear();
    }

    public static void finishActivity(AppCompatActivity activity) {
        if (!activity.isFinishing()) {
            activity.finish();
        }
    }

}
