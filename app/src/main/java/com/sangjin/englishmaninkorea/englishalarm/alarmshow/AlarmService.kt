package com.sangjin.englishmaninkorea.englishalarm.alarmshow

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.util.Log
import kotlin.concurrent.timer
import android.os.Build
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.app.NotificationCompat
import android.content.Context.NOTIFICATION_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.app.NotificationManager
import android.app.NotificationChannel
import android.content.Context
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.media.MediaPlayer
import com.sangjin.englishmaninkorea.R


class AlarmService : Service() {

    private val mediaPlayer by lazy {
        MediaPlayer.create(this, R.raw.music_sample2)
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate() {
        if (Build.VERSION.SDK_INT >= 26) {
            val CHANNEL_ID = "my_channel_01"
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(channel)

            val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("")
                .setContentText("").build()

            //오레오 이상부터는 포그라운드에서 실행하지 않으면 빨리 죽음
            startForeground(111, notification)

            mediaPlayer.start()
            Log.d("AlarmService : ", "음악이 시작됩니다..")

        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
        mediaPlayer.start()
        Log.d("AlarmService : ", "음악이 시작됩니다..")

    }

    override fun onDestroy() {
        super.onDestroy()

        mediaPlayer.stop()
        Log.d("AlarmService : ", "서비스가 파괴되었습니다.")
    }
}
