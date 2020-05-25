package com.sangjin.englishmaninkorea.englishlearn.data.model

import androidx.room.*
import com.sangjin.englishmaninkorea.vocabularylist.data.Voca

@Entity(tableName = "learn_table")
data class Learn(

    @ColumnInfo(name = "view_type")
    val viewType: Int,

    @PrimaryKey(autoGenerate = true)
    val laernId: Long = 0L,

    @ColumnInfo(name= "news_list")
    var newsList : List<News> = listOf(),

    @ColumnInfo(name = "todayword_list")
    var todayWordList : List<Voca> = listOf(),

    @ColumnInfo(name = "quota")
    var qouta : Quota = Quota()
)