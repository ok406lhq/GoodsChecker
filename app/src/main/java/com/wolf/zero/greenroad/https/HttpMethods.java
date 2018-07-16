package com.wolf.zero.greenroad.https;

import com.orhanobut.logger.Logger;
import com.wolf.zero.greenroad.GreenRoadApplication;
import com.wolf.zero.greenroad.tools.SPUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by liukun on 16/3/9.
 */
public class HttpMethods {

//    public static final String BASE_URL = "http://192.168.2.122/lvsetondao/index.php/Interfacy/";
//   public static final String BASE_URL = "http://greenft.githubshop.com/index.php/Interfacy/";
//   public static final String BASE_URL = "http://greenft.githubshop."+ SPUtils.get(GreenRoadApplication.sApplication,SPUtils.CONFIG_PORT,"com")+"/index.php/Interfacy/";
//   public static final String BASE_URL = "http://192.168.98.99:"+ SPUtils.get(GreenRoadApplication.sApplication,SPUtils.CONFIG_PORT,"88")+"/index.php/Interfacy/";
//   public static final String BASE_URL = "http://"+SPUtils.get(GreenRoadApplication.sApplication,SPUtils.CONFIG_PORT,"88")+"/index.php/Interfacy/";
//
//

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;
    private static HttpMethods sMethods;
    private static String BASE_url;

    //构造方法私有
    private HttpMethods() {

    }

    //获取单例
    public static HttpMethods getInstance(){
        BASE_url = "http://"+ SPUtils.get(GreenRoadApplication.sApplication,SPUtils.CONFIG_PORT,"88")+"/Interfacy/";
//        BASE_url = "http://"+ SPUtils.get(GreenRoadApplication.sApplication,SPUtils.CONFIG_PORT,"88");


//        BASE_url = "http://greenft.githubshop.com/index.php/Interfacy/";
//        BASE_url = "http://192.168.15.200:88/Interfacy/";
        if (sMethods == null) {
            sMethods = new HttpMethods();
        }
//        Logger.i(BASE_url);
        return sMethods;
    }

    public HttpUtilsApi getApi() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
       builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

      //  String url_header= (String) SPUtils.get(GreenRoadApplication.sApplication, SPUtils.LINE_CONFIG, "http://greenft.githubshop.com");

   //     final String BASE_URL = url_header+"/index.php/Interfacy/";*//*

     //   builder.addInterceptor(new GzipRequestInterceptor());
    //    builder.build();

      /*   OkHttpClient client = new OkHttpClient
                .Builder()
                //拓展功能：网络请求的log，compile 'com.squareup.okhttp3:logging-interceptor:3.2.0'
               //.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                //拓展功能：数据请求的压缩，下面会解析自定义：
                .addInterceptor(new GzipRequestInterceptor())
                .build();
*/

        retrofit = new Retrofit.Builder()
               // .client(genericClient())
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_url)
                .build();

        HttpUtilsApi httpUtilsApi = retrofit.create(HttpUtilsApi.class);
        return httpUtilsApi;
    }

    public <T> void toSubscribe(Observable<T> o, Subscriber<T> s){
         o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    private class GzipRequestInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            if (originalRequest.body() == null || originalRequest.header("Content-Encoding") != null) {
                return chain.proceed(originalRequest);
            }
            Request compressedRequest = originalRequest.newBuilder()
                    .header("Content-Encoding", "gzip")
                    .method(originalRequest.method(), gzip(originalRequest.body()))
                    .build();
            return chain.proceed(compressedRequest);
        }

        private RequestBody gzip(final RequestBody body) {
            return new RequestBody() {
                @Override
                public MediaType contentType() {
                    Logger.i("gzip!");
                    return body.contentType();
                }

                @Override
                public long contentLength() throws IOException {
                    return -1;
                }

                @Override
                public void writeTo(BufferedSink sink) throws IOException {
                    BufferedSink gzipSink = Okio.buffer(new GzipSink(sink));
                    body.writeTo(gzipSink);
                    gzipSink.close();
                }
            };
        }
    }
    public  OkHttpClient genericClient() {

                OkHttpClient httpClient = new OkHttpClient.Builder()

                         .addInterceptor(new Interceptor() {

                     @Override

                     public Response intercept(Chain chain) throws IOException {

                                        Request request = chain.request()

                                                .newBuilder()

                                                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")

                                                .addHeader("Accept-Encoding", "gzip, deflate")

                                                .addHeader("Connection", "keep-alive")

                                                 .addHeader("Accept", "*/*")

                                                .addHeader("Cookie", "add cookies here")

                                                .build();

                                        return chain.proceed(request);

                                    }



                         })

                         .build();



                 return httpClient;

             }

    private String zipInputStream(InputStream is) throws IOException {
        GZIPInputStream gzip = new GZIPInputStream(is);
        BufferedReader in = new BufferedReader(new InputStreamReader(gzip, "UTF-8"));
        StringBuffer buffer = new StringBuffer();
        String line;
        while ((line = in.readLine()) != null)
            buffer.append(line + "\n");
        is.close();
        return buffer.toString();
    }
}