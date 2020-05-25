package com.sangjin.englishmaninkorea.englishlearn.data.source.local

import com.sangjin.englishmaninkorea.database.DB
import com.sangjin.englishmaninkorea.englishlearn.data.model.Learn
import com.sangjin.englishmaninkorea.englishlearn.data.model.TodayWord
import com.sangjin.englishmaninkorea.vocabularylist.data.Voca

interface LocalDataSource {

    fun saveLearnData(
        learnList : List<Learn>,
        roomDB: DB
    )

    fun getLearnData(
        roomDB: DB,
        onSuccess: (learnList: List<Learn>)-> Unit,
        onFailure: ((t: Throwable) -> Unit)
    )

    fun getTodayWord(
        roomDB: DB,
        onSuccess: (learn: Learn)-> Unit
    )
}