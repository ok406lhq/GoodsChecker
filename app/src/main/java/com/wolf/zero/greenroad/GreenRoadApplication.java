package com.wolf.zero.greenroad;

import android.app.Application;
import android.support.multidex.MultiDex;

import com.wolf.zero.greenroad.interfacy.ThemeChangeObserver;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;


/**
 * @author sineom
 * @version 1.0
 * @time 2017/6/25 12:28
 * @des ${TODO}
 * @updateAuthor ${Author}
 * @updataTIme 2017/6/25
 * @updataDes ${描述更新内容}
 */

public class GreenRoadApplication extends Application {
    public static GreenRoadApplication sApplication;

    private List<ThemeChangeObserver> mThemeChangeObserverStack; //  主题切换监听栈


    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;

        LitePal.initialize(this);
//        ZXingLibrary.initDisplayOpinion(this);
          //  startLongConn();
        // 登录后开启长连接
      /*  if (UserConfig.isPassLogined()) {
            Logger.i("用户已登录，开启长连接...");
            startLongConn();
        }*/
        //startLongConn();
        MultiDex.install(this);

    }
/*

    */
/**
     * 开始执行启动长连接服务
     */

    private void startLongConn() {

        //quitLongConn();

//        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        Intent intent = new Intent(this, LoopService.class);
//        intent.setAction(LoopService.ACTION);
//        PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        long triggerAtTime = SystemClock.elapsedRealtime();
//        manager.setRepeating(AlarmManager.RTC_WAKEUP, triggerAtTime, 60 * 1000, pendingIntent);

    }

    /**
     * 向堆栈中所有对象发送更新UI的指令
     */
    public void notifyByThemeChanged() {
        List<ThemeChangeObserver> observers = obtainThemeChangeObserverStack();
        for (ThemeChangeObserver observer : observers) {
            observer.loadingCurrentTheme(); //
            observer.notifyByThemeChanged(); //
        }
    }

    /**
     * 获得observer堆栈
     */
    private List<ThemeChangeObserver> obtainThemeChangeObserverStack() {
        if (mThemeChangeObserverStack == null) mThemeChangeObserverStack = new ArrayList<>();
        return mThemeChangeObserverStack;
    }

    /**
     * 向堆栈中添加observer
     */
    public void registerObserver(ThemeChangeObserver observer) {
        if (observer == null || obtainThemeChangeObserverStack().contains(observer)) return;
        obtainThemeChangeObserverStack().add(observer);
    }

    /**
     * 从堆栈中移除observer
     */
    public void unregisterObserver(ThemeChangeObserver observer) {
        if (observer == null || !(obtainThemeChangeObserverStack().contains(observer))) return;
        obtainThemeChangeObserverStack().remove(observer);
    }


}
