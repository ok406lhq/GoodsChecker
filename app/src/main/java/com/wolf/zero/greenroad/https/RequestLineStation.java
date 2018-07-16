package com.wolf.zero.greenroad.https;


import com.wolf.zero.greenroad.httpresultbean.HttpResultLineStation;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * @author sineom
 * @version 1.0
 * @time 2017/7/9 9:30
 * @des ${TODO}
 * @updateAuthor ${Author}
 * @updataTIme 2017/7/9
 * @updataDes ${描述更新内容}
 */

public class RequestLineStation {

    public static RequestLineStation sRequestLineStation;

    public RequestLineStation() {

    }

    public static RequestLineStation getInstance() {
        if (sRequestLineStation == null) {
            sRequestLineStation = new RequestLineStation();
        }
        return sRequestLineStation;
    }

    public void getLineStation(Subscriber<List<HttpResultLineStation.DataBean>> subscriber) {
        Observable<List<HttpResultLineStation.DataBean>> observable = HttpMethods.getInstance().getApi().getLines()
                .map(listHttpResultLineStation -> listHttpResultLineStation.getData())
                ;

        HttpMethods.getInstance().toSubscribe(observable,subscriber);

    }

}
