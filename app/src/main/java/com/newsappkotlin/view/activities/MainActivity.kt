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
    private var isSpinnerShown: Boolean = false

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
        isSpinnerShown = false
        resources.getDrawable(
            R.drawable.ic_arrow_drop_down_black_24dp,
            null
        )
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        isSpinnerShown = false
        showProgressBar()
        presenter.callGetNewsReqApi(arrayList[position].countryCode)
        showDownIcon()

    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        if (null != progressBar && progressBar.isVisible) {
            progressBar.visibility = View.GONE
        }

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (!hasFocus) {
            showUpIcon()
        } else {
            showDownIcon()
        }
    }

    private fun showUpIcon() {
        ivArrow.setImageDrawable(
            resources.getDrawable(
                R.drawable.ic_arrow_drop_up_black_24dp,
                null
            )
        )
    }

    private fun showDownIcon() {
        ivArrow.setImageDrawable(
            resources.getDrawable(
                R.drawable.ic_arrow_drop_down_black_24dp,
                null
            )
        )
    }

}
