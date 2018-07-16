package com.wolf.zero.greenroad.litepalbean;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Administrator on 2017/8/24.
 */

public class SupportDetail extends DataSupport implements Parcelable{

    private int lite_ID;


    private String station;
    private String lane;
    private String road;
//    private String color;
    private String number;
    private String goods;
    private String detail_weight;
    private String detail_free;
    private String detail_export;
    private String detail_carType;
    private List<String> picturePath;
    private List<String> pictureTitle;

    public SupportDetail() {
    }

    protected SupportDetail(Parcel in) {
        lite_ID = in.readInt();
        station = in.readString();
        lane = in.readString();
        road = in.readString();
        number = in.readString();
        goods = in.readString();
        detail_weight = in.readString();
        detail_free = in.readString();
        detail_export = in.readString();
        detail_carType = in.readString();
        picturePath = in.createStringArrayList();
        pictureTitle = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(lite_ID);
        dest.writeString(station);
        dest.writeString(lane);
        dest.writeString(road);
        dest.writeString(number);
        dest.writeString(goods);
        dest.writeString(detail_weight);
        dest.writeString(detail_free);
        dest.writeString(detail_export);
        dest.writeString(detail_carType);
        dest.writeStringList(picturePath);
        dest.writeStringList(pictureTitle);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SupportDetail> CREATOR = new Creator<SupportDetail>() {
        @Override
        public SupportDetail createFromParcel(Parcel in) {
            return new SupportDetail(in);
        }

        @Override
        public SupportDetail[] newArray(int size) {
            return new SupportDetail[size];
        }
    };

    @Override
    public String toString() {
        return "SupportDetail{" +
                "lite_ID=" + lite_ID +
                ", station='" + station + '\'' +
                ", lane='" + lane + '\'' +
                ", road='" + road + '\'' +
                ", number='" + number + '\'' +
                ", goods='" + goods + '\'' +
                ", detail_weight='" + detail_weight + '\'' +
                ", detail_free='" + detail_free + '\'' +
                ", detail_export='" + detail_export + '\'' +
                ", detail_carType='" + detail_carType + '\'' +
                ", picturePath=" + picturePath +
                ", pictureTitle=" + pictureTitle +
                '}';
    }

    public String getDetail_carType() {
        return detail_carType;
    }

    public void setDetail_carType(String detail_carType) {
        this.detail_carType = detail_carType;
    }

    public int getLite_ID() {
        return lite_ID;
    }

    public void setLite_ID(int lite_ID) {
        this.lite_ID = lite_ID;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
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

    public String getDetail_weight() {
        return detail_weight;
    }

    public void setDetail_weight(String detail_weight) {
        this.detail_weight = detail_weight;
    }

    public String getDetail_free() {
        return detail_free;
    }

    public void setDetail_free(String detail_free) {
        this.detail_free = detail_free;
    }

    public String getDetail_export() {
        return detail_export;
    }

    public void setDetail_export(String detail_export) {
        this.detail_export = detail_export;
    }

    public List<String> getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(List<String> picturePath) {
        this.picturePath = picturePath;
    }

    public List<String> getPictureTitle() {
        return pictureTitle;
    }

    public void setPictureTitle(List<String> pictureTitle) {
        this.pictureTitle = pictureTitle;
    }
}
