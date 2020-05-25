package com.sangjin.englishmaninkorea.englishalarm.alarmlist.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sangjin.englishmaninkorea.R
import com.sangjin.englishmaninkorea.englishalarm.data.Alarm
import com.sangjin.englishmaninkorea.englishalarm.data.Word
import kotlinx.android.synthetic.main.item_alarm.view.*
import kotlinx.android.synthetic.main.item_word_no_delete.view.*

class AlarmListAdapter(): ListAdapter<Word, AlarmListViewHolder>(TodoListDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmListViewHolder {
        val alarmItem = LayoutInflater.from(parent.context).inflate(R.layout.item_word_no_delete, parent, false)

        return AlarmListViewHolder(alarmItem)
    }

    override fun onBindViewHolder(holder: AlarmListViewHolder, position: Int) {
        holder.containerView.englishWordTV_noDelete.text = getItem(position).englishWord

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