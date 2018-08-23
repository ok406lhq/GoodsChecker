package com.wolf.zero.greenroad.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/14.
 */

public class DetailInfoBean {
    private String number;
    private String goods;
    private String text_weight;
    private String text_free;
    private String text_export;
    private String text_carType;
    private List<PathTitleBean> mPath_and_title;

    @Override
    public String toString() {
        return "DetailInfoBean{" +
                "number='" + number + '\'' +
                ", goods='" + goods + '\'' +
                ", text_weight='" + text_weight + '\'' +
                ", text_free='" + text_free + '\'' +
                ", text_export='" + text_export + '\'' +
                ", text_carType='" + text_carType + '\'' +
                ", mPath_and_title=" + mPath_and_title +
                '}';
    }

    public DetailInfoBean() {
    }

    public String getText_carType() {
        return text_carType;
    }

    public void setText_carType(String text_carType) {
        this.text_carType = text_carType;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public String getText_weight() {
        return text_weight;
    }

    public void setText_weight(String text_weight) {
        this.text_weight = text_weight;
    }

    public String getText_free() {
        return text_free;
    }

    public void setText_free(String text_free) {
        this.text_free = text_free;
    }

    public String getText_export() {
        return text_export;
    }

    public void setText_export(String text_export) {
        this.text_export = text_export;
    }

    public List<PathTitleBean> getPath_and_title() {
        return mPath_and_title;
    }

    public void setPath_and_title(List<PathTitleBean> path_and_title) {
        mPath_and_title = path_and_title;
    }
}
