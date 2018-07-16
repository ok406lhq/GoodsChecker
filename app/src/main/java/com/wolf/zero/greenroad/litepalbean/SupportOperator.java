package com.wolf.zero.greenroad.litepalbean;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/7/6.
 */

public class SupportOperator extends DataSupport {



    private String username;
    private String job_number;
    private String operator_name;
    private int check_select;
    private int login_select;


    @Override
    public String toString() {
        return "SupportOperator{" +
                "username=" + username +
                ", job_number='" + job_number + '\'' +
                ", operator_name='" + operator_name + '\'' +
                ", check_select=" + check_select +
                ", login_select=" + login_select +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCheck_select() {
        return check_select;
    }

    public void setCheck_select(int check_select) {
        this.check_select = check_select;
    }

    public int getLogin_select() {
        return login_select;
    }

    public void setLogin_select(int login_select) {
        this.login_select = login_select;
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


}
