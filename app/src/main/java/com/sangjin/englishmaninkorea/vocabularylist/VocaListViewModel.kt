package com.sangjin.englishmaninkorea.vocabularylist

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.sangjin.englishmaninkorea.database.DB
import com.sangjin.englishmaninkorea.vocabularylist.data.Voca
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VocaListViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application
    private val vocaDao = DB.getInstance(application).vocaDao

    private var _vocabularyList = vocaDao.getAllVoca()
    val vocaList  = _vocabularyList

    private val dbScope = CoroutineScope(Dispatchers.IO)

    fun addList(english : String, korean : String){
        dbScope.launch {
            val voca = Voca()
            voca.englishWord = english
            voca.koreanMeaning = korean
            vocaDao.insert(voca)
            Log.d("VocaListViewModel", "insert 수행")
        }
    }



}