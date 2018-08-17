package com.wolf.zero.greenroad.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/14.
 */

public class ScanInfoBean implements Serializable{

    private String scan_05Q;
    private String scan_10Q;
    private String scan_sWeight;
    private String scan_sVolume;

    private int isLimit;

    public String getScan_05Q() {
        return scan_05Q;
    }

    public void setScan_05Q(String scan_05Q) {
        this.scan_05Q = scan_05Q;
    }

    @Override
    public String toString() {
        return "ScanInfoBean{" +
                "scan_05Q='" + scan_05Q + '\'' +
                ", scan_10Q='" + scan_10Q + '\'' +
                ", scan_sWeight='" + scan_sWeight + '\'' +
                ", scan_sVolume='" + scan_sVolume + '\'' +
                ", isLimit=" + isLimit +
                '}';
    }

    public String getScan_10Q() {
        return scan_10Q;
    }

    public void setScan_10Q(String scan_10Q) {
        this.scan_10Q = scan_10Q;
    }



    public int getIsLimit() {
        return isLimit;
    }

    public void setIsLimit(int isLimit) {
        this.isLimit = isLimit;
    }


    public String getScan_sWeight() {
        return scan_sWeight;
    }

    public void setScan_sWeight(String scan_sWeight) {
        this.scan_sWeight = scan_sWeight;
    }

    public String getScan_sVolume() {
        return scan_sVolume;
    }

    public void setScan_sVolume(String scan_sVolume) {
        this.scan_sVolume = scan_sVolume;
    }
}
