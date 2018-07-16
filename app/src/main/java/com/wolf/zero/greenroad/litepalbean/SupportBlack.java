package com.wolf.zero.greenroad.litepalbean;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/8/24.
 */

public class SupportBlack extends DataSupport {

    private String License;

    @Override
    public String toString() {
        return "SupportBlackList{" +
                "License='" + License + '\'' +
                '}';
    }

    public String getLicense() {
        return License;
    }

    public void setLicense(String license) {
        License = license;
    }
}
