package com.newsappkotlin.gateway

import com.newsappkotlin.appUtils.MyAppConstant
import com.newsappkotlin.dtos.NewsResponse
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class CommunicationManager {

    private var mCommunicationManager: CommunicationManager? = null

    fun getInstance(): CommunicationManager {
        if (mCommunicationManager == null) {
            mCommunicationManager = CommunicationManager()
        }
        return mCommunicationManager as CommunicationManager
    }


    private fun getRetrofitInstance(): RetrofitAPI? {
        var api: RetrofitAPI? = null
        val url: String?
        try {
            //Need to put in build config file
            url = "https://newsapi.org/v2/"
            val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .readTimeout(MyAppConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(MyAppConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            api = retrofit.create(RetrofitAPI::class.java)

        } catch (e: Exception) {
            e.printStackTrace()

        }
        return api
    }

    fun getNewsResponseReq(): Observable<NewsResponse>? {
        return try {
            getRetrofitInstance()?.getNewsResponse(
                MyAppConstant.API_KEY_VALUE,
                MyAppConstant.COUNTRY_VALUE
            )
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}
