package com.sangjin.englishmaninkorea.util

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sangjin.englishmaninkorea.englishlearn.adapter.LearnListAdapter
import com.sangjin.englishmaninkorea.englishlearn.data.model.Learn

@BindingAdapter("refreshData")
fun RecyclerView.refreshData(list: List<Learn>?){
    Log.d("#### bindingAdapter : ", "refreshData()")
    list?.let {
        val adapter = this.adapter as LearnListAdapter
        adapter.updateList(it)
        Log.d("#### bindingAdapter : ", "onSuccess")
    }
}