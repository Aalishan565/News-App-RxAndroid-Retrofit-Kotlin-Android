package com.newsappkotlin.gateway

import com.newsappkotlin.appUtils.MyAppConstant
import com.newsappkotlin.dtos.NewsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitAPI {

    @GET("top-headlines")
    fun getNewsResponse(
        @Query(MyAppConstant.API_KEY) apiKey: String,
        @Query(MyAppConstant.COUNTRY) country: String
    ): Observable<NewsResponse>?
}