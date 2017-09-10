package com.wangjf.oneday.mvp.presenter

import android.content.Context
import com.wangjf.kotlinframwork.network.rxjava.RxSubscribe
import com.wangjf.oneday.model.result.ArticleResult
import com.wangjf.oneday.mvp.model.OneDayModel
import com.wangjf.oneday.mvp.view.OneDayView

/**
 * Created by junfengwang on 2017/9/10.
 */
class OneDayPresenter {

    var oneDayModel: OneDayModel = OneDayModel()
    var oneDayView: OneDayView? =null
    var context: Context? =null

    constructor(oneDayView: OneDayView?, context: Context?) {
        this.oneDayView = oneDayView
        this.context = context
    }

    fun getArticle(date: String){
        oneDayModel.getArticle(date , object : RxSubscribe<ArticleResult>(context!!){
            override fun _onError(message: String?) {
                oneDayView?.onFailed(message)
            }

            override fun _onNext(t: ArticleResult) {
                oneDayView?.onSuccessed(t)
            }
        })
    }
}