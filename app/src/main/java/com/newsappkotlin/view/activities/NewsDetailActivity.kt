package com.newsappkotlin.view.activities

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.newsappkotlin.R
import com.newsappkotlin.appUtils.MyAppConstant
import com.newsappkotlin.dtos.Article
import kotlinx.android.synthetic.main.activity_news_detail.*


class NewsDetailActivity : AppCompatActivity() {

    private val TAG = "NewsDetailActivity"

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        transparentStatusBar(this)
        val bundle: Bundle? = intent.extras
        var newsDetail = bundle?.getParcelable<Article>(MyAppConstant.EXTRA_PARCELABLE_FOR_DETAIL)

        Glide.with(this).load(newsDetail?.urlToImage).into(ivNews)
        tvNewsTitle.text = newsDetail?.title
        tvNewsSrc.text = newsDetail?.source?.name
        tvPublishedDate.text = newsDetail?.publishedAt
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tvNewsContent.text = Html.fromHtml(newsDetail?.content, Html.FROM_HTML_MODE_COMPACT)
        } else {
            if(null!=newsDetail?.content){
                tvNewsContent.text = Html.fromHtml(newsDetail?.content)
            }
        }
        Log.d(TAG, "newsDetail ${newsDetail.toString()}")
    }

    private fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
        val win: Window = activity.window
        val winParams: WindowManager.LayoutParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }

    fun backPress(view: View) {
        finish()

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun transparentStatusBar(ctx: Activity) {
        if (Build.VERSION.SDK_INT in 19..20) {
            setWindowFlag(ctx, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            ctx.window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        //make fully Android Transparent Status bar
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(ctx, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            ctx.window.statusBarColor = resources.getColor(R.color.transparent, null)
        }
    }
}