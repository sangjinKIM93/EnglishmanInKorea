package com.sangjin.englishmaninkorea.vocabularylist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "voca_table")
data class Voca(

    @PrimaryKey(autoGenerate = true)
    var vocaId: Long = 0L,

    @ColumnInfo(name = "english_word")
    var englishWord: String = "",

    @ColumnInfo(name = "korean_meaning")
    var koreanMeaning: String = "",

    @ColumnInfo(name = "checked")
    var checked: Int = 0
)