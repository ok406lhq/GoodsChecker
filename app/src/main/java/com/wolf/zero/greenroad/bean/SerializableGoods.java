package com.wolf.zero.greenroad.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/27.
 */

public class SerializableGoods implements Serializable{

    public int SortId;

    public int getSortId() {
        return SortId;
    }

    public void setSortId(int sortId) {
        SortId = sortId;
    }


    public String scientific_name;

    public String alias;


    public String bitmapUrl;
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



    public String getBitmapUrl() {
        return bitmapUrl;
    }

    public void setBitmapUrl(String bitmapUrl) {
        this.bitmapUrl = bitmapUrl;
    }



    public String getScientific_name() {
        return scientific_name;
    }

    public void setScientific_name(String scientific_name) {
        this.scientific_name = scientific_name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

    public String getSimpleSpell() {
        return simpleSpell;
    }

    public void setSimpleSpell(String simpleSpell) {
        this.simpleSpell = simpleSpell;
    }

    public String getWholeSpell() {
        return wholeSpell;
    }

    public void setWholeSpell(String wholeSpell) {
        this.wholeSpell = wholeSpell;
    }

}
