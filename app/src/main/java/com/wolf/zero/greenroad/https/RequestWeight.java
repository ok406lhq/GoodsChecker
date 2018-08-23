package com.wolf.zero.greenroad.https;


import com.wolf.zero.greenroad.httpresultbean.HttpResultBlack;
import com.wolf.zero.greenroad.httpresultbean.HttpResultWeight;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * @author lam
 * @version 1.0
 * @time 2018/8/22 14:38
 * @des ${TODO}
 * @updateAuthor lam
 * @updataTIme 2018/8/22
 * @updataDes RequestWeight
 */

public class RequestWeight {

    public static RequestWeight sRequestWeight;

    public RequestWeight() {

    }

    public static RequestWeight getInstance() {
        if (sRequestWeight == null) {
            sRequestWeight = new RequestWeight();
        }
        return sRequestWeight;
    }

    public void getWeightTime(Subscriber<HttpResultWeight> subscriber, String plate_number) {
        Observable<HttpResultWeight> observable = HttpMethods.getInstance().getApi().getWeightTime(plate_number);

        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

}
