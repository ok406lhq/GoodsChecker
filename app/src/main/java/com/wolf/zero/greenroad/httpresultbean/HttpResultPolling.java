package com.wolf.zero.greenroad.httpresultbean;

import java.util.List;

/**
 * Created by zerowolf on 2018/3/19.
 */

public class HttpResultPolling {

    /**
     * code : 200
     * data : [{"lite_ID":2018215124,"isPass":0,"message":""},{"lite_ID":2018215124,"isPass":1,"message":""},{"lite_ID":2018215124,"isPass":2,"message":"黑名单车牌号"},{"lite_ID":2018215124,"isPass":0,"message":""}]
     */

    private int code;
    private List<DataBean> data;

    @Override
    public String toString() {
        return "HttpResultPolling{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * lite_ID : 2018215124
         * isPass : 0
         * message :
         */

        private int lite_ID;
        private int isPass;
        private String message;


        @Override
        public String toString() {
            return "DataBean{" +
                    "lite_ID=" + lite_ID +
                    ", isPass=" + isPass +
                    ", message='" + message + '\'' +
                    '}';
        }

        public int getLite_ID() {
            return lite_ID;
        }

        public void setLite_ID(int lite_ID) {
            this.lite_ID = lite_ID;
        }

        public int getIsPass() {
            return isPass;
        }

        public void setIsPass(int isPass) {
            this.isPass = isPass;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
