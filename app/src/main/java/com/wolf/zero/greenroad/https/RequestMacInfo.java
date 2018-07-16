package com.wolf.zero.greenroad.https;


import com.wolf.zero.greenroad.httpresultbean.HttpResultMacInfo;

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

public class RequestMacInfo {

    public static RequestMacInfo sRequestMacInfo;

    public RequestMacInfo() {

    }

    public static RequestMacInfo getInstance() {
        if (sRequestMacInfo == null) {
            sRequestMacInfo = new RequestMacInfo();
        }
        return sRequestMacInfo;
    }

    public void getMacInfo(Subscriber<HttpResultMacInfo> subscriber, String userName) {
        Observable<HttpResultMacInfo> observable = HttpMethods.getInstance().
                getApi().getMacInfo(userName);

        HttpMethods.getInstance().toSubscribe(observable,subscriber);

    }

}
