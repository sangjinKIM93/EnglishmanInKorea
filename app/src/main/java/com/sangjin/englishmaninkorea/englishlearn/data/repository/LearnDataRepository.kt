package com.sangjin.englishmaninkorea.englishlearn.data.repository

import com.sangjin.englishmaninkorea.database.DB
import com.sangjin.englishmaninkorea.englishlearn.data.model.Learn

interface LearnDataRepository {

    fun getEnglishContent(
        roomDB: DB,
        onSuccess: ((learnList: List<Learn>) -> Unit),
        onFailure: ((t: Throwable) -> Unit)
    )


}