package com.sangjin.englishmaninkorea.englishalarm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sangjin.englishmaninkorea.R
import kotlinx.android.synthetic.main.activity_alarm_result.*

class AlarmResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm_result)


        val dialog = FinishDialog(this)
        dialog.clickListener = {
            if(it == 1){
                finish()
            }
        }
        dialog.start()
    }


}
