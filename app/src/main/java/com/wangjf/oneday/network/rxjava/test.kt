package com.wangjf.oneday.network.rxjava

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe

/**
 * Created by Administrator on 2017/9/4.
 */

class test {

    private fun test() {
        val observable = Observable.create(ObservableOnSubscribe<Int> { e ->
            e.onNext(1)
            e.onNext(2)
            e.onComplete()
        })

        observable.subscribe()
    }
}
