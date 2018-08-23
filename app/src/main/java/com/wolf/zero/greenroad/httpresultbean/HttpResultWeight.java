package com.wolf.zero.greenroad.httpresultbean;

import android.widget.TextView;

import com.wolf.zero.greenroad.bean.GoodsChartBean;

import java.util.List;

/**
 * Created by lam on 2018/8/22.
 */

public class HttpResultWeight {

    /**
     * code : 300
     * msg : 返回成功
     * data : {"weightList":["date":"20180815","weight":"33.5"},{"date":"2018017","weight":"31.4"]}
     */

    private int code;
    private String msg;
    private DataBean data;


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
        return "HttpResultWeight{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {

        private List<ChartBean> weightList;

        public List<ChartBean> getWeight_time() {
            return weightList;
        }

        public void setWeight_time(List<GoodsChartBean> weight_time) {
            this.weightList = weightList;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "weightList='" + weightList + '\'' +
                    '}';
        }

        public static class ChartBean {
            private String weight;
            private String date;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }
        }
    }
}
