package com.sangjin.englishmaninkorea.englishlearn.data.source.remote

import com.sangjin.englishmaninkorea.englishlearn.data.model.News

data class NewsResponse(
    val articles : List<News>
)