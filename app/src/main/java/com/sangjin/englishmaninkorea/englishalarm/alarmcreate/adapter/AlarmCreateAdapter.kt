package com.sangjin.englishmaninkorea.englishalarm.alarmcreate.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sangjin.englishmaninkorea.R
import com.sangjin.englishmaninkorea.englishalarm.data.Word
import kotlinx.android.synthetic.main.activity_alarm_create.view.*
import kotlinx.android.synthetic.main.item_word.view.*

class AlarmCreateAdapter(clickListener : ((Int)->Unit)): ListAdapter<Word, AlarmCreateViewHolder>(TodoListDiffCallback()){

    var onItemClickListener = clickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmCreateViewHolder {
        val alarmItem = LayoutInflater.from(parent.context).inflate(R.layout.item_word, parent, false)

        return AlarmCreateViewHolder(alarmItem).apply {
            alarmItem.setOnClickListener{
                var position = this.adapterPosition
                onItemClickListener(position)
            }
        }
    }

    override fun onBindViewHolder(holder: AlarmCreateViewHolder, position: Int) {
        holder.containerView.englishWordTV.text = getItem(position).englishWord

    }

}


class TodoListDiffCallback : DiffUtil.ItemCallback<Word>(){
    override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
        return oldItem.wordId == newItem.wordId
    }

    override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
        return oldItem == newItem
    }

}