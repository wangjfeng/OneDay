package com.wangjf.kotlinframwork.model.result.base

import java.io.Serializable


/**
 * Created by Administrator on 2017/7/17.
 */
data class BaseResult<T> (var status: Int , var msg: String , var data: T): Serializable