package com.sangjin.englishmaninkorea.englishlearn.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sangjin.englishmaninkorea.R
import com.sangjin.englishmaninkorea.englishlearn.adapter.innerrecycler.QuotaViewHolder
import com.sangjin.englishmaninkorea.englishlearn.adapter.innerrecycler.NewsViewHolder
import com.sangjin.englishmaninkorea.englishlearn.adapter.innerrecycler.TodayWordViewHolder
import com.sangjin.englishmaninkorea.englishlearn.data.model.Learn
import kotlinx.android.synthetic.main.item_recycler_recyler.view.*

class LearnListAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var datas: List<Learn> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when(viewType){
            TYPE_NEWS -> {
                val newsItem = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_recyler, parent, false)
                NewsViewHolder(newsItem)
            }
            TYPE_TODAYWORD -> {
                val todayWordItem = LayoutInflater.from(parent.context).inflate(R.layout.item_today_voca_recycler, parent, false)
                TodayWordViewHolder(todayWordItem)
            }
            TYPE_QUOTA -> {
                val jokeItem = LayoutInflater.from(parent.context).inflate(R.layout.item_quota, parent, false)
                QuotaViewHolder(jokeItem)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder){
            is NewsViewHolder -> {

                holder.bindView(datas[position].newsList!!)
                holder.itemView.tv_partTitle.text = "뉴스"

            }
            is TodayWordViewHolder -> {

                holder.bindView(datas[position].todayWordList!!)
                holder.itemView.tv_partTitle.text = "오늘의 단어"

            }
            is QuotaViewHolder -> {

                datas[position].qouta?.let { holder.bindView(it) }
                holder.itemView.tv_partTitle.text = "오늘의 명언"

            }
        }

    }

    fun updateList(list : List<Learn>){

        datas = list

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = datas.size

    override fun getItemViewType(position: Int): Int {
        val comparable = datas.get(position).viewType
        return when(comparable){
            0 -> TYPE_LEARN
            1 -> TYPE_NEWS
            2 -> TYPE_TODAYWORD
            3 -> TYPE_QUOTA
            else -> throw IllegalArgumentException("Invalid type of data " + position)
        }
    }

    companion object {
        private const val TYPE_LEARN = 0
        private const val TYPE_NEWS = 1
        private const val TYPE_TODAYWORD = 2
        private const val TYPE_QUOTA = 3
    }
}