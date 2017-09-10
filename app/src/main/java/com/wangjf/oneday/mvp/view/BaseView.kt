package com.wangjf.oneday.mvp.view

/**
 * Created by junfengwang on 2017/9/10.
 */
interface BaseView {

    fun onFailed(message: String? , errorCode : Int = -1)

}