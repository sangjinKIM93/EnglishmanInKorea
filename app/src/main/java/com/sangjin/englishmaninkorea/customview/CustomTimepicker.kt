package com.sangjin.englishmaninkorea.customview

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.widget.TimePicker
import java.text.SimpleDateFormat
import java.util.*

class CustomTimepicker @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : TimePicker(context, attrs, defStyleAttr) {

    fun setTimeExisting(item : String){
        //timepicker는 calendar 자료형만 받을 수 있어.
        var calendar = Calendar.getInstance()
        calendar.time = timeFormat(item)

        //초기화
        if (Build.VERSION.SDK_INT < 23) {
            this.currentHour = calendar.get(Calendar.HOUR_OF_DAY)
            this.currentMinute = calendar.get(Calendar.MINUTE)
        } else {
            this.hour = calendar.get(Calendar.HOUR_OF_DAY)
            this.minute = calendar.get(Calendar.MINUTE)
        }
    }


    private fun timeFormat(item: String): Date? {
        val itemTrimed = item.replace(" ", "")
        val format = SimpleDateFormat("hh:mm")
        val time = format.parse(itemTrimed)
        return time
    }

}