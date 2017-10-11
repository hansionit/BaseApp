package com.hansion.baseapp.model.retrofit;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Description：
 * Author: Hansion
 * Time: 2016/9/20
 */
public interface ApiService {

    //举例
    //登出 http://192.72.1.1/cgi-bin/User.cgi?action=logout
    @GET("cgi-bin/User.cgi?")
    Observable<ResponseBody> logout(@Query("action") String logout);

}
