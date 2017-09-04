package com.wangjf.kotlinframwork.model.result.base

import java.io.Serializable

/** 返回单个对象  */

class ObjectResult<T> : Serializable {
    var obj: T? = null

    companion object {
        const val serialversionuid = 1L
    }

}
