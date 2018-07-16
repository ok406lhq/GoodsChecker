package com.wolf.zero.greenroad.litepalbean;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Administrator on 2017/8/24.
 */

public class SupportCarTypeAndConfig extends DataSupport {
    private String markTime_config;
    private List<String> carTypeList;
    private List<String> configMustList;

    @Override
    public String toString() {
        return "SupportCarTypeAndConfig{" +
                "markTime_config='" + markTime_config + '\'' +
                ", carTypeList=" + carTypeList +
                ", configMustList=" + configMustList +
                '}';
    }

    public String getMarkTime_config() {
        return markTime_config;
    }

    public void setMarkTime_config(String markTime_config) {
        this.markTime_config = markTime_config;
    }

    public List<String> getCarTypeList() {
        return carTypeList;
    }

    public void setCarTypeList(List<String> carTypeList) {
        this.carTypeList = carTypeList;
    }

    public List<String> getConfigMustList() {
        return configMustList;
    }

    public void setConfigMustList(List<String> configMustList) {
        this.configMustList = configMustList;
    }
}
