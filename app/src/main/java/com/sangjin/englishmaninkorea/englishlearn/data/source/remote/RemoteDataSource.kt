package com.sangjin.englishmaninkorea.englishlearn.data.source.remote

import com.sangjin.englishmaninkorea.englishlearn.data.model.Learn

interface RemoteDataSource {

    fun getEnglishContent(onSuccess: (learnList: ArrayList<Learn>)-> Unit, onFailure: ((t: Throwable) -> Unit))
}