package com.wangjf.kotlinframwork.network.base

import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.wangjf.kotlinframwork.network.Constraint
import com.wangjf.oneday.BuildConfig
import com.wangjf.oneday.ui.base.BaseApplication
import okhttp3.*
import java.util.concurrent.TimeUnit

/**
 * Created by junfengwang on 2017/7/14.
 */
class OkHttpSingleton {

    companion object{
        var okHttpInstance = OkHttpSingleton()
    }

    /*val cacheSize : Long = 10*1024*1024 //设置缓存大小为10M
    val cachedirectory = File(FramworkApplication.mainContext?.externalCacheDir , "dirCache")
    val cache = Cache(cachedirectory , cacheSize)*/
    var httpClient: OkHttpClient?=null

    constructor() {
        httpClient = OkHttpClient()
        var httpClientBuilder = httpClient?.newBuilder()

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(BaseApplication.mainContext)
            httpClientBuilder?.addNetworkInterceptor(StethoInterceptor())
        }

        httpClientBuilder?.connectTimeout(Constraint.DEFAULT_CONNECT_TIMEOUT_MILLIS, TimeUnit.SECONDS)
                ?.readTimeout(Constraint.DEFAULT_READ_TIMEOUT_MILLIS, TimeUnit.SECONDS)
                ?.writeTimeout(Constraint.DEFAULT_WRITE_TIMEOUT_MILLIS, TimeUnit.SECONDS)

        httpClient = httpClientBuilder?.build()
    }

    fun getOkHttpClient() = okHttpInstance?.httpClient
}