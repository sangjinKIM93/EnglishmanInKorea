package com.sangjin.englishmaninkorea.englishlearn.adapter.innerrecycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sangjin.englishmaninkorea.R
import com.sangjin.englishmaninkorea.englishlearn.adapter.ItemViewHolder
import com.sangjin.englishmaninkorea.englishlearn.data.model.TodayWord
import com.sangjin.englishmaninkorea.vocabularylist.data.Voca
import kotlinx.android.synthetic.main.item_voca.view.*

class TodayWordAdapter(): RecyclerView.Adapter<ItemViewHolder>(){

    private var todayWordList : List<Voca> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_voca, parent, false)

        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        holder.containerView.tv_englishWord.text = todayWordList[position].englishWord
        holder.containerView.tv_koreanMeaning.text = todayWordList[position].koreanMeaning

    }

    override fun getItemCount(): Int = todayWordList.size

    fun submitList(list : List<Voca>){
        todayWordList = list
        notifyDataSetChanged()
    }

}