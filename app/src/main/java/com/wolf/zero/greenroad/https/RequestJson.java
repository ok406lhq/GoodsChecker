package com.wolf.zero.greenroad.https;

import com.wolf.zero.greenroad.httpresultbean.HttpResultCode;

import okhttp3.RequestBody;
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

public class RequestJson {

    public static RequestJson sRequestJson;

    public RequestJson() {

    }

    public static RequestJson getInstance() {
        if (sRequestJson == null) {
            sRequestJson = new RequestJson();
        }
        return sRequestJson;
    }

    public void postJson(Subscriber<HttpResultCode> subscriber, RequestBody requestBody) {
        Observable<HttpResultCode> observable = HttpMethods.getInstance().
                getApi().postJson(requestBody);

        HttpMethods.getInstance().toSubscribe(observable, subscriber);

    }
}
