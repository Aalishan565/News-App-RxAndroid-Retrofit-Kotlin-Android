package com.newsappkotlin.presenterimpl

import android.annotation.SuppressLint
import android.util.Log
import com.newsappkotlin.gateway.CommunicationManager
import com.newsappkotlin.listener.MainActivityListener
import com.newsappkotlin.dtos.NewsResponse
import com.newsappkotlin.presenter.MainActivityPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityPresenterImpl(val listener: MainActivityListener) : MainActivityPresenter {
    private val TAG = "MainActivityPresenterImpl"

    @SuppressLint("LongLogTag")
    override fun callGetNewsReqApi() {
        Log.d(TAG, "callGetNewsReqApi()")
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

    }
}