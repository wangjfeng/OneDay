package com.wangjf.oneday.utils

import android.widget.Toast
import com.wangjf.oneday.ui.base.BaseApplication

/**
 * Created by junfengwang on 2017/9/10.
 */
object ToastUtils {

    fun showToast(msg: String? , lenght: Int = Toast.LENGTH_SHORT){
        Toast.makeText(BaseApplication.mainContext , msg , lenght)
    }
}