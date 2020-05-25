package com.sangjin.englishmaninkorea.englishalarm.alarmshow

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sangjin.englishmaninkorea.englishalarm.data.Alarm
import com.sangjin.englishmaninkorea.database.DB
import com.sangjin.englishmaninkorea.englishalarm.data.Word
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlarmShowViewModel(application: Application) : AndroidViewModel(application) {

    //DB에서 리스트 받아오기
    private var alarmDao = DB.getInstance(application).alarmDao
    private lateinit var wordList: MutableList<Word>

    private var _quizWord = MutableLiveData<String>()
    val quizWord = _quizWord

    private var _answer = MutableLiveData<String>()
    val answer = _answer

    private var _gameFinished = MutableLiveData<Boolean>()
    val gameFinished = _gameFinished

    var numberOfWord: Int = 0
    var numberOfCorrect: Int = 0


    private val dbScope = CoroutineScope(Dispatchers.IO)


    init {
        CoroutineScope(Dispatchers.Main).launch {
            dbScope.launch {
                wordList = alarmDao.getLastAlarm().wordList as MutableList<Word>
            }.join()            //이후 nextWordk를 위해서 그전에 wordList가 초기화 되어야하기 때문에 join을 걸어줘야함.

            Log.d("AlarmShowViewModel : ", "wordList 정보 : $wordList")
            //처음 단어 초기화
            _quizWord.value = wordList.get(0).wordMeaning
            _answer.value = wordList.get(0).englishWord

            //percentage를 표현하기 위한 전체 길이
            numberOfWord = wordList.size
        }

        _gameFinished.value = false

    }


    fun nextWord() {

        if(wordList.size > 1){
            wordList.removeAt(0)

            _quizWord.value = wordList.get(0).wordMeaning
            _answer.value = wordList.get(0).englishWord

            numberOfCorrect += 1    //맞춘 갯수 올려주기
        } else {
            numberOfCorrect += 1    //맞춘 갯수 올려주기
            _gameFinished.value = true
        }

    }


    fun updateAlarmCompleted() {

        var alarm: Alarm? = null

        CoroutineScope(Dispatchers.Main).launch {
            dbScope.launch {
                alarm = alarmDao.getLastAlarm()
                alarm?.completed = 1

            }.join()

            dbScope.launch {
                alarmDao.update(alarm!!)
            }
        }
    }


}