package com.wolf.zero.greenroad.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class GoodsChartBean{
    private String date;
    private double weight;




    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public GoodsChartBean(String date, double weight) {
        this.date = date;
        this.weight = weight;
    }

    public double getWeight() {

        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }


}
