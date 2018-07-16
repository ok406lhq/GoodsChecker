package com.wolf.zero.greenroad.litepalbean;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Administrator on 2017/8/24.
 */

public class SupportGoods extends DataSupport {
    private String markTime;
    private List<String> goodsTypeList;
    private String name;
    private String pinyin;
    private String imageUrl;
    private String type;
    private int sortId;


    @Override
    public String toString() {
        return "SupportGoods{" +
                "markTime='" + markTime + '\'' +
                ", goodsTypeList=" + goodsTypeList +
                ", name='" + name + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", type='" + type + '\'' +
                ", sortId=" + sortId +
                '}';
    }


    public String getMarkTime() {
        return markTime;
    }

    public void setMarkTime(String markTime) {
        this.markTime = markTime;
    }

    public List<String> getGoodsTypeList() {
        return goodsTypeList;
    }

    public void setGoodsTypeList(List<String> goodsTypeList) {
        this.goodsTypeList = goodsTypeList;
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
}
