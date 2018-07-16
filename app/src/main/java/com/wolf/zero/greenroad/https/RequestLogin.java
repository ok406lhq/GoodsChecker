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

public class RequestLogin {

    public static RequestLogin sRequestLogin;

    public RequestLogin() {

    }

    public static RequestLogin getInstance() {
        if (sRequestLogin == null) {
            sRequestLogin = new RequestLogin();
        }
        return sRequestLogin;
    }

    public void postLogin(Subscriber<HttpResult> subscriber,
                          String name, String psw
                               ) {
        Observable<HttpResult> observable = HttpMethods.getInstance().
                getApi().postLogin(name,psw);

        HttpMethods.getInstance().toSubscribe(observable, subscriber);

    }
}
