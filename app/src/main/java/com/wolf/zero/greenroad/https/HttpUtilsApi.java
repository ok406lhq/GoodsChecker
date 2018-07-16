package com.wolf.zero.greenroad.https;


import com.wolf.zero.greenroad.Moviebean;
import com.wolf.zero.greenroad.bean.UpdateAppInfo;
import com.wolf.zero.greenroad.httpresultbean.HttpResult;
import com.wolf.zero.greenroad.httpresultbean.HttpResultBlack;
import com.wolf.zero.greenroad.httpresultbean.HttpResultCode;
import com.wolf.zero.greenroad.httpresultbean.HttpResultGoods;
import com.wolf.zero.greenroad.httpresultbean.HttpResultLane;
import com.wolf.zero.greenroad.httpresultbean.HttpResultLineStation;
import com.wolf.zero.greenroad.httpresultbean.HttpResultLoginName;
import com.wolf.zero.greenroad.httpresultbean.HttpResultMacInfo;
import com.wolf.zero.greenroad.httpresultbean.HttpResultPolling;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author sineom
 * @version 1.0
 * @time 2016/7/4 17:09
 * @updateAuthor ${Author}
 * @updataTIme 2016/7/4
 * @updataDes ${描述更新内容}
 */
public interface HttpUtilsApi {

    @FormUrlEncoded
    @POST("Login/listapi")
    Call<HttpResultLoginName> login(@Field("name") String name, @Field("password") String password);

   @GET("Apiversion/update")
   Observable<UpdateAppInfo> update(
           @Query("appversion") String appversion);
//            @Query("appname") String appname,


//    @FormUrlEncoded
//    @POST("Api/polling")
//    Observable<HttpResultPolling> polling(@Field("polling") int[] pollingList);
 @FormUrlEncoded
    @POST("Api/polling")
    Observable<HttpResultPolling> polling(@Field("polling") String pollingList);


    @Multipart
    @POST("Api/picture")
    Observable<HttpResultCode> postPicture(
            @Part("post_time") String postTime,
            @Part List<MultipartBody.Part> sanzheng
    );

    @POST("Api/json")
    Observable<HttpResultCode> postJson(@Body RequestBody info);

    @FormUrlEncoded
    @POST("Login/register")
    Observable<HttpResult> postRegistered(
            @Field("register_road") String road,
            @Field("register_station") String station,
            @Field("register_code") String code,
            @Field("register_name") String name,
            @Field("register_psw") String psw
    );

    @FormUrlEncoded
    @POST("Login/login")
    Observable<HttpResult> postLogin(
            @Field("login_name") String name,
            @Field("login_psw") String psw
    );


    @GET("Api/black")
    Observable<HttpResultBlack<List<HttpResultBlack.DataBean>>> getBlack();

    @GET("Api/submit_black")
    Observable<HttpResultBlack<List<HttpResultBlack.DataBean>>> getSubmitBlack(@Query("licence_header") String appname);

    @GET("Api/linestations")
    Observable<HttpResultLineStation<List<HttpResultLineStation.DataBean>>> getLines();

    @FormUrlEncoded
    @POST("Api/lane")
    Observable<HttpResultLane<List<HttpResultLane.DataBean>>> getLanes(@Field("station") String station);

    @FormUrlEncoded
    @POST("Api/macInfo")
    Observable<HttpResultMacInfo> getMacInfo(@Field("macId") String macId);

    //http://localhost:3000/data/read?type=goods
    @GET("read")
    Observable<HttpResultGoods> getGoods(@Query("type") String type);

    @GET("top250")
    Observable<Moviebean> getMovies(@Query("start") int start, @Query("count") int count);

    //    start=0&count=10
    @FormUrlEncoded
    @POST("Api/goods")
    Observable<HttpResultGoods> postGoods(@Field("markTime") String markTime, @Field("markTime_config") String markTime_config);
//    start=0&count=10


//   @FormUrlEncoded
   @GET("FirstServlet")
   Observable<HttpResultPolling> doPost();

   @GET("Api/signal")
    Observable<HttpResultCode> postSignal();
//   Observable<HttpResultPolling> doPost(@Field("id") String id);
//
}