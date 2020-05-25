package com.sangjin.englishmaninkorea.vocabularylist.data

import androidx.lifecycle.LiveData
import androidx.room.*
import io.reactivex.Single

@Dao
interface VocaDao {

    @Insert
    fun insert(voca: Voca)

    @Update
    fun update(voca: Voca)

    @Delete
    fun delete(voca: Voca)

    @Query("SELECT * FROM voca_table")
    fun getAllVoca(): LiveData<List<Voca>>

    @Query("SELECT * FROM voca_table ORDER BY RANDOM() LIMIT 8")
    fun getRandomVoca(): Single<List<Voca>>

}