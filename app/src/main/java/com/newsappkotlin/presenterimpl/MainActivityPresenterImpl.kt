package com.newsappkotlin.presenterimpl

import android.annotation.SuppressLint
import android.util.Log
import com.newsappkotlin.R
import com.newsappkotlin.appUtils.AppUtils
import com.newsappkotlin.dtos.NewsResponse
import com.newsappkotlin.gateway.CommunicationManager
import com.newsappkotlin.listener.MainActivityListener
import com.newsappkotlin.presenter.MainActivityPresenter
import com.newsappkotlin.view.activities.MainActivity
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class MainActivityPresenterImpl(private val context: MainActivity) :
    MainActivityPresenter {

    var listener: MainActivityListener = context
    private val TAG = "MainActivityPresenterImpl"

    @SuppressLint("LongLogTag")
    override fun callGetNewsReqApi(countryCode: String) {

        Log.d(TAG, "callGetNewsReqApi()")
        if (AppUtils.isNetworkAvailable(context)) {

            val communicationManager = CommunicationManager().getInstance()
            val observable: Observable<NewsResponse> =
                communicationManager.getNewsResponseReq(countryCode)!!.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread()).apply {
                        subscribe(object : Observer<NewsResponse> {

                            override fun onSubscribe(d: Disposable) {
                            }

                            override fun onNext(response: NewsResponse) {
                                Log.d(TAG, "response ${response?.articles}")
                                listener.getNewsResponseReqSuccess(response?.articles)
                            }

                            override fun onError(e: Throwable) {
                                if (e != null) {
                                    Log.d(TAG, "onFailure ${e.message}")
                                    e.message?.let { listener.getApiResponseReqFail(it) }
                                }
                            }

                            override fun onComplete() {

                            }
                        })
                    }

        } else {
            Log.d(TAG, "No internet connection")
            listener.getApiResponseReqFail(context.getString(R.string.no_internet))
        }
    }
}