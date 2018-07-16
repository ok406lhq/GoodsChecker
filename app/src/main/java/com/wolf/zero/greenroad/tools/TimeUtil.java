package com.wolf.zero.greenroad.tools;

import android.content.Context;

import com.orhanobut.logger.Logger;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author sineom
 * @version 1.0
 * @time 2017/7/10 22:54
 * @des ${TODO}
 * @updateAuthor ${Author}
 * @updataTIme 2017/7/10
 * @updataDes ${描述更新内容}
 */

public class TimeUtil {

    private final Context mContext;

    public TimeUtil(Context context) {
        mContext = context;
    }

    /**
     * 得到当前的系统时间
     */
    public static String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Date curDate = new Date(System.currentTimeMillis());
        String shutTime = formatter.format(curDate);
        return shutTime;
    }

    public static String getCurrentTimeToDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String curTime = formatter.format(new Date(System.currentTimeMillis()));
        // String shutTime = formatter.format(curDate);
        return curTime;
    }

    public static int getTimeId() {
        SimpleDateFormat formatter = new SimpleDateFormat("MMddHHmmss");
        int curTime = Integer.parseInt(formatter.format(new Date(System.currentTimeMillis())));
        // String shutTime = formatter.format(curDate);
        Logger.i(curTime + "");
        return curTime;
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     * time1 保存在数据库中的时间
     * time2 当前登陆的时间的时间
     *
     * @return
     */
    public static int differentDaysByMillisecond(String time1, String time2) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date date1 = format.parse(time1);
            Date date2 = format.parse(time2);
            int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));//天数
            // int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600));//小时
            //int days = (int) ((date2.getTime() - date1.getTime()) / (1000*60));//分钟
            Logger.i(days + "-----days");
            return days;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     * time1 保存在数据库中的时间
     * time2 当前登陆的时间的时间
     *
     * @return
     */
    public static int differentDaysByTime(String time1, String time2) {

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

        try {
            Date date1 = format.parse(time1);
            Date date2 = format.parse(time2);
            int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));//天数
            // int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600));//小时
            // int days = (int) ((date2.getTime() - date1.getTime()) / (1000*60));//分钟
            Logger.i(days + "-----days");
            return days;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     * time1 保存在数据库中的时间
     * time2 当前登陆的时间的时间
     *
     * @return
     */
    public static int getLastTime(String time1) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date date1 = format.parse(time1);
            int lastTime = (int) (date1.getTime() - 1000 * 60);
            Logger.i(lastTime + "");

            return lastTime;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 时间前推或后推分钟,其中JJ表示分钟.
     */
    public static String getPreTime(String sj1, String jj) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mydate1 = "";
        try {
            Date date1 = format.parse(sj1);
            long Time = (date1.getTime() / 1000) + Integer.parseInt(jj) * 60;
            date1.setTime(Time * 1000);
            mydate1 = format.format(date1);
        } catch (Exception e) {
        }
        return mydate1;
    }

    /**
     * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
     */
    public static String getNextDay(String nowdate, String delay) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String mdate = "";
            Date d = strToDate(nowdate);
            long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24 * 60 * 60;
            d.setTime(myTime * 1000);
            mdate = format.format(d);
            return mdate;
        } catch (Exception e) {
            return "";
        }
    }


    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }
    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getStringDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
}
