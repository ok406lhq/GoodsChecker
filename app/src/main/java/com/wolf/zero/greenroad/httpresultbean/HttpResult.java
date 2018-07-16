package com.wolf.zero.greenroad.httpresultbean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/5.
 */

public class HttpResult{


    private int code;
    private String msg;
    private DataBean data;
//    private data DataBean;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {

        private String line;
        private String station;
        private List<String> lanes;


        public List<String> getLanes() {
            return lanes;
        }

        public void setLanes(List<String> lanes) {
            this.lanes = lanes;
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

        @Override
        public String toString() {
            return "DataBean{" +
                    "line='" + line + '\'' +
                    ", station='" + station + '\'' +
                    ", lanes=" + lanes +
                    '}';
        }
    }
}
