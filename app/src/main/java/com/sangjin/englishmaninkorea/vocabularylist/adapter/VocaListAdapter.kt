package com.sangjin.englishmaninkorea.vocabularylist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sangjin.englishmaninkorea.R
import com.sangjin.englishmaninkorea.vocabularylist.data.Voca
import kotlinx.android.synthetic.main.item_voca.view.*

class VocaListAdapter(clickListener : ((Int)->Unit)): ListAdapter<Voca, VocaListViewHolder>(TodoListDiffCallback()){

    var onItemClickListener = clickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VocaListViewHolder {
        val alarmItem = LayoutInflater.from(parent.context).inflate(R.layout.item_voca, parent, false)

        return VocaListViewHolder(alarmItem).apply {
            alarmItem.setOnClickListener{
                var position = this.adapterPosition
                onItemClickListener(position)
            }
        }
    }

    override fun onBindViewHolder(holder: VocaListViewHolder, position: Int) {
        holder.containerView.tv_englishWord.text = getItem(position).englishWord
        holder.containerView.tv_koreanMeaning.text = getItem(position).koreanMeaning
    }

}


class TodoListDiffCallback : DiffUtil.ItemCallback<Voca>(){
    override fun areItemsTheSame(oldItem: Voca, newItem: Voca): Boolean {
        return oldItem.vocaId == newItem.vocaId
    }

    override fun areContentsTheSame(oldItem: Voca, newItem: Voca): Boolean {
        return oldItem == newItem
    }

}