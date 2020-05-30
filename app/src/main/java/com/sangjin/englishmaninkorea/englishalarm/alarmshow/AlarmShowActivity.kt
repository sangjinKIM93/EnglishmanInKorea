package com.sangjin.englishmaninkorea.englishalarm.alarmshow

import android.content.Intent
import android.media.AudioAttributes
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sangjin.englishmaninkorea.R
import com.sangjin.englishmaninkorea.englishalarm.FinishDialog
import kotlinx.android.synthetic.main.activity_alarm_show.*


class AlarmShowActivity : AppCompatActivity() {

    private lateinit var viewModel: AlarmShowViewModel

    private var percentage: Int = 0

    private val ringtone by lazy {
        val uri = RingtoneManager.getActualDefaultRingtoneUri(application.baseContext, RingtoneManager.TYPE_ALARM)
        RingtoneManager.getRingtone(this, uri)
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm_show)

        //activity를 잠금화면 위에 띄우기
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED)
        window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD)

        //ringtone으로 벨소리 설정
        val audioAttributes =
            AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ALARM)
                .build()

        ringtone.audioAttributes = audioAttributes
        ringtone.play()

        viewModel =  ViewModelProviders.of(this).get(AlarmShowViewModel::class.java)

        //퀴즈 단어 출력
        viewModel.quizWord.observe(this, Observer {word ->
            questionWordTV.text = word
        })

        //맞힌 갯수 출력
//        viewModel.numberOfCorrect.observe(this, Observer {numOfCorrect ->
//
//        })

        //퀴즈가 종료되었는지 여부 확인
        viewModel.gameFinished.observe(this, Observer {gameFinished ->
            quizFinished(gameFinished)
        })


        answerBtn.setOnClickListener{
            checkCorrect()
        }


        passBtn.setOnClickListener{
            showNextword()
        }

    }

    //모든 문제가 끝나고 퀴즈가 종료되었을때
    private fun quizFinished(gameFinished: Boolean?) {
        if (gameFinished == true) {

            ringtone.stop()

            //종료 dialog 출력
            val dialog = FinishDialog(this)
            dialog.clickListener = {
                if (it == 1) {
                    //해당 알람 complete update 하기
                    viewModel.updateAlarmCompleted()
                    finish()
                }
            }
            dialog.start()
        }
    }


    //올바른 답을 적었는지 확인 후 틀렸을 경우 hint 출력
    private fun checkCorrect() {
        var correctAnswer = viewModel.answer.value.toString()

        if (correctAnswer.equals(answerET.text.toString())) {
            showNextword()
        } else {
            tv_hint.showHint(correctAnswer)
        }

        answerET.text.clear()
    }


    private fun showNextword() {
        viewModel.nextWord()

        progressbar.settingProgress(viewModel.numberOfCorrect, viewModel.numberOfWord)

        tv_hint.hideHinT()
    }

    override fun onDestroy() {
        ringtone.stop()
        super.onDestroy()
    }

    override fun onBackPressed() {
//        super.onBackPressed()
    }
}
