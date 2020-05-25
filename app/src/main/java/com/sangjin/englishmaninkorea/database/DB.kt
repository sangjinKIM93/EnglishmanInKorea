package com.sangjin.englishmaninkorea.database

import android.content.Context
import androidx.room.*
import com.sangjin.englishmaninkorea.englishalarm.data.*
import com.sangjin.englishmaninkorea.englishlearn.data.model.Learn
import com.sangjin.englishmaninkorea.englishlearn.data.source.local.LearnDao
import com.sangjin.englishmaninkorea.vocabularylist.data.Voca
import com.sangjin.englishmaninkorea.vocabularylist.data.VocaDao

@Database(entities = arrayOf(Alarm::class, Voca::class, Word::class, Learn::class), version = 17)
@TypeConverters(Converters::class)
abstract class DB : RoomDatabase(){

    abstract val alarmDao : AlarmDao
    abstract val vocaDao : VocaDao
    abstract val wordDao : WordDao
    abstract val learnDao : LearnDao

    companion object{

        @Volatile
        private var INSTANCE: DB? = null

        fun getInstance(context: Context): DB {

            synchronized(this){
                var instance =
                    INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DB::class.java,
                        "alarm_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }

        }

    }
}