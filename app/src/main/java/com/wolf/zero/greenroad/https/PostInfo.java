package com.wolf.zero.greenroad.https;

/**
 * Created by Administrator on 2017/8/14.
 */

public class PostInfo {


    private String siteCheck;
    private String siteLogin;
    private String station;
    private String lane;
    private String carType;
    private String road;
    private int liteId;

    @Override
    public String toString() {
        return "PostInfo{" +
                "siteCheck='" + siteCheck + '\'' +
                ", siteLogin='" + siteLogin + '\'' +
                ", station='" + station + '\'' +
                ", lane='" + lane + '\'' +
                ", carType='" + carType + '\'' +
                ", road='" + road + '\'' +
                ", liteId=" + liteId +
                ", currentShift='" + currentShift + '\'' +
                ", color='" + color + '\'' +
                ", number='" + number + '\'' +
                ", goods='" + goods + '\'' +
                ", isRoom=" + isRoom +
                ", isFree=" + isFree +
                ", conclusion='" + conclusion + '\'' +
                ", description='" + description + '\'' +
                ", current_time='" + current_time + '\'' +
                ", scan_01Q='" + scan_01Q + '\'' +
                ", scan_04Q='" + scan_04Q + '\'' +
                ", scan_05Q='" + scan_05Q + '\'' +
                ", scan_06Q='" + scan_06Q + '\'' +
                ", scan_10Q='" + scan_10Q + '\'' +
                ", scan_12Q='" + scan_12Q + '\'' +
                ", limit=" + limit +
                '}';
    }

    public String getCurrentShift() {
        return currentShift;
    }

    public void setCurrentShift(String currentShift) {
        this.currentShift = currentShift;
    }

    private String currentShift;

    private String color;
    private String number;
    private String goods;
    private int isRoom;
    private int isFree;
    private String conclusion;
    private String description;

    private String current_time;
    private String scan_01Q;
    private String scan_04Q;
    private String scan_05Q;
    private String scan_06Q;
    private String scan_10Q;
    private String scan_12Q;
    private int limit;

    public int getLiteId() {
        return liteId;
    }

    public void setLiteId(int liteId) {
        this.liteId = liteId;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getCurrent_time() {
        return current_time;
    }

    public void setCurrent_time(String current_time) {
        this.current_time = current_time;
    }


    public String getScan_01Q() {
        return scan_01Q;
    }

    public void setScan_01Q(String scan_01Q) {
        this.scan_01Q = scan_01Q;
    }

    public String getScan_04Q() {
        return scan_04Q;
    }

    public void setScan_04Q(String scan_04Q) {
        this.scan_04Q = scan_04Q;
    }

    public String getScan_05Q() {
        return scan_05Q;
    }

    public void setScan_05Q(String scan_05Q) {
        this.scan_05Q = scan_05Q;
    }

    public String getScan_06Q() {
        return scan_06Q;
    }

    public void setScan_06Q(String scan_06Q) {
        this.scan_06Q = scan_06Q;
    }

    public String getScan_10Q() {
        return scan_10Q;
    }

    public void setScan_10Q(String scan_10Q) {
        this.scan_10Q = scan_10Q;
    }

    public String getScan_12Q() {
        return scan_12Q;
    }

    public void setScan_12Q(String scan_12Q) {
        this.scan_12Q = scan_12Q;
    }

    public String getSiteCheck() {
        return siteCheck;
    }

    public void setSiteCheck(String siteCheck) {
        this.siteCheck = siteCheck;
    }

    public String getSiteLogin() {
        return siteLogin;
    }

    public void setSiteLogin(String siteLogin) {
        this.siteLogin = siteLogin;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public int getIsRoom() {
        return isRoom;
    }

    public void setIsRoom(int isRoom) {
        this.isRoom = isRoom;
    }

    public int getIsFree() {
        return isFree;
    }

    public void setIsFree(int isFree) {
        this.isFree = isFree;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
