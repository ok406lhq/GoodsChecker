package com.wolf.zero.greenroad.litepalbean;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Administrator on 2017/7/6.
 */

public class TeamItem extends DataSupport {



//    private int teamID;
    private String username;
    private String password;
    private String line;
    private String station;
    private String defaultLane;
    private List<String> lanes;

    @Override
    public String toString() {
        return "TeamItem{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", line='" + line + '\'' +
                ", station='" + station + '\'' +
                ", defaultLane='" + defaultLane + '\'' +
                ", lanes=" + lanes +
                '}';
    }

    public String getDefaultLane() {
        return defaultLane;
    }

    public void setDefaultLane(String defaultLane) {
        this.defaultLane = defaultLane;
    }

    public List<String> getLanes() {
        return lanes;
    }

    public void setLanes(List<String> lanes) {
        this.lanes = lanes;
    }

    //    public int getTeamID() {
//        return teamID;
//    }
//
//    public void setTeamID(int teamID) {
//        this.teamID = teamID;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

}
