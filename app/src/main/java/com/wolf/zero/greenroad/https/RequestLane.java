package com.wolf.zero.greenroad.https;


import com.wolf.zero.greenroad.httpresultbean.HttpResultLane;

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

public class RequestLane {

    public static RequestLane sRequestLane;

    public RequestLane() {

    }

    public static RequestLane getInstance() {
        if (sRequestLane == null) {
            sRequestLane = new RequestLane();
        }
        return sRequestLane;
    }

    public void getLanes(Subscriber<List<HttpResultLane.DataBean>> subscriber, String station) {
        Observable<List<HttpResultLane.DataBean>> observable = HttpMethods.getInstance().getApi().getLanes(station)
                .map(listHttpResultLane -> listHttpResultLane.getData());
//                .map(listHttpResultLineStation -> listHttpResultLineStation.getData());

        HttpMethods.getInstance().toSubscribe(observable,subscriber);

    }

}
