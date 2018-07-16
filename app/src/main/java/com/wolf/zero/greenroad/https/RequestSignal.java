package com.wolf.zero.greenroad.https;


import com.wolf.zero.greenroad.httpresultbean.HttpResultCode;

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

public class RequestSignal {

    public static RequestSignal sRequestSignal;

    public RequestSignal() {

    }

    public static RequestSignal getInstance() {
        if (sRequestSignal == null) {
            sRequestSignal = new RequestSignal();
        }
        return sRequestSignal;
    }

    public void postSignal(Subscriber<HttpResultCode> subscriber) {
        Observable<HttpResultCode> observable = HttpMethods.getInstance().
                getApi().postSignal();

        HttpMethods.getInstance().toSubscribe(observable, subscriber);

    }
}
