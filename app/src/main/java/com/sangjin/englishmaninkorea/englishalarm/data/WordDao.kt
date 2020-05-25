package com.sangjin.englishmaninkorea.englishalarm.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(word: Word)

    @Update
    fun update(word: Word)

    @Delete
    fun delete(word: Word)

    @Query("SELECT * FROM word_table")
    fun getAllWord(): LiveData<List<Word>>

    @Query("DELETE FROM word_table")
    fun deleteAll()


}