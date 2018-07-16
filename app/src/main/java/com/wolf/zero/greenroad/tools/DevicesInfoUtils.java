package com.wolf.zero.greenroad.tools;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

/**
 * @author sineom
 * @version 1.0
 * @time 2016/7/4 17:17
 * @updateAuthor ${Author}
 * @updataTIme 2016/7/4
 * @updataDes ${描述更新内容}
 */
public class DevicesInfoUtils {

    private static DevicesInfoUtils sDevicesInfoUtils;

    public static DevicesInfoUtils getInstance() {
        if (sDevicesInfoUtils == null)
            sDevicesInfoUtils = new DevicesInfoUtils();
        return sDevicesInfoUtils;
    }

    private DevicesInfoUtils() {

    }


    public String getVersion(Context context) {
        PackageManager packmanager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packmanager.getPackageInfo(context.getPackageName(), 0);
            String version = packageInfo.versionName;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getVersionCode(Context context) {
        PackageManager packmanager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packmanager.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
            int version = packageInfo.versionCode;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * 判断sdk版本是否在5.0以上
     *
     * @return true表示是的 反之低于或等于5.0
     */
    public static boolean SDKVersionMUp() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

}
