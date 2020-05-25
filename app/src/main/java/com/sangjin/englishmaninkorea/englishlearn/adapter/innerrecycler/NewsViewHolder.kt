package com.sangjin.englishmaninkorea.englishlearn.adapter.innerrecycler

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sangjin.englishmaninkorea.englishlearn.data.model.News
import kotlinx.android.synthetic.main.item_recycler_recyler.view.*

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    fun bindView(news: List<News>){

        val newsAdapter =
            NewsAdapter(
                news
            )
        itemView.rv_item.adapter = newsAdapter
        itemView.rv_item.layoutManager = LinearLayoutManager(itemView.context)

    }
}