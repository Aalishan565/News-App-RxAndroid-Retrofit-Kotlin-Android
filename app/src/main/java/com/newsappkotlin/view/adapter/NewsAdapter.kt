package com.newsappkotlin.view.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.newsappkotlin.R
import com.newsappkotlin.dtos.Article
import kotlinx.android.synthetic.main.row_item_news.view.*

class NewsAdapter(private val context: Context) :
    RecyclerView.Adapter<NewsAdapter.NewsAdapterViewHolder>() {

    private val TAG = "NewsAdapter"
    private lateinit var view: View
    private var newsList: List<Article> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapterViewHolder {
        Log.d(TAG, "onCreateViewHolder()")
        view = LayoutInflater.from(context).inflate(R.layout.row_item_news, parent, false)
        return NewsAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount()")
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsAdapterViewHolder, position: Int) {
        //  Log.d(TAG, "onBindViewHolder()")
        holder.tvNewsTitle.text = newsList!![position].title
        holder.tvNewsSrc.text = newsList!![position].source.name
        holder.tvPublishedDate.text = newsList!![position].publishedAt
        Glide.with(context).load(newsList[position].urlToImage).into(holder.ivNews)
        holder.ivNews.setScaleType(ImageView.ScaleType.FIT_XY);
        Log.d(TAG, "onBindViewHolder()${newsList[position].urlToImage}  ${holder.ivNews.drawable}")

    }

    fun setDataToList(newsList: List<Article>) {
        Log.d(TAG, "setDataToList() ${newsList}")
        this.newsList = newsList
        notifyDataSetChanged()
    }

    class NewsAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardNews = view.cardNews!!
        val frameNews = view.frameNews!!
        val ivNews = view.ivNews!!
        val tvNewsTitle = view.tvNewsTitle!!
        val tvNewsSrc = view.tvNewsSrc!!
        val tvPublishedDate = view.tvPublishedDate!!
    }
}