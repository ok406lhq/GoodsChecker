package com.wolf.zero.greenroad.httpresultbean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/6.
 * 轮询的回调
 */

public class HttpResultPoll {

    /**
     * code : 301
     * msg : 账号为空
     */

    private int code;
    private List<Integer> data;

    @Override
    public String toString() {
        return "HttpResultPolling{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
