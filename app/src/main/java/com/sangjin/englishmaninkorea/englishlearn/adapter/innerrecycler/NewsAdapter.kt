package com.sangjin.englishmaninkorea.englishlearn.adapter.innerrecycler

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.sangjin.englishmaninkorea.R
import com.sangjin.englishmaninkorea.englishlearn.adapter.ItemViewHolder
import com.sangjin.englishmaninkorea.englishlearn.data.model.News
import kotlinx.android.synthetic.main.item_news.view.*
import kotlinx.android.synthetic.main.item_voca.view.*

class NewsAdapter(private val news: List<News>): RecyclerView.Adapter<ItemViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)

        return ItemViewHolder(view).apply {
            itemView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(news[adapterPosition].url))
                parent.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int = news.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        holder.containerView.tv_new_title.text = news[position].title

        Glide.with(holder.containerView.iv_news.context)
            .load(news[position].urlToImage)
            .centerCrop()
            .placeholder(R.drawable.img_default)
            .into(holder.containerView.iv_news)

    }


}