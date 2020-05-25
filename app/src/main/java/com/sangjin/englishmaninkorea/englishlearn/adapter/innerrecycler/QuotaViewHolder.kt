package com.sangjin.englishmaninkorea.englishlearn.adapter.innerrecycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sangjin.englishmaninkorea.englishlearn.data.model.DadJoke
import com.sangjin.englishmaninkorea.englishlearn.data.model.Quota
import kotlinx.android.synthetic.main.item_quota.view.*

class QuotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    fun bindView(quota: Quota){

        itemView.tv_jokeSetup.text = quota.text
        itemView.tv_jokePunchline.text = quota.author

    }
}