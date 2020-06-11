package com.sangjin.englishmaninkorea.englishalarm.alarmlist

import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.sangjin.englishmaninkorea.englishalarm.alarmshow.AlarmReceiver
import com.sangjin.englishmaninkorea.englishalarm.data.Alarm
import com.sangjin.englishmaninkorea.database.DB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlarmListViewModel(application: Application) : AndroidViewModel(application){

    private val context = application
    private val alarmDao = DB.getInstance(application).alarmDao

    var isEdited = MutableLiveData<Boolean>()
    var isCreated = MutableLiveData<Boolean>()

    private var _alarmList = alarmDao.getUnCompletedAlarm()
    val alarmList = _alarmList

    private val dbScope = CoroutineScope(Dispatchers.IO)


    fun deleteAlarm(){
        if(_alarmList.value != null){

            dbScope.launch {
                alarmDao.delete(_alarmList.value!!)
            }
        }

        deleteAlarmManager()

        isEdited.value = false
        isCreated.value = true
    }

    fun deleteAlarmManager(){
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val alarmIntent = Intent(context, AlarmReceiver::class.java).let{
            PendingIntent.getBroadcast(context, 0, it, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        alarmManager.cancel(alarmIntent)
    }


}