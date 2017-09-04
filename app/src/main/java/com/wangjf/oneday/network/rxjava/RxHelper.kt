package com.wangjf.kotlinframwork.network.rxjava

import com.wangjf.kotlinframwork.model.result.base.BaseResult
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber


/**
 * Created by Administrator on 2017/7/18.
 * 处理返回后的数据
 */
object RxHelper {

    fun <T> handleResult(): ObservableTransformer<BaseResult<T>, T> {
        return ObservableTransformer { tObservable ->
            tObservable.flatMap { result ->
                if (result.status == 0) {
                    createData(result.data)
                } else {
                    Observable.error<T>(Exception(result.msg))
                }
            }
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }

    }

    fun <T> createData(data : T): Observable<T>{
        return Observable.create{
                t ->
                t.onNext(data)
                t.onComplete()
            }
    }
}