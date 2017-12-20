package com.example.heartinfei.testsample.api;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author 王强 on 2017/12/15 249346528@qq.com
 */

public interface BaiduApi {
    @GET("fnng/p/4790294.html")
    Observable<String> request();
}
