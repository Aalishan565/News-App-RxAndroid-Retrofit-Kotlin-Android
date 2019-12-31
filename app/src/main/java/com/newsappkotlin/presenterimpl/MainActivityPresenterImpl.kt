package com.newsappkotlin.presenterimpl

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.newsappkotlin.R
import com.newsappkotlin.appUtils.AppUtils
import com.newsappkotlin.dtos.NewsResponse
import com.newsappkotlin.gateway.CommunicationManager
import com.newsappkotlin.listener.MainActivityListener
import com.newsappkotlin.presenter.MainActivityPresenter
import com.newsappkotlin.view.activities.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityPresenterImpl(private val context: MainActivity) :
    MainActivityPresenter {
    var listener: MainActivityListener = context
    private val TAG = "MainActivityPresenterImpl"

    @SuppressLint("LongLogTag")
    override fun callGetNewsReqApi() {
        Log.d(TAG, "callGetNewsReqApi()")
        if (AppUtils.isNetworkAvailable(context)) {
            val communicationManager = CommunicationManager().getInstance()
            val call = communicationManager.getNewsResponseReq()
            call?.enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>?
                ) {
                    Log.d(TAG, "response ${response?.body()?.articles}")
                    listener.getNewsResponseReqSuccess(response?.body()?.articles)
                }

                override fun onFailure(call: Call<NewsResponse>?, t: Throwable) {
                    Log.d(TAG, "onFailure ${t.message}")
                    t.message?.let { listener.getApiResponseReqFail(it) }
                }
            })

        } else {
            Log.d(TAG, "No internet connection")
            listener.getApiResponseReqFail(context.getString(R.string.no_internet))
        }


    }
}