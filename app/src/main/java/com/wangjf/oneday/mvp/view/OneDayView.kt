package com.wangjf.oneday.mvp.view

import com.wangjf.oneday.model.result.ArticleResult

/**
 * Created by junfengwang on 2017/9/10.
 */
interface OneDayView : BaseView{

    fun onSuccessed(result: ArticleResult , successCode: Int = 0)

}