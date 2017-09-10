package com.wangjf.kotlinframwork.network.base

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.wangjf.kotlinframwork.network.ApiService
import com.wangjf.kotlinframwork.network.Constraint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Administrator on 2017/7/17.
 */
class RetrofitSingleton private constructor() {
    var apiService: ApiService? = null
        get() = instance.apiService

    private var retrofit: Retrofit ? = null

    init {
        retrofit = Retrofit.Builder().baseUrl(Constraint.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonUtil.create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OkHttpSingleton.okHttpInstance.getOkHttpClient())
                .build()
        apiService = retrofit?.create(ApiService::class.java)
    }

    companion object {
        var instance = RetrofitSingleton()
    }
}