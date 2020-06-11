package com.sangjin.englishmaninkorea.englishlearn.adapter.innerrecycler

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sangjin.englishmaninkorea.vocabularylist.data.Voca
import kotlinx.android.synthetic.main.item_recycler_recyler.view.rv_item
import kotlinx.android.synthetic.main.item_today_voca_recycler.view.*

class TodayWordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(todayWords: List<Voca>) {

        val todayWordAdapter = TodayWordAdapter()
        itemView.rv_item.adapter = todayWordAdapter
        itemView.rv_item.layoutManager = GridLayoutManager(itemView.context, 2)

        //** 등록된 단어가 4개 이하인 경우와 4개 이상인 경우 나누기
        if (todayWords.size > 4) {

            todayWordAdapter.submitList(todayWords.subList(0, 4))
            itemView.btn_other_word.visibility = View.VISIBLE

            //단어 전환 버튼 클릭 리스너
            wordChangeBtnListener(todayWords, todayWordAdapter)

        } else {

            todayWordAdapter.submitList(todayWords.subList(0, todayWords.lastIndex+1))
            itemView.btn_other_word.visibility = View.GONE

        }

    }

    private fun wordChangeBtnListener(
        todayWords: List<Voca>,
        todayWordAdapter: TodayWordAdapter
    ) {
        var isFirst = true  //"다른 단어 보기 1/2" 일때 true

        itemView.btn_other_word.setOnClickListener {

            if (isFirst) {

                //** 단어가 8개 보다 많은 경우 8개 까지만 자르기
                if (todayWords.size > 8) {
                    todayWordAdapter.submitList(todayWords.subList(4, 8))
                } else {
                    todayWordAdapter.submitList(todayWords.subList(4, todayWords.lastIndex + 1))
                }

                itemView.btn_other_word.text = "다른 단어 보기 2/2"
                isFirst = false

            } else {
                todayWordAdapter.submitList(todayWords.subList(0, 4))

                itemView.btn_other_word.text = "다른 단어 보기 1/2"
                isFirst = true
            }

        }
    }
}