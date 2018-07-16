package com.wolf.zero.greenroad.https;


import com.wolf.zero.greenroad.httpresultbean.HttpResult;

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

public class RequestRegistered {

    public static RequestRegistered sRequestRegistered;

    public RequestRegistered() {

    }

    public static RequestRegistered getInstance() {
        if (sRequestRegistered == null) {
            sRequestRegistered = new RequestRegistered();
        }
        return sRequestRegistered;
    }

    public void postRegistered(Subscriber<HttpResult> subscriber,
                               String road, String station, String code,
                               String name, String psw
                               ) {
        Observable<HttpResult> observable = HttpMethods.getInstance().
                getApi().postRegistered(road,station,code,name,psw);

        HttpMethods.getInstance().toSubscribe(observable, subscriber);

    }
}
