package com.wolf.zero.greenroad.bean;

import java.io.Serializable;

/**
 * Created by shadow on 2016/3/4.
 */
public class SerializablePreview implements Serializable {


    private int isPost;

    private String color;

    private String shutTime;

    private String station;

    private String operator;

    private String car_number;

    private String car_goods;

    private String photoPath1;

    private String photoPath2;

    private String photoPath3;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPhotoPath1() {
        return photoPath1;
    }

    public void setPhotoPath1(String photoPath1) {
        this.photoPath1 = photoPath1;
    }

    public String getPhotoPath2() {
        return photoPath2;
    }

    public void setPhotoPath2(String photoPath2) {
        this.photoPath2 = photoPath2;
    }

    public String getPhotoPath3() {
        return photoPath3;
    }

    public void setPhotoPath3(String photoPath3) {
        this.photoPath3 = photoPath3;
    }

    public int getIsPost() {
        return isPost;
    }

    public void setIsPost(int isPost) {
        this.isPost = isPost;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getShutTime() {
        return shutTime;
    }

    public void setShutTime(String shutTime) {
        this.shutTime = shutTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getCar_number() {
        return car_number;
    }

    public void setCar_number(String car_number) {
        this.car_number = car_number;
    }

    public String getCar_goods() {
        return car_goods;
    }

    public void setCar_goods(String car_goods) {
        this.car_goods = car_goods;
    }


    @Override
    public String toString() {
        return "SerializablePreview{" +
                "isPost=" + isPost +
                ", color='" + color + '\'' +
                ", shutTime='" + shutTime + '\'' +
                ", station='" + station + '\'' +
                ", operator='" + operator + '\'' +
                ", car_number='" + car_number + '\'' +
                ", car_goods='" + car_goods + '\'' +
                ", photoPath1='" + photoPath1 + '\'' +
                ", photoPath2='" + photoPath2 + '\'' +
                ", photoPath3='" + photoPath3 + '\'' +
                '}';
    }
}
