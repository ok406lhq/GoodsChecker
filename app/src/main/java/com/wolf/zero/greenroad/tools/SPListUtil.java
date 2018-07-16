package com.wolf.zero.greenroad.tools;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sineom
 * @version 1.0
 * @time 2017/8/5 15:11
 * @des ${TODO}
 * @updateAuthor ${Author}
 * @updataTIme 2017/8/5
 * @updataDes ${描述更新内容}
 */

public class SPListUtil {

    /** 数据存储的XML名称 **/
    public final static String APPCONFIGINFO = "appconfiginfo";
  /** 数据存储的XML名称 **/
    public final static String LOGINNFO = "loginnfo";

    /**
     * 存储数据(Int)
     *
     * @param context
     * @param key
     * @param value
     */
    private static void putIntValue(Context context, String key, int value) {
        SharedPreferences.Editor sp = context.getSharedPreferences(APPCONFIGINFO, Context.MODE_PRIVATE)
                .edit();
        sp.putInt(key, value);
        sp.commit();
    }

    /**
     * 存储数据(String)
     *
     * @param context
     * @param key
     * @param value
     */
    private static void putStringValue(Context context, String key, String value) {
        SharedPreferences.Editor sp = context.getSharedPreferences(APPCONFIGINFO, Context.MODE_PRIVATE)
                .edit();
        sp.putString(key, value);
        sp.commit();
    }

    /**
     * 存储List<String>
     *
     * @param context
     * @param key
     *            List<String>对应的key
     * @param strList
     *            对应需要存储的List<String>
     */
    public static void putStrListValue(Context context, String key,
                                       List<String> strList) {
        if (null == strList) {
            return;
        }
        // 保存之前先清理已经存在的数据，保证数据的唯一性
        removeStrList(context, key);
        int size = strList.size();
        putIntValue(context, key + "size", size);
        for (int i = 0; i < size; i++) {
            putStringValue(context, key + i, strList.get(i));
        }
    }

    /**
     * 取出数据（int)
     *
     * @param context
     * @param key
     * @param defValue
     *            默认值
     * @return
     */
    private static int getIntValue(Context context, String key, int defValue) {
        SharedPreferences sp = context.getSharedPreferences(APPCONFIGINFO,
                Context.MODE_PRIVATE);
        int value = sp.getInt(key, defValue);
        return value;
    }

    /**
     * 取出数据（String)
     *
     * @param context
     * @param key
     * @param defValue
     *            默认值
     * @return
     */
    private static String getStringValue(Context context, String key,
                                         String defValue) {
        SharedPreferences sp = context.getSharedPreferences(APPCONFIGINFO,
                Context.MODE_PRIVATE);
        String value = sp.getString(key, defValue);
        return value;
    }

    /**
     * 取出List<String>
     *
     * @param context
     * @param key
     *            List<String> 对应的key
     * @return List<String>
     */
    public static List<String> getStrListValue(Context context, String key) {
        List<String> strList = new ArrayList<String>();
        int size = getIntValue(context, key + "size", 0);
        for (int i = 0; i < size; i++) {
            strList.add(getStringValue(context, key + i, null));
        }
        return strList;
    }

    /**
     * 清空List<String>所有数据
     *
     * @param context
     * @param key
     *            List<String>对应的key
     */
    public static void removeStrList(Context context, String key) {
        int size = getIntValue(context, key + "size", 0);
        if (0 == size) {
            return;
        }
        remove(context, key + "size");
        for (int i = 0; i < size; i++) {
            remove(context, key + i);
        }
    }

    /**
     * @Description TODO 清空List<String>单条数据
     * @param context
     * @param key
     *            List<String>对应的key
     * @param str
     *            List<String>中的元素String
     */
    public static void removeStrListItem(Context context, String key, String str) {
        int size = getIntValue(context, key + "size", 0);
        if (0 == size) {
            return;
        }
        List<String> strList = getStrListValue(context, key);
        // 待删除的List<String>数据暂存
        List<String> removeList = new ArrayList<String>();
        for (int i = 0; i < size; i++) {
            if (str.equals(strList.get(i))) {
                if (i >= 0 && i < size) {
                    removeList.add(strList.get(i));
                    remove(context, key + i);
                    putIntValue(context, key + "size", size - 1);
                }
            }
        }
        strList.removeAll(removeList);
        // 删除元素重新建立索引写入数据
        putStrListValue(context, key, strList);
    }

    /**
     * 清空对应key数据
     *
     * @param context
     * @param key
     */
    public static void remove(Context context, String key) {
        SharedPreferences.Editor sp = context.getSharedPreferences(APPCONFIGINFO, Context.MODE_PRIVATE)
                .edit();
        sp.remove(key);
        sp.commit();
    }

    /**
     * 清空所有数据
     *
     * @param context
     */
    public static void clear(Context context) {
        SharedPreferences.Editor sp = context.getSharedPreferences(APPCONFIGINFO, Context.MODE_PRIVATE)
                .edit();
        sp.clear();
        sp.commit();
    }

}
