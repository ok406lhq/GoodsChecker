package com.wolf.zero.greenroad.https;


import com.wolf.zero.greenroad.httpresultbean.HttpResultBlack;

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

public class RequestBlackList {

    public static RequestBlackList sRequestBlackList;

    public RequestBlackList() {

    }

    public static RequestBlackList getInstance() {
        if (sRequestBlackList == null) {
            sRequestBlackList = new RequestBlackList();
        }
        return sRequestBlackList;
    }

    public void getBlackList(Subscriber<List<HttpResultBlack.DataBean>> subscriber) {
        Observable<List<HttpResultBlack.DataBean>> observable = HttpMethods.getInstance().getApi().getBlack()
//                .map(new HttpNumberFunc<>());
                .map(listHttpResultBlack -> listHttpResultBlack.getData());

        HttpMethods.getInstance().toSubscribe(observable,subscriber);

    }

}
