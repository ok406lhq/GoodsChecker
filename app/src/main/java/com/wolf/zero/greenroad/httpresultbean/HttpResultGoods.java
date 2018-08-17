package com.wolf.zero.greenroad.httpresultbean;

import java.util.List;

/**
 * Created by zerowolf on 2018/2/28.
 */

public class HttpResultGoods {

    /**
     * code : 200
     * data_config : {"markTime_config":"2018-3-14 00-00-00","carTypeList":["货车","卡车","轿车","三轮车"],"configList":["车道","称重","免费","车型"]}
     * data_goods : {"markTime":"2018-3-14 00-00-00","goodsTypeList":["蔬菜","水果","水产品","畜禽","杂粮","肉蛋奶","小吃","甜品","奶酪"],"subjects":[{"name":"大白菜","pinyin":"dabaicai","imageUrl":"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQr5C9kBRsAkKKzJOyqh8nHgZfCj8JJSWYrGzJpkcq6brmmGM4Y","type":"蔬菜","sortId":1},{"name":"菜薹","pinyin":"caitai","imageUrl":"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQr5C9kBRsAkKKzJOyqh8nHgZfCj8JJSWYrGzJpkcq6brmmGM4Y","type":"蔬菜","sortId":4},{"name":"奶酪1","pinyin":"nailao1","imageUrl":"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQr5C9kBRsAkKKzJOyqh8nHgZfCj8JJSWYrGzJpkcq6brmmGM4Y","type":"奶酪","sortId":4},{"name":"猪肉","imageUrl":"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQr5C9kBRsAkKKzJOyqh8nHgZfCj8JJSWYrGzJpkcq6brmmGM4Y","pinyin":"zhurou","type":"肉蛋奶","sortId":4},{"name":"蛋糕","imageUrl":"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQr5C9kBRsAkKKzJOyqh8nHgZfCj8JJSWYrGzJpkcq6brmmGM4Y","pinyin":"dangao","type":"甜品","sortId":4},{"name":"饺子","imageUrl":"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQr5C9kBRsAkKKzJOyqh8nHgZfCj8JJSWYrGzJpkcq6brmmGM4Y","pinyin":"jiaozi","type":"小吃","sortId":2},{"name":"烧麦","pinyin":"shaomai","imageUrl":"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQr5C9kBRsAkKKzJOyqh8nHgZfCj8JJSWYrGzJpkcq6brmmGM4Y","type":"小吃","sortId":5},{"name":"大饼","pinyin":"dabing","imageUrl":"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQr5C9kBRsAkKKzJOyqh8nHgZfCj8JJSWYrGzJpkcq6brmmGM4Y","type":"小吃","sortId":5},{"name":"芥蓝","pinyin":"jielan","imageUrl":"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQr5C9kBRsAkKKzJOyqh8nHgZfCj8JJSWYrGzJpkcq6brmmGM4Y","type":"蔬菜","sortId":6},{"name":"奶糖","pinyin":"naitang","imageUrl":"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQr5C9kBRsAkKKzJOyqh8nHgZfCj8JJSWYrGzJpkcq6brmmGM4Y","type":"甜品","sortId":7},{"name":"西蓝花","pinyin":"xilanhua","imageUrl":"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQr5C9kBRsAkKKzJOyqh8nHgZfCj8JJSWYrGzJpkcq6brmmGM4Y","type":"蔬菜","sortId":10},{"name":"小青菜","pinyin":"xiaoqingcai","imageUrl":"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQr5C9kBRsAkKKzJOyqh8nHgZfCj8JJSWYrGzJpkcq6brmmGM4Y","type":"蔬菜","sortId":3},{"name":"香蕉","pinyin":"xiangjiao","imageUrl":"www.baidu.com","type":"水果","sortId":8},{"name":"苹果","pinyin":"pingguo","imageUrl":"www.baidu.com","type":"水果","sortId":5},{"name":"哈密瓜","pinyin":"hamigua","imageUrl":"www.baidu.com","type":"水果","sortId":1},{"name":"黑鱼","pinyin":"heiyu","imageUrl":"www.baidu.com","type":"水产品","sortId":3},{"name":"鲶鱼","pinyin":"nianyu","imageUrl":"www.baidu.com","type":"水产品","sortId":2},{"name":"虾","pinyin":"xia","imageUrl":"www.baidu.com","type":"水产品","sortId":1},{"name":"猪","pinyin":"zhu","imageUrl":"www.baidu.com","type":"畜禽","sortId":1},{"name":"大豆","pinyin":"dadou","imageUrl":"www.baidu.com","type":"杂粮","sortId":1}]}
     */

    private int code;
    private DataConfigBean data_config;
    private DataGoodsBean data_goods;

    @Override
    public String toString() {
        return "HttpResultGoods{" +
                "code=" + code +
                ", data_config=" + data_config +
                ", data_goods=" + data_goods +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataConfigBean getData_config() {
        return data_config;
    }

    public void setData_config(DataConfigBean data_config) {
        this.data_config = data_config;
    }

    public DataGoodsBean getData_goods() {
        return data_goods;
    }

    public void setData_goods(DataGoodsBean data_goods) {
        this.data_goods = data_goods;
    }

    public static class DataConfigBean {
        /**
         * markTime_config : 2018-3-14 00-00-00
         * carTypeList : ["货车","卡车","轿车","三轮车"]
         * configList : ["车道","称重","免费","车型"]
         */

        private String markTime_config;
        private List<String> carTypeList;
        private List<String> configList;

        @Override
        public String toString() {
            return "DataConfigBean{" +
                    "markTime_config='" + markTime_config + '\'' +
                    ", carTypeList=" + carTypeList +
                    ", configList=" + configList +
                    '}';
        }

        public String getMarkTime_config() {
            return markTime_config;
        }

        public void setMarkTime_config(String markTime_config) {
            this.markTime_config = markTime_config;
        }

        public List<String> getCarTypeList() {
            return carTypeList;
        }

        public void setCarTypeList(List<String> carTypeList) {
            this.carTypeList = carTypeList;
        }

        public List<String> getConfigList() {
            return configList;
        }

        public void setConfigList(List<String> configList) {
            this.configList = configList;
        }
    }

    public static class DataGoodsBean {
        /**
         * markTime : 2018-3-14 00-00-00
         * goodsTypeList : ["蔬菜","水果","水产品","畜禽","杂粮","肉蛋奶","小吃","甜品","奶酪"]
         * subjects : [{"name":"大白菜","pinyin":"dabaicai","imageUrl":"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQr5C9kBRsAkKKzJOyqh8nHgZfCj8JJSWYrGzJpkcq6brmmGM4Y","type":"蔬菜","sortId":1},{"name":"菜薹","pinyin":"caitai","imageUrl":"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQr5C9kBRsAkKKzJOyqh8nHgZfCj8JJSWYrGzJpkcq6brmmGM4Y","type":"蔬菜","sortId":4},{"name":"奶酪1","pinyin":"nailao1","imageUrl":"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQr5C9kBRsAkKKzJOyqh8nHgZfCj8JJSWYrGzJpkcq6brmmGM4Y","type":"奶酪","sortId":4},{"name":"猪肉","imageUrl":"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQr5C9kBRsAkKKzJOyqh8nHgZfCj8JJSWYrGzJpkcq6brmmGM4Y","pinyin":"zhurou","type":"肉蛋奶","sortId":4},{"name":"蛋糕","imageUrl":"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQr5C9kBRsAkKKzJOyqh8nHgZfCj8JJSWYrGzJpkcq6brmmGM4Y","pinyin":"dangao","type":"甜品","sortId":4},{"name":"饺子","imageUrl":"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQr5C9kBRsAkKKzJOyqh8nHgZfCj8JJSWYrGzJpkcq6brmmGM4Y","pinyin":"jiaozi","type":"小吃","sortId":2},{"name":"烧麦","pinyin":"shaomai","imageUrl":"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQr5C9kBRsAkKKzJOyqh8nHgZfCj8JJSWYrGzJpkcq6brmmGM4Y","type":"小吃","sortId":5},{"name":"大饼","pinyin":"dabing","imageUrl":"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQr5C9kBRsAkKKzJOyqh8nHgZfCj8JJSWYrGzJpkcq6brmmGM4Y","type":"小吃","sortId":5},{"name":"芥蓝","pinyin":"jielan","imageUrl":"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQr5C9kBRsAkKKzJOyqh8nHgZfCj8JJSWYrGzJpkcq6brmmGM4Y","type":"蔬菜","sortId":6},{"name":"奶糖","pinyin":"naitang","imageUrl":"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQr5C9kBRsAkKKzJOyqh8nHgZfCj8JJSWYrGzJpkcq6brmmGM4Y","type":"甜品","sortId":7},{"name":"西蓝花","pinyin":"xilanhua","imageUrl":"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQr5C9kBRsAkKKzJOyqh8nHgZfCj8JJSWYrGzJpkcq6brmmGM4Y","type":"蔬菜","sortId":10},{"name":"小青菜","pinyin":"xiaoqingcai","imageUrl":"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQr5C9kBRsAkKKzJOyqh8nHgZfCj8JJSWYrGzJpkcq6brmmGM4Y","type":"蔬菜","sortId":3},{"name":"香蕉","pinyin":"xiangjiao","imageUrl":"www.baidu.com","type":"水果","sortId":8},{"name":"苹果","pinyin":"pingguo","imageUrl":"www.baidu.com","type":"水果","sortId":5},{"name":"哈密瓜","pinyin":"hamigua","imageUrl":"www.baidu.com","type":"水果","sortId":1},{"name":"黑鱼","pinyin":"heiyu","imageUrl":"www.baidu.com","type":"水产品","sortId":3},{"name":"鲶鱼","pinyin":"nianyu","imageUrl":"www.baidu.com","type":"水产品","sortId":2},{"name":"虾","pinyin":"xia","imageUrl":"www.baidu.com","type":"水产品","sortId":1},{"name":"猪","pinyin":"zhu","imageUrl":"www.baidu.com","type":"畜禽","sortId":1},{"name":"大豆","pinyin":"dadou","imageUrl":"www.baidu.com","type":"杂粮","sortId":1}]
         */

        private String markTime;
        private List<String> goodsTypeList;
        private List<SubjectsBean> subjects;

        @Override
        public String toString() {
            return "DataGoodsBean{" +
                    "markTime='" + markTime + '\'' +
                    ", goodsTypeList=" + goodsTypeList +
                    ", subjects=" + subjects +
                    '}';
        }

        public String getMarkTime() {
            return markTime;
        }

        public void setMarkTime(String markTime) {
            this.markTime = markTime;
        }

        public List<String> getGoodsTypeList() {
            return goodsTypeList;
        }

        public void setGoodsTypeList(List<String> goodsTypeList) {
            this.goodsTypeList = goodsTypeList;
        }

        public List<SubjectsBean> getSubjects() {
            return subjects;
        }

        public void setSubjects(List<SubjectsBean> subjects) {
            this.subjects = subjects;
        }

        public static class SubjectsBean {
            /**
             * name : 大白菜
             * pinyin : dabaicai
             * imageUrl : https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQr5C9kBRsAkKKzJOyqh8nHgZfCj8JJSWYrGzJpkcq6brmmGM4Y
             * type : 蔬菜
             * sortId : 1
             */

            private String name;
            private String pinyin;
            private String imageUrl;
            private String type;
            private double density;
            private int sortId;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPinyin() {
                return pinyin;
            }

            public void setPinyin(String pinyin) {
                this.pinyin = pinyin;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getSortId() {
                return sortId;
            }

            public void setSortId(int sortId) {
                this.sortId = sortId;
            }

            public double getDensity() {
                return density;
            }

            public void setDensity(double density) {
                this.density = density;
            }
        }
    }
}

