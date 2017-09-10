package com.wangjf.oneday.mvp.model

import com.wangjf.kotlinframwork.network.ApiService
import com.wangjf.kotlinframwork.network.base.RetrofitSingleton
import com.wangjf.kotlinframwork.network.rxjava.RxHelper
import com.wangjf.kotlinframwork.network.rxjava.RxSubscribe
import com.wangjf.oneday.model.result.ArticleResult

/**
 * Created by junfengwang on 2017/9/10.
 */
class OneDayModel {

    var service: ApiService?= null

    init {
        service = RetrofitSingleton.instance.apiService
    }

    fun getArticle(date: String , subscribe: RxSubscribe<ArticleResult>){
        service?.getDayArticle(date = date)
                ?.compose(RxHelper.handleResult())
                ?.subscribe(subscribe)
    }
}