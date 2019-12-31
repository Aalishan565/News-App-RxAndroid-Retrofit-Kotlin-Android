package com.newsappkotlin.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.newsappkotlin.R
import com.newsappkotlin.view.adapter.NewsAdapter
import com.newsappkotlin.listener.MainActivityListener
import com.newsappkotlin.dtos.Article
import com.newsappkotlin.presenter.MainActivityPresenter
import com.newsappkotlin.presenterimpl.MainActivityPresenterImpl
import com.newsappkotlin.setDivider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityListener {
    private val TAG = "MainActivity"
    private lateinit var presenter: MainActivityPresenter
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate()")
        presenter = MainActivityPresenterImpl(this)
        newsAdapter = NewsAdapter(this)
        rvNews.layoutManager = LinearLayoutManager(this)
        rvNews.adapter = newsAdapter
        rvNews.setDivider(R.drawable.recycler_view_divider)
        presenter.callGetNewsReqApi()
    }

    override fun getNewsResponseReqSuccess(list: List<Article>?) {
        Log.d(TAG, "getNewsResponseReqSuccess() list ${list?.size}")
        //Toast.makeText(this, list.toString(), Toast.LENGTH_SHORT).show()
        list?.let { newsAdapter.setDataToList(it) }
    }

    override fun getApiResponseReqFail(message: String) {
        Log.d(TAG, "getApiResponseReqFail() list $message")
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}
