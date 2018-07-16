package com.wolf.zero.greenroad.httpresultbean;

/**
 * Created by Administrator on 2017/7/6.
 * 轮询的回调
 */

public class HttpResultMacInfo {

    /**
     * code : 301
     * msg : 账号为空
     */

    private int code;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HttpResultMacInfo{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        private String name;
        private String terminal_site;
        private String terminal_road;

        @Override
        public String toString() {
            return "DataBean{" +
                    "name='" + name + '\'' +
                    ", terminal_site='" + terminal_site + '\'' +
                    ", terminal_road='" + terminal_road + '\'' +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTerminal_site() {
            return terminal_site;
        }

        public void setTerminal_site(String terminal_site) {
            this.terminal_site = terminal_site;
        }

        public String getTerminal_road() {
            return terminal_road;
        }

        public void setTerminal_road(String terminal_road) {
            this.terminal_road = terminal_road;
        }
    }
}
