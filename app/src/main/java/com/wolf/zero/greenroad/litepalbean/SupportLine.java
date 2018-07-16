package com.wolf.zero.greenroad.litepalbean;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Administrator on 2017/8/24.
 */

public class SupportLine extends DataSupport {

    private String line;
    private List<String> stations;

    @Override
    public String toString() {
        return "SupportLine{" +
                "line='" + line + '\'' +
                ", stations=" + stations +
                '}';
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public List<String> getStations() {
        return stations;
    }

    public void setStations(List<String> stations) {
        this.stations = stations;
    }
}
