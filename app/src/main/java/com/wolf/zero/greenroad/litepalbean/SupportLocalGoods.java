package com.wolf.zero.greenroad.litepalbean;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/8/24.
 */

public class SupportLocalGoods extends DataSupport {
    private String name;
    private String pinyin;
    private String imageUrl;
    private String type;
    private int sortId;
    private double density;

    @Override
    public String toString() {
        return "SupportLocalGoods{" +
                "name='" + name + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", type='" + type + '\'' +
                ", sortId=" + sortId +
                ", density=" + density +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSortId() {
        return sortId;
    }

    public void setSortId(int sortId) {
        this.sortId = sortId;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    public double getDensity() {
        return density;
    }
}
