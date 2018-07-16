package com.wolf.zero.greenroad.httpresultbean;

/**
 * Created by Administrator on 2017/7/5.
 */

public class HttpResultLineStation<T> {

    /**
     * code : 300
     * msg : 返回成功
     * data : [{"pac":"京A"},{"pac":"京B"},{"pac":"京C"},{"pac":"京E"},{"pac":"京F"},{"pac":"京G"},{"pac":"津A"},{"pac":"津B"},{"pac":"津C"},{"pac":"津E"},{"pac":"沪A"},{"pac":"沪B"},{"pac":"沪C"},{"pac":"沪D"},{"pac":"渝A"},{"pac":"渝B"},{"pac":"渝C"},{"pac":"渝F"},{"pac":"渝G"},{"pac":"渝H"},{"pac":"冀A"},{"pac":"冀B"},{"pac":"冀C"},{"pac":"冀D"},{"pac":"冀E"},{"pac":"冀F"},{"pac":"冀G"},{"pac":"冀H"},{"pac":"冀J"},{"pac":"冀R"},{"pac":"藏T"},{"pac":"豫A"},{"pac":"豫B"},{"pac":"豫C"},{"pac":"豫D"},{"pac":"豫E"},{"pac":"豫F"},{"pac":"豫G"},{"pac":"豫H"},{"pac":"豫J"},{"pac":"豫K"},{"pac":"豫L"},{"pac":"豫M"},{"pac":"豫N"},{"pac":"豫P"},{"pac":"豫Q"},{"pac":"豫R"},{"pac":"豫S"},{"pac":"豫U"},{"pac":"云A"},{"pac":"云C"},{"pac":"云D"},{"pac":"云E"},{"pac":"云F"},{"pac":"云G"},{"pac":"云H"},{"pac":"云J"},{"pac":"云L"},{"pac":"云K"},{"pac":"云M"},{"pac":"云N"},{"pac":"云P"},{"pac":"云Q"},{"pac":"云R"},{"pac":"云S"},{"pac":"辽A"},{"pac":"辽B"},{"pac":"辽C"},{"pac":"辽D"},{"pac":"辽E"},{"pac":"辽F"},{"pac":"辽G"},{"pac":"辽H"},{"pac":"辽J"},{"pac":"辽K"},{"pac":"辽L"},{"pac":"辽M"},{"pac":"辽N"},{"pac":"辽P"},{"pac":"辽V"},{"pac":"黑A"},{"pac":"黑B"},{"pac":"黑C"},{"pac":"黑D"},{"pac":"黑E"},{"pac":"黑F"},{"pac":"黑G"},{"pac":"黑H"},{"pac":"黑J"},{"pac":"黑K"},{"pac":"黑L"},{"pac":"黑M"},{"pac":"黑N"},{"pac":"黑P"},{"pac":"湘A"},{"pac":"湘B"},{"pac":"湘C"},{"pac":"湘D"},{"pac":"湘E"},{"pac":"湘F"},{"pac":"湘G"},{"pac":"湘H"},{"pac":"湘J"},{"pac":"湘K"},{"pac":"湘L"},{"pac":"湘M"},{"pac":"湘N"},{"pac":"湘P"},{"pac":"皖A"},{"pac":"皖B"},{"pac":"皖C"},{"pac":"皖D"},{"pac":"皖E"},{"pac":"皖F"},{"pac":"皖G"},{"pac":"皖H"},{"pac":"皖J"},{"pac":"皖K"},{"pac":"皖L"},{"pac":"皖M"},{"pac":"皖P"},{"pac":"皖Q"},{"pac":"皖R"},{"pac":"鲁A"},{"pac":"鲁B"},{"pac":"鲁C"},{"pac":"鲁D"},{"pac":"鲁E"},{"pac":"鲁F"},{"pac":"鲁G"},{"pac":"鲁H"},{"pac":"鲁J"},{"pac":"鲁K"},{"pac":"鲁L"},{"pac":"鲁M"},{"pac":"鲁N"},{"pac":"鲁P"},{"pac":"鲁Q"},{"pac":"鲁R"},{"pac":"鲁U"},{"pac":"鲁V"},{"pac":"鲁W"},{"pac":"新A"},{"pac":"新B"},{"pac":"新C"},{"pac":"新D"},{"pac":"新E"},{"pac":"新F"},{"pac":"新G"},{"pac":"新H"},{"pac":"新J"},{"pac":"新K"},{"pac":"新L"},{"pac":"新M"},{"pac":"新N"},{"pac":"新P"},{"pac":"新Q"},{"pac":"新R"},{"pac":"苏A"},{"pac":"苏B"},{"pac":"苏C"},{"pac":"苏D"},{"pac":"苏E"},{"pac":"苏F"},{"pac":"苏G"},{"pac":"苏H"},{"pac":"苏J"},{"pac":"苏K"},{"pac":"苏L"},{"pac":"苏M"},{"pac":"苏N"},{"pac":"浙A"},{"pac":"浙B"},{"pac":"浙C"},{"pac":"浙D"},{"pac":"浙E"},{"pac":"浙F"},{"pac":"浙G"},{"pac":"浙H"},{"pac":"浙J"},{"pac":"浙K"},{"pac":"浙L"},{"pac":"赣A"},{"pac":"赣B"},{"pac":"赣C"},{"pac":"赣D"},{"pac":"赣E"},{"pac":"赣F"},{"pac":"赣G"},{"pac":"赣H"},{"pac":"赣J"},{"pac":"赣K"},{"pac":"赣L"},{"pac":"鄂A"},{"pac":"鄂B"},{"pac":"鄂C"},{"pac":"鄂D"},{"pac":"鄂E"},{"pac":"鄂F"},{"pac":"鄂G"},{"pac":"鄂H"},{"pac":"鄂J"},{"pac":"鄂K"},{"pac":"鄂L"},{"pac":"鄂M"},{"pac":"鄂N"},{"pac":"鄂P"},{"pac":"鄂Q"},{"pac":"桂A"},{"pac":"桂B"},{"pac":"桂C"},{"pac":"桂D"},{"pac":"桂E"},{"pac":"桂J"},{"pac":"桂K"},{"pac":"桂M"},{"pac":"桂L"},{"pac":"桂N"},{"pac":"桂P"},{"pac":"甘A"},{"pac":"甘B"},{"pac":"甘C"},{"pac":"甘D"},{"pac":"甘E"},{"pac":"甘F"},{"pac":"甘G"},{"pac":"甘H"},{"pac":"甘J"},{"pac":"甘K"},{"pac":"甘L"},{"pac":"甘M"},{"pac":"甘N"},{"pac":"甘P"},{"pac":"晋A"},{"pac":"晋B"},{"pac":"晋C"},{"pac":"晋D"},{"pac":"晋E"},{"pac":"晋F"},{"pac":"晋H"},{"pac":"晋J"},{"pac":"晋K"},{"pac":"晋L"},{"pac":"晋M"},{"pac":"蒙A"},{"pac":"蒙B"},{"pac":"蒙C"},{"pac":"蒙D"},{"pac":"蒙E"},{"pac":"蒙F"},{"pac":"蒙G"},{"pac":"蒙H"},{"pac":"蒙J"},{"pac":"蒙K"},{"pac":"蒙L"},{"pac":"陕A"},{"pac":"陕B"},{"pac":"陕C"},{"pac":"陕D"},{"pac":"陕E"},{"pac":"陕F"},{"pac":"陕G"},{"pac":"陕H"},{"pac":"陕J"},{"pac":"陕K"},{"pac":"陕U"},{"pac":"陕V"},{"pac":"吉A"},{"pac":"吉B"},{"pac":"吉C"},{"pac":"吉D"},{"pac":"吉E"},{"pac":"吉F"},{"pac":"吉G"},{"pac":"吉H"},{"pac":"闽A"},{"pac":"闽V"},{"pac":"闽C"},{"pac":"闽D"},{"pac":"闽E"},{"pac":"闽F"},{"pac":"闽G"},{"pac":"闽H"},{"pac":"闽J"},{"pac":"闽K"},{"pac":"贵A"},{"pac":"贵B"},{"pac":"贵C"},{"pac":"贵D"},{"pac":"贵E"},{"pac":"贵F"},{"pac":"贵G"},{"pac":"贵H"},{"pac":"贵J"},{"pac":"贵K"},{"pac":"粤A"},{"pac":"粤B"},{"pac":"粤C"},{"pac":"粤D"},{"pac":"粤E"},{"pac":"粤F"},{"pac":"粤G"},{"pac":"粤H"},{"pac":"粤J"},{"pac":"粤K"},{"pac":"粤L"},{"pac":"粤M"},{"pac":"粤N"},{"pac":"粤P"},{"pac":"粤Q"},{"pac":"粤R"},{"pac":"粤S"},{"pac":"粤T"},{"pac":"粤U"},{"pac":"粤V"},{"pac":"粤W"},{"pac":"粤X"},{"pac":"粤Y"},{"pac":"粤Z"},{"pac":"青A"},{"pac":"青B"},{"pac":"青C"},{"pac":"青D"},{"pac":"青E"},{"pac":"青F"},{"pac":"青G"},{"pac":"青H"},{"pac":"藏A"},{"pac":"藏B"},{"pac":"藏C"},{"pac":"藏D"},{"pac":"藏E"},{"pac":"藏F"},{"pac":"藏G"},{"pac":"川A"},{"pac":"川B"},{"pac":"川C"},{"pac":"川D"},{"pac":"川E"},{"pac":"川F"},{"pac":"川H"},{"pac":"川J"},{"pac":"川K"},{"pac":"川L"},{"pac":"川M"},{"pac":"川Q"},{"pac":"川R"},{"pac":"川S"},{"pac":"川T"},{"pac":"川U"},{"pac":"川V"},{"pac":"川W"},{"pac":"川X"},{"pac":"川Y"},{"pac":"川Z"},{"pac":"宁A"},{"pac":"宁B"},{"pac":"宁C"},{"pac":"宁D"},{"pac":"琼A"},{"pac":"琼B"},{"pac":"琼B"}]
     */

    private int code;
    private T data;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HttpResultLineStation{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }

    public static class DataBean<T> {

        private String line;
        private String stations;


        public void setStations(String stations) {
            this.stations = stations;
        }

        public String getStations() {
            return stations;
        }

        public void setLine(String line) {
            this.line = line;
        }

        public String getLine() {
            return line;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "line='" + line + '\'' +
                    ", stations='" + stations + '\'' +
                    '}';
        }
    }

}
