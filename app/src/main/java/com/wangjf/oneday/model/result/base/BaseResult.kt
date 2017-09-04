package com.wangjf.kotlinframwork.model.result.base

import java.io.Serializable


/**
 * Created by Administrator on 2017/7/17.
 */
class BaseResult<T> : Serializable{

    //状态码
    var status: Int = 0
    //信息
    var msg: String? = null
    //数据部分
    var data: T? = null

    companion object {
        const val serialversionuid = 1L
    }
}