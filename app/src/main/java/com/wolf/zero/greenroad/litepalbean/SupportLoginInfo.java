package com.wolf.zero.greenroad.litepalbean;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/8/24.
 */

public class SupportLoginInfo extends DataSupport {
    private String username;
    private String password;
    private String loginTime;
//    private int isCheck;   //0 未选中   1  选中

    @Override
    public String toString() {
        return "SupportLoginInfo{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", loginTime='" + loginTime + '\'' +
//                ", isCheck=" + isCheck +
                '}';
    }
//
//    public int getIsCheck() {
//        return isCheck;
//    }
//
//    public void setIsCheck(int isCheck) {
//        this.isCheck = isCheck;
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

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }
}
