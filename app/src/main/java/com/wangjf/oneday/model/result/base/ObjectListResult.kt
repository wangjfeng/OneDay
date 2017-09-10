package com.wangjf.kotlinframwork.model.result.base

import java.io.Serializable

/** 返回多个对象  */

class ObjectListResult<T> (var list: List<T>): Serializable
