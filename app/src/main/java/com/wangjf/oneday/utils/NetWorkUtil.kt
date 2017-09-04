package com.wangjf.kotlinframwork.utils

import android.content.Context
import android.net.ConnectivityManager


/**
 * Created by Administrator on 2017/7/18.
 */
object NetWorkUtil {

    //判断是否有网络
    @JvmStatic fun isNetWorkConnected(context: Context): Boolean {
        if (context != null) {
            val mConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val mNetworkInfo = mConnectivityManager.activeNetworkInfo
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable && mNetworkInfo.isConnected
            }
        }
        return false
    }
}