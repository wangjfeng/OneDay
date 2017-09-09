package com.wangjf.oneday.ui.base

import android.app.Application
import android.content.Context

/**
 * Created by junfengwang on 2017/9/9.
 */
class BaseApplication : Application(){

    companion object{
        var mainContext : Context?=null
    }

    override fun onCreate() {
        super.onCreate()
        mainContext = applicationContext
    }
}