package com.wangjf.kotlinframwork.model.result.base

import java.io.Serializable

/** 返回单个对象  */

data class ObjectResult<T>(var data:T) : Serializable
