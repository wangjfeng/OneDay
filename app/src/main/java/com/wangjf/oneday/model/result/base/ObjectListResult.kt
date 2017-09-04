package com.wangjf.kotlinframwork.model.result.base

import java.io.Serializable

/** 返回多个对象  */

class ObjectListResult<T> : Serializable {
    var list: List<T>? = null

    companion object {
        const val serialversionuid = 1L
    }

}
