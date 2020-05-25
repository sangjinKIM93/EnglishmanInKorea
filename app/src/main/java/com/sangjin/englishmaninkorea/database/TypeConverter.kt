package com.sangjin.englishmaninkorea.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.sangjin.englishmaninkorea.englishalarm.data.Word
import com.sangjin.englishmaninkorea.englishlearn.data.model.DadJoke
import com.sangjin.englishmaninkorea.englishlearn.data.model.News
import com.sangjin.englishmaninkorea.englishlearn.data.model.Quota
import com.sangjin.englishmaninkorea.vocabularylist.data.Voca

class Converters {
    @TypeConverter
    fun listToJson(value: List<Word>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Word>::class.java).toList()

    @TypeConverter
    fun newsToJson(value: List<News>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToNews(value: String) = Gson().fromJson(value, Array<News>::class.java).toList()

    @TypeConverter
    fun todayWordToJson(value: List<Voca>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToTodayWord(value: String) = Gson().fromJson(value, Array<Voca>::class.java).toList()

    @TypeConverter
    fun quotaToString(value: Quota?) = Gson().toJson(value)

    @TypeConverter
    fun stringToQuota(string : String) : Quota = Gson().fromJson(string, Quota::class.java)
}