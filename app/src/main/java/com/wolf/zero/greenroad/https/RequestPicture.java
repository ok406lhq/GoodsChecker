package com.wolf.zero.greenroad.https;


import com.wolf.zero.greenroad.httpresultbean.HttpResultCode;

import java.util.List;

import okhttp3.MultipartBody;
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

public class RequestPicture {

    public static RequestPicture sRequestPicture;

    public RequestPicture() {

    }

    public static RequestPicture getInstance() {
        if (sRequestPicture == null) {
            sRequestPicture = new RequestPicture();
        }
        return sRequestPicture;
    }

    public void postPicture(Subscriber<HttpResultCode> subscriber, String submit_time,
                            List<MultipartBody.Part> sanzheng
//                            List<MultipartBody.Part> cheshen,
//                            List<MultipartBody.Part> huozhao
    ) {
        Observable<HttpResultCode> observable = HttpMethods.getInstance().
//                getApi().postPicture(submit_time,sanzheng,cheshen,huozhao);
                getApi().postPicture(submit_time,sanzheng);

        HttpMethods.getInstance().toSubscribe(observable, subscriber);

    }
}
