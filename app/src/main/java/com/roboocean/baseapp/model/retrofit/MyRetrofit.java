package com.roboocean.baseapp.model.retrofit;

import com.google.gson.Gson;
import com.roboocean.baseapp.App;
import com.roboocean.baseapp.Constants;
import com.roboocean.baseapp.model.retrofit.cookie.AddCookiesInterceptor;
import com.roboocean.baseapp.model.retrofit.cookie.ReceivedCookiesInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Description：Retrofit封装类
 * Author: Hansion
 * Time: 2016/9/20
 */
public class MyRetrofit {

    private static final int DEFAULT_TIMEOUT = 3;
    private Retrofit retrofit;
    private ApiService apiService;

    //构造方法私有
    private MyRetrofit() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

                //okhttp的cookie持久化
                .addInterceptor(new ReceivedCookiesInterceptor(App.getAppContext()))
                .addInterceptor(new AddCookiesInterceptor(App.getAppContext()))

                .build();


        retrofit = new Retrofit.Builder()
                .client(httpClient)
                .baseUrl(Constants.BASE_HTTP)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final MyRetrofit INSTANCE = new MyRetrofit();
    }

    //获取单例
    public static MyRetrofit getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private <T> void toSubscribe(Observable<T> observable, Subscriber<T> subscriber) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    //举例
    public void logout(Subscriber<ResponseBody> subscriber, String logout) {
        toSubscribe(apiService.logout(logout), subscriber);
    }

}
