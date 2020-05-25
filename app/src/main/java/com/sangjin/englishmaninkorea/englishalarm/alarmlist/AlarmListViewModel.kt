package com.sangjin.englishmaninkorea.englishalarm.alarmlist

import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.sangjin.englishmaninkorea.englishalarm.alarmshow.AlarmReceiver
import com.sangjin.englishmaninkorea.englishalarm.data.Alarm
import com.sangjin.englishmaninkorea.database.DB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlarmListViewModel(application: Application) : AndroidViewModel(application){

    private val context = application
    private val alarmDao = DB.getInstance(application).alarmDao

    private var _alarmList = alarmDao.getUnCompletedAlarm()
    var alarmList : LiveData<Alarm>? = _alarmList

    private val dbScope = CoroutineScope(Dispatchers.IO)

    fun deleteAlarm(){
        if(_alarmList.value != null){

            dbScope.launch {
                alarmDao.delete(_alarmList.value!!)
            }
        }

        deleteAlarmManager()
    }

    fun deleteAlarmManager(){
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val alarmIntent = Intent(context, AlarmReceiver::class.java).let{
            PendingIntent.getBroadcast(context, 0, it, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        alarmManager.cancel(alarmIntent)
    }


}