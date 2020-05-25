package com.sangjin.englishmaninkorea.englishalarm.alarmshow

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.PowerManager


class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        val wakeLock = powerManager.newWakeLock(
            PowerManager.SCREEN_DIM_WAKE_LOCK or
                    PowerManager.ACQUIRE_CAUSES_WAKEUP or
                    PowerManager.ON_AFTER_RELEASE, "myapp:englishManInKorea"
        )
        wakeLock.acquire(5000)  //설정한 time 이후에 자동으로 release

        val intent = Intent(context, AlarmShowActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)


    }
}

