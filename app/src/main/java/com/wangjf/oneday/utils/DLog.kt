package com.wangjf.kotlinframwork.utils

import android.util.Log

object DLog {
    fun e(tag: String, value: Any) {
        Log.e(tag, value.toString() + "")
    }
}