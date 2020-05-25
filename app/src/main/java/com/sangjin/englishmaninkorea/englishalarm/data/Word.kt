package com.sangjin.englishmaninkorea.englishalarm.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
data class Word(

    @PrimaryKey(autoGenerate = true)
    var wordId: Long = 0L,

    @ColumnInfo(name = "english_word")
    var englishWord: String = "",

    @ColumnInfo(name = "korean_meaning")
    var wordMeaning: String = ""

)