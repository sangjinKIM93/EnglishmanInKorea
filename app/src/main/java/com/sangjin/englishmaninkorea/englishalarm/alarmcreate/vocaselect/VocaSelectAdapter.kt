package com.sangjin.englishmaninkorea.englishalarm.alarmcreate.vocaselect

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sangjin.englishmaninkorea.R
import com.sangjin.englishmaninkorea.vocabularylist.data.Voca
import kotlinx.android.synthetic.main.item_voca.view.*

class VocaSelectAdapter(): ListAdapter<Voca, VocaSelectViewHolder>(TodoListDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VocaSelectViewHolder {
        val alarmItem = LayoutInflater.from(parent.context).inflate(R.layout.item_voca, parent, false)

        return VocaSelectViewHolder(alarmItem).apply {
            alarmItem.setOnClickListener{
                var position = this.adapterPosition
                if(selectionList.contains(position)) selectionList.remove(position)
                else selectionList.add(position)
                notifyDataSetChanged()

                onItemSelectedListener?.let{ it(selectionList)}
            }
        }
    }

    override fun onBindViewHolder(holder: VocaSelectViewHolder, position: Int) {
        holder.containerView.tv_englishWord.text = getItem(position).englishWord
        holder.containerView.tv_koreanMeaning.text = getItem(position).koreanMeaning
        holder.containerView.isActivated = selectionList.contains(position)
    }


    val selectionList = mutableListOf<Int>()
    var onItemSelectedListener: ((MutableList<Int>) -> Unit)? = null


}


class TodoListDiffCallback : DiffUtil.ItemCallback<Voca>(){
    override fun areItemsTheSame(oldItem: Voca, newItem: Voca): Boolean {
        return oldItem.vocaId == newItem.vocaId
    }

    override fun areContentsTheSame(oldItem: Voca, newItem: Voca): Boolean {
        return oldItem == newItem
    }

}