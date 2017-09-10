package com.wangjf.oneday.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.wangjf.oneday.R
import com.wangjf.oneday.model.result.ArticleResult
import com.wangjf.oneday.mvp.presenter.OneDayPresenter
import com.wangjf.oneday.mvp.view.OneDayView
import com.wangjf.oneday.utils.ToastUtils

class MainActivity : AppCompatActivity(), OneDayView {

    var oneDayPresenter: OneDayPresenter = OneDayPresenter(this , this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        oneDayPresenter.getArticle("20170909")
    }

    override fun onFailed(message: String?, errorCode: Int) {
        ToastUtils.showToast(message)
    }

    override fun onSuccessed(result: ArticleResult, successCode: Int) {
        ToastUtils.showToast(result.title)
    }
}
