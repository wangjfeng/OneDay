package com.wangjf.kotlinframwork.network

import com.wangjf.kotlinframwork.model.req.BaseReq
import com.wangjf.kotlinframwork.model.result.base.BaseResult
import io.reactivex.Observable
import retrofit2.http.POST

/**
 * Created by Administrator on 2017/7/17.
 */
interface ApiService {

    @POST("/")
    fun getLogin(req : BaseReq) : Observable<BaseResult<String>>
}