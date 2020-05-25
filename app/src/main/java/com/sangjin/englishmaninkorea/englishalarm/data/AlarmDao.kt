package com.sangjin.englishmaninkorea.englishalarm.data

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.Deferred

@Dao
interface AlarmDao {

    @Insert
    fun insert(alarm: Alarm)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(alarm: Alarm)

    @Delete
    fun delete(alarm: Alarm)

    @Query("SELECT * FROM alarm_table")
    fun getAllAlarm(): LiveData<List<Alarm>>

    @Query("SELECT * FROM alarm_table where completed = 0 ORDER BY alarmId DESC LIMIT 1")
    fun getLastAlarm(): Alarm

    @Query("SELECT * FROM alarm_table where completed = 0")
    fun getUnCompletedAlarm(): LiveData<Alarm>

}