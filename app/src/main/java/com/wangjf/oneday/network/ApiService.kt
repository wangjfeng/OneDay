package com.wangjf.kotlinframwork.network

import com.wangjf.kotlinframwork.model.req.BaseReq
import com.wangjf.kotlinframwork.model.result.base.BaseResult
import com.wangjf.kotlinframwork.model.result.base.ObjectResult
import com.wangjf.oneday.model.result.ArticleResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by Administrator on 2017/7/17.
 */
interface ApiService {

    @POST("/")
    fun getLogin(req : BaseReq) : Observable<BaseResult<String>>

    @GET("day")
    fun getDayArticle(dev:Int = 1, date:String) :  Observable<BaseResult<ArticleResult>>

    @GET("today")
    fun getTodayArticle(dev:Int = 1) :  Observable<BaseResult<ArticleResult>>

    @GET("random")
    fun getRandomArticle(dev:Int = 1) :  Observable<BaseResult<ArticleResult>>
}