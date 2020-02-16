package com.newsappkotlin.listener

import com.newsappkotlin.dtos.Article

interface MainActivityListener {

    fun getNewsResponseReqSuccess(list: List<Article>?)
    fun getApiResponseReqFail(message: String)

}