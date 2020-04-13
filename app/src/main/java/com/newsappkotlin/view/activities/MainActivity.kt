package com.newsappkotlin.view.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.newsappkotlin.R
import com.newsappkotlin.appUtils.AppUtils
import com.newsappkotlin.dtos.Article
import com.newsappkotlin.dtos.CountryDto
import com.newsappkotlin.dtos.provideCountryList
import com.newsappkotlin.listener.MainActivityListener
import com.newsappkotlin.presenter.MainActivityPresenter
import com.newsappkotlin.presenterimpl.MainActivityPresenterImpl
import com.newsappkotlin.setDivider
import com.newsappkotlin.view.adapter.NewsAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainActivityListener, AdapterView.OnItemSelectedListener {

    private val TAG = "MainActivity"
    private lateinit var presenter: MainActivityPresenter
    private lateinit var newsAdapter: NewsAdapter
    var arrayList = ArrayList<CountryDto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate()")
        arrayList = provideCountryList()
        val countryAdapter = CountryAdapter(this, arrayList)
        spinner.adapter = countryAdapter
        spinner.onItemSelectedListener = this
        presenter = MainActivityPresenterImpl(this)
        newsAdapter = NewsAdapter(this)
        rvNews.layoutManager = LinearLayoutManager(this)
        rvNews.adapter = newsAdapter
        rvNews.setDivider(R.drawable.recycler_view_divider)


    }

    override fun getNewsResponseReqSuccess(list: List<Article>?) {
        hideProgressBar()
        Log.d(TAG, "getNewsResponseReqSuccess() list ${list?.size}")
        //Toast.makeText(this, list.toString(), Toast.LENGTH_SHORT).show()
        list?.let { newsAdapter.setDataToList(it) }
    }

    override fun getApiResponseReqFail(message: String) {
        hideProgressBar()
        Log.d(TAG, "getApiResponseReqFail() list $message")
        AppUtils.showToastMessage(this, message)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        showProgressBar()
        presenter.callGetNewsReqApi(arrayList[position].countryCode)
    }

    fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        if (null != progressBar && progressBar.isVisible) {
            progressBar.visibility = View.GONE
        }

    }

}
