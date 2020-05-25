package com.sangjin.englishmaninkorea.englishalarm.alarmcreate

import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.sangjin.englishmaninkorea.englishalarm.alarmshow.AlarmReceiver
import com.sangjin.englishmaninkorea.englishalarm.data.Alarm
import com.sangjin.englishmaninkorea.database.DB
import com.sangjin.englishmaninkorea.englishalarm.data.Word
import kotlinx.coroutines.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

class AlarmCreateViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application
    private var alarmDao = DB.getInstance(context).alarmDao
    private var wordDao = DB.getInstance(context).wordDao

    private var _wordList = wordDao.getAllWord()
    val wordList = _wordList

    //timepicker에서 선택된 시간
    var hour = 0
    var min = 0

    private var _isEdited: Boolean = false  //수정 버튼으로 들어온건지, 추가 버튼으로 들어온건지 확인
    var isEdited = _isEdited

    private var lastAlarm : Alarm ?= null

    private val dbScope = CoroutineScope(Dispatchers.IO)

    init {
        CoroutineScope(Dispatchers.Main).launch {       //.join .await 메서드는 코루틴 안에서만 실행 가능해.

            dbScope.launch {        //RoomDB 작업은 메인 스레드에서 할 수 없어
                lastAlarm = alarmDao.getLastAlarm()
            }.join()

            checkEdit()
        }

    }


    private fun checkEdit() {

        //수정하는 경우 단어 리스트를 가져와서 보여줌
        if (lastAlarm != null) {

            dbScope.launch {

                for(word in lastAlarm!!.wordList!!){
                    wordDao.insert(word)
                }
            }

            isEdited = true //수정으로 불러온건지 체크
        }
    }


    fun addWordList(englishWord: String, meaning: String) {
        val word = Word()
        word.englishWord = englishWord
        word.wordMeaning = meaning

        dbScope.launch {
            wordDao.insert(word)
        }
    }


    //wordList에서 특정 단어 삭제
    fun removeWord(word: Word) {

        dbScope.launch {
            wordDao.delete(word)
        }
    }


    //알람DB에 추가 및 알람매니저 등록
    fun addAlarm() {

        dbScope.launch {
            if (isEdited == false) {
                val alarm = Alarm()
                alarm.alarmTime = "${timeFormat(hour)} : ${timeFormat(min)}"
                alarm.wordList = _wordList.value!!.toList()  //여기 null 이면 추가 안 되도록 처리해주자.

                alarmDao.insert(alarm)
            } else {
                lastAlarm?.alarmTime = "${timeFormat(hour)} : ${timeFormat(min)}"
                lastAlarm?.wordList = _wordList.value!!.toList()

                alarmDao.update(lastAlarm!!)
            }
        }
        //알람매니저 등록. 일단 알람 하나만 등록하게 만들었으니까 추가든 수정이든 상관없어.
        createAlarmManager()
    }


    //한자리수 숫자는 0 붙여주기
    private fun timeFormat(clock: Int): String {
        var clockStr: String = clock.toString()
        if (clock < 10) {
            clockStr = "0" + clock
        }
        return clockStr
    }


    //알람매니저에 등록
    fun createAlarmManager() {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        //이렇게 팬딩인텐트와 그냥 인텐트를 합칠 수 있는듯
        val alarmIntent = Intent(context, AlarmReceiver::class.java).let {
            PendingIntent.getBroadcast(context, 0, it, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        //localdatetime으로 시간 설정하기.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var setDateTime = LocalDate.now().atTime(hour, min).apply {
                if (this < LocalDateTime.now()){
                    plusDays(1)
                }
            }
        }
        else {
        }

        //캘린더 객체를 통해서 알람 시간 정하기
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()

            var (year: Int, alarmDay: Int) = alarmDateSet()

            set(Calendar.YEAR, year)
            set(Calendar.DAY_OF_YEAR, alarmDay)
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, min)
            set(Calendar.SECOND, 0)
        }

        //최종 셋팅
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                alarmIntent
            )
        }

        //word 목록 지우기
        dbScope.launch {
            wordDao.deleteAll()
        }

    }

    private fun alarmDateSet(): Pair<Int, Int> {

        val calendar = Calendar.getInstance()

        var year: Int = calendar.get(Calendar.YEAR)
        val currentHour: Int = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMin: Int = calendar.get(Calendar.MINUTE)
        var alarmDay: Int = calendar.get(Calendar.DAY_OF_YEAR)


        if (currentHour > hour) {   //현재시간 보다 설정시간이 작은 경우 날짜 +1
            if (alarmDay < 356) {
                alarmDay += 1
            } else {
                alarmDay = 1
                year += 1
            }
        } else if(currentHour == hour) {    //현재 시간은 같지만 현재 min보다 설정 분(min)이 작은 경우도 날짜 +1
            if(currentMin > min){
                if (alarmDay < 356) {
                    alarmDay += 1
                } else {
                    alarmDay = 1
                    year += 1
                }
            }
        }
        return Pair(year, alarmDay)
    }


}