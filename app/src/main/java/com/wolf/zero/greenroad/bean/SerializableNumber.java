package com.wolf.zero.greenroad.bean;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by shadow on 2016/3/4.
 */
public class SerializableNumber implements Serializable, Comparable {

    /**
     * 是否置顶
     */
    public int top;

    /**
     * 置顶时间
     **/
    public long time;

    /**
     * 头像
     */
    public String avatar;

    public String name;
    /**
     * 显示数据拼音的首字母
     */
    public String sortLetters;
    /**
     * 简拼
     */
    public String simpleSpell;
    /**
     * 全拼
     */
    public String wholeSpell;


    public void setSimpleSpell(String simpleSpell) {
        this.simpleSpell = simpleSpell;
    }

    public String getWholeSpell() {
        return wholeSpell;
    }

    public void setWholeSpell(String wholeSpell) {
        this.wholeSpell = wholeSpell;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public int compareTo(Object another) {
        if (another == null || !(another instanceof SerializableNumber)) {
            return -1;
        }

        SerializableNumber otherSerializableNumber = (SerializableNumber) another;
        /**置顶判断 ArrayAdapter是按照升序从上到下排序的，就是默认的自然排序
         * 如果是相等的情况下返回0，包括都置顶或者都不置顶，返回0的情况下要
         * 再做判断，拿它们置顶时间进行判断
         * 如果是不相等的情况下，otherSession是置顶的，则当前session是非置顶的，应该在otherSession下面，所以返回1
         *  同样，session是置顶的，则当前otherSession是非置顶的，应该在otherSession上面，所以返回-1
         * */
        int result = 0 - (top - otherSerializableNumber.getTop());
        if (result == 0) {
            result = 0 - compareToTime(time, otherSerializableNumber.getTime());
        }
        return result;
    }

    /**
     * 根据时间对比
     * */
    public static int compareToTime(long lhs, long rhs) {
        Calendar cLhs = Calendar.getInstance();
        Calendar cRhs = Calendar.getInstance();
        cLhs.setTimeInMillis(lhs);
        cRhs.setTimeInMillis(rhs);
        return cLhs.compareTo(cRhs);

    }

}
