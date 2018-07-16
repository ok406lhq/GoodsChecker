package com.wolf.zero.greenroad.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.wolf.zero.greenroad.https.HttpUtilsApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author sineom
 * @version 1.0
 * @time 2016/7/5 14:09
 * @updateAuthor ${Author}
 * @updataTIme 2016/7/5
 * @updataDes ${描述更新内容}
 */
public class NetWorkManager {

   // public static final String BASEURL = "http://192.168.2.122/lvsetondao/index.php/Interfacy/Api/";
    public static final String BASEURL = "http://greenft.githubshop.com/index.php/Interfacy/Api/";

    private static NetWorkManager mNetWorkManager;

    private final HttpUtilsApi mHttpUtilsApi;
    private static final int DEFAUTL_TIMEOUT = 5;

    private NetWorkManager() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAUTL_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAUTL_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAUTL_TIMEOUT, TimeUnit.SECONDS);
        mHttpUtilsApi = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASEURL)
                .build()
                .create(HttpUtilsApi.class);

    }

    public static NetWorkManager getInstane() {

        if (mNetWorkManager == null) {
            synchronized (NetWorkManager.class) {
                mNetWorkManager = new NetWorkManager();
            }
        }
        return mNetWorkManager;
    }



    /**
     * 判断网络是否连接
     *
     * @param context
     * @return
     */
    public static boolean isnetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager maneger = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = maneger.getActiveNetworkInfo();
            if (activeNetworkInfo != null)
                return activeNetworkInfo.isAvailable();
        }
        return false;

    }
/*
    *//**
     * 更新app
     *
     * @param language 根据语言获取更新日志
     * @return
     *//*
    public Observable<UpdateApp> updateApp(String language) {
//        return mHttpUtilsApi.update(language)
//                .compose(RxHolder.<UpdateApp>io_main());
        return mHttpUtilsApi.update(language);
    }


    public Observable<RequestResult> updateLogInfo(LogBean logBean) {
        return mHttpUtilsApi.updateLog(logBean)
                .subscribeOn(Schedulers.newThread());

    }

    *//**
     * 检测机器是否登录过
     *
     * @param bean
     * @return
     *//*
    public Observable<RequestResult> checkLogin(GetActivationCodeBean bean) {
        return mHttpUtilsApi.checkLogin(bean);
//                .compose(RxHolder.io_main());
    }

    //日志拦截
    public class LogInterceptor implements Interceptor {
        public static final String TAG = "NetWorkManager.java";

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            //the request url
            String url = request.url().toString();
            //the request method
            String method = request.method();
            long t1 = System.nanoTime();
            if (BuildConfig.DEBUG)
                Log.d(TAG, String.format(Locale.getDefault(), "Sending %s request [url = %s]", method, url));
            //the request body
            RequestBody requestBody = request.body();
            if (requestBody != null) {
                StringBuilder sb = new StringBuilder("Request Body [");
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);
                Charset charset = Charset.forName("UTF-8");
                MediaType contentType = requestBody.contentType();
                if (contentType != null) {
                    charset = contentType.charset(charset);
                }
                sb.append(buffer.readString(charset));
                sb.append(" (Content-BaseIWBType = ").append(contentType.toString()).append(",")
                        .append(requestBody.contentLength()).append("-byte body)");
                sb.append(" (Content-BaseIWBType = ").append(contentType.toString())
                        .append(",binary ").append(requestBody.contentLength()).append("-byte body omitted)");
                sb.append("]");
                if (BuildConfig.DEBUG)
                    Log.d(TAG, String.format(Locale.getDefault(), "%s %s", method, sb.toString()));
//                LogUtils.f("NET", String.format(Locale.getDefault(), "%s %s", method, sb.toString()), mContext);
            }
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            //the response time
            if (BuildConfig.DEBUG)
                Log.d(TAG, String.format(Locale.getDefault(), "Received response for [url = %s] in %.1fms", url, (t2 - t1) / 1e6d));

            //the response state
            if (BuildConfig.DEBUG)
                Log.d(TAG, String.format(Locale.CHINA, "Received response is %s ,message[%s],code[%d]", response.isSuccessful() ? "success" : "fail", response.message(), response.code()));

            //the response data
            ResponseBody body = response.body();

            BufferedSource source = body.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();
            Charset charset = Charset.defaultCharset();
            MediaType contentType = body.contentType();
            if (contentType != null) {
                charset = contentType.charset(charset);
            }
            String bodyString = buffer.clone().readString(charset);
            if (BuildConfig.DEBUG)
                Log.d(TAG, String.format("Received response json string [%s]", bodyString));

            return response;
        }
    }*/
}
