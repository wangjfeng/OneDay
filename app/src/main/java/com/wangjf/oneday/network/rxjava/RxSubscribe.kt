package com.wangjf.kotlinframwork.network.rxjava

/**
 * Created by Administrator on 2017/7/18.
 */
import android.content.Context

import com.wangjf.kotlinframwork.utils.NetWorkUtil
import io.reactivex.observers.DisposableObserver

import java.net.ConnectException
import java.net.SocketTimeoutException

import retrofit2.HttpException

/**
 * 回调封装
 */
abstract class RxSubscribe<T>(private val mContext: Context) : DisposableObserver<T>() {

    override fun onComplete() {
        if (!isDisposed) {
            dispose()
        }
    }

    override fun onStart() {
        if (!NetWorkUtil.isNetWorkConnected(mContext)) {
            if (!isDisposed) {
                dispose()
            }
            _onError("网络不可用")
        }
    }

    override fun onNext(t: T) {
        _onNext(t)
    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
        if (!NetWorkUtil.isNetWorkConnected(mContext)) {
            _onError("网络不可用")
            return
        }
        when (e) {
            is SocketTimeoutException -> _onError("请求超时,请稍后再试...")
            is ConnectException -> _onError("请求超时,请稍后再试...")
            is HttpException -> _onError("服务器异常，请稍后再试...")
            else -> _onError(e.message)
        }
    }

    protected abstract fun _onNext(t: T)

    protected abstract fun _onError(message: String?)

}
