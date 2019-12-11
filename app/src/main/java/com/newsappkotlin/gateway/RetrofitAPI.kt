package com.newsappkotlin.gateway

import com.newsappkotlin.appUtils.MyAppConstant
import com.newsappkotlin.dtos.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitAPI {

    @GET("top-headlines")
    fun getNewsResponse(@Query(MyAppConstant.API_KEY)  apiKey:String,
                        @Query(MyAppConstant.COUNTRY) country:String): Call<NewsResponse>?

}