package com.wolf.zero.greenroad.httpresultbean;

/**
 * Created by Administrator on 2017/7/4.
 */

public class HttpResultLoginName {

    /**
     * code : 201
     * msg : 账号错误
     */

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
