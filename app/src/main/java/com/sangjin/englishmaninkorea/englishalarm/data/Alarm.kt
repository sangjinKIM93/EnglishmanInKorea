package com.sangjin.englishmaninkorea.englishalarm.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "alarm_table")
data class Alarm(

    @PrimaryKey(autoGenerate = true)
    var alarmId: Long = 0L,

    @ColumnInfo(name = "alarm_time")
    var alarmTime: String = "",

    @ColumnInfo(name = "word_list")
    var wordList: List<Word>? = null,

    @ColumnInfo(name = "completed")
    var completed: Int = 0,

    @ColumnInfo(name = "checked")
    var checked: Int = 1
)

