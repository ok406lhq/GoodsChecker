package com.wolf.zero.greenroad.https;


import com.wolf.zero.greenroad.httpresultbean.HttpResultBlack;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * @author sineom
 * @version 1.0
 * @time 2017/7/9 9:30
 * @des ${TODO}
 * @updateAuthor ${Author}
 * @updataTIme 2017/7/9
 * @updataDes ${描述更新内容}
 */

public class RequestSubmitBlackList {

    public static RequestSubmitBlackList sRequestBlackList;

    public RequestSubmitBlackList() {

    }

    public static RequestSubmitBlackList getInstance() {
        if (sRequestBlackList == null) {
            sRequestBlackList = new RequestSubmitBlackList();
        }
        return sRequestBlackList;
    }

    public void getBlackList(Subscriber<List<HttpResultBlack.DataBean>> subscriber, String licence_header) {
        Observable<List<HttpResultBlack.DataBean>> observable = HttpMethods.getInstance().getApi().getSubmitBlack(licence_header)
//                .map(new HttpNumberFunc<>());
                .map(new Func1<HttpResultBlack<List<HttpResultBlack.DataBean>>, List<HttpResultBlack.DataBean>>() {
                    @Override
                    public List<HttpResultBlack.DataBean> call(HttpResultBlack<List<HttpResultBlack.DataBean>> listHttpResultBlack) {
                        return listHttpResultBlack.getData();
                    }
                });

        HttpMethods.getInstance().toSubscribe(observable,subscriber);

    }

}
