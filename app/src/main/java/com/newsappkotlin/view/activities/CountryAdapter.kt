package com.newsappkotlin.view.activities

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.newsappkotlin.R
import com.newsappkotlin.dtos.CountryDto
import kotlin.math.log


class CountryAdapter(context: Context, var countryArrayList: ArrayList<CountryDto>) :
    BaseAdapter() {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val vh: ItemRowHolder
        if (convertView == null) {
            view = mInflater.inflate(R.layout.spn_view, parent, false)
            vh = ItemRowHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ItemRowHolder
        }
        vh.tvCountryName.text = countryArrayList[position].countryName
        return view
    }

    override fun getItem(position: Int): Any? {
        return countryArrayList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return countryArrayList.size
    }

    private inner class ItemRowHolder(row: View?) {
        val tvCountryName: TextView = row?.findViewById(R.id.tv_country) as TextView
    }

}