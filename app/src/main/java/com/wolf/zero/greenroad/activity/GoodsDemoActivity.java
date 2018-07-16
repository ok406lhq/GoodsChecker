package com.wolf.zero.greenroad.activity;/*
package com.android.htc.greenroad.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import R;
import GoodsTypeAdapter;
import HttpResultGoods;
import HttpUtilsApi;
import SupportGoods;
import com.orhanobut.logger.Logger;

import org.litepal.crud.DataSupport;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class GoodsDemoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_demo);

        initGoodData();
        initGoodView();

    }

    private void initGoodView() {

        SupportGoods supportGoods = DataSupport.findFirst(SupportGoods.class);
        List<String> typeList = supportGoods.getGoodsTypeList();
        typeList.add(0,"货种");
        RecyclerView recycler_goods = (RecyclerView) findViewById(R.id.recycler_goods);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 4, LinearLayoutManager.VERTICAL, false);
        recycler_goods.setLayoutManager(manager);
        GoodsTypeAdapter adapter = new GoodsTypeAdapter(this,typeList);
        recycler_goods.setAdapter(adapter);

    }

    private void initGoodData() {
//        String BASE_url = "http://10.0.2.2:3000/data/";
        String BASE_url = "http://192.168.43.108:3000/data/";
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(5, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_url)
                .build();

        HttpUtilsApi httpUtilsApi = retrofit.create(HttpUtilsApi.class);

        Observable<HttpResultGoods> goods = httpUtilsApi.getGoods("goods");


        Subscriber<HttpResultGoods> subscriber = new Subscriber<HttpResultGoods>() {
            @Override
            public void onCompleted() {
                Logger.i("完成");
            }

            @Override
            public void onError(Throwable e) {

                Logger.i("错误" + e.getMessage());
            }

            @Override
            public void onNext(HttpResultGoods goodsBean) {
                Logger.i(goodsBean.getCode() + "");
                List<HttpResultGoods.DataBean.SubjectsBean> subjectsBeanList = goodsBean.getData().getSubjects();
                String markTime = goodsBean.getData().getMarkTime();

                Logger.i(subjectsBeanList.toString());
//                ArrayList<String> goodsTypeList = new ArrayList<>();
//                ArrayList<String> carTypeList = new ArrayList<>();
                List<String> goodTypeNames = goodsBean.getData().getGoodsTypeList();
                List<String> carTypeNames = goodsBean.getData().getGoodsTypeList();
//                goodsTypeList.addAll(goodTypeNames);
//                carTypeList.addAll(carTypeNames);

                for (int i = 0; i < subjectsBeanList.size(); i++) {
                    SupportGoods supportGoods = new SupportGoods();
                    supportGoods.setName(subjectsBeanList.get(i).getName());
                    supportGoods.setType(subjectsBeanList.get(i).getType());
                    supportGoods.setImageUrl(subjectsBeanList.get(i).getImageUrl());
                    supportGoods.setSortId(subjectsBeanList.get(i).getSortId());
                    supportGoods.save();
                }
                SupportGoods supportGoods = new SupportGoods();
                supportGoods.setMarkTime(markTime);
                supportGoods.setGoodsTypeList(goodTypeNames);
                supportGoods.setGoodsTypeList(carTypeNames);
                supportGoods.updateAll();


                List<SupportGoods> supportGoodsList = DataSupport.findAll(SupportGoods.class);
                for (int i = 0; i < supportGoodsList.size(); i++) {
                    Logger.i(supportGoodsList.get(i).getName());

                }
            }
        };
        goods.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }

}
*/
