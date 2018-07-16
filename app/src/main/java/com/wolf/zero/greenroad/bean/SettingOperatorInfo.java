package com.wolf.zero.greenroad.bean;

/**
 * Created by Administrator on 2017/8/3.
 */

public class SettingOperatorInfo {
    private String username;
    private int isLoginSelected;
    private int isCheckSelected;
    private String job_number;
    private String operator_name;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIsLoginSelected() {
        return isLoginSelected;
    }

    public void setIsLoginSelected(int isLoginSelected) {
        this.isLoginSelected = isLoginSelected;
    }

    public int getIsCheckSelected() {
        return isCheckSelected;
    }

    public void setIsCheckSelected(int isCheckSelected) {
        this.isCheckSelected = isCheckSelected;
    }

    public String getJob_number() {
        return job_number;
    }

    public void setJob_number(String job_number) {
        this.job_number = job_number;
    }

    public String getOperator_name() {
        return operator_name;
    }

    public void setOperator_name(String operator_name) {
        this.operator_name = operator_name;
    }

    @Override
    public String toString() {
        return "SettingOperatorInfo{" +
                "username='" + username + '\'' +
                ", isLoginSelected=" + isLoginSelected +
                ", isCheckSelected=" + isCheckSelected +
                ", job_number='" + job_number + '\'' +
                ", operator_name='" + operator_name + '\'' +
                '}';
    }
}
