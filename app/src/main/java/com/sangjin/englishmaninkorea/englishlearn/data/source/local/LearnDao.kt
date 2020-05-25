package com.sangjin.englishmaninkorea.englishlearn.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sangjin.englishmaninkorea.englishlearn.data.model.Learn
import io.reactivex.Single

@Dao
interface LearnDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insetAll(learnList: List<Learn>)

    @Query("SELECT * FROM learn_table")
    fun getLearnList() : Single<List<Learn>>

    @Query("DELETE FROM learn_table")
    fun deleteAllList()

}