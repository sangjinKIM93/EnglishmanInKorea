package com.sangjin.englishmaninkorea.englishlearn.adapter.innerrecycler

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sangjin.englishmaninkorea.englishlearn.data.model.TodayWord
import com.sangjin.englishmaninkorea.vocabularylist.data.Voca
import kotlinx.android.synthetic.main.item_recycler_recyler.view.*
import kotlinx.android.synthetic.main.item_recycler_recyler.view.rv_item
import kotlinx.android.synthetic.main.item_today_voca_recycler.view.*

class TodayWordViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){

    fun bindView(todayWords: List<Voca>){

        val todayWordAdapter = TodayWordAdapter()
        todayWordAdapter.submitList(todayWords.subList(0,4))

        itemView.rv_item.adapter = todayWordAdapter
        itemView.rv_item.layoutManager = GridLayoutManager(itemView.context, 2)


        var stateFirst = true

        itemView.btn_other_word.setOnClickListener {
            if(stateFirst == true){
                todayWordAdapter.submitList(todayWords.subList(4,8))
                itemView.btn_other_word.text = "다른 단어 보기 2/2"
                stateFirst = false
            } else {
                todayWordAdapter.submitList(todayWords.subList(0,4))
                itemView.btn_other_word.text = "다른 단어 보기 1/2"
                stateFirst = true
            }
        }

    }
}