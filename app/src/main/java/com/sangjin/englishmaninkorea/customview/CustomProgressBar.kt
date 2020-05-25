package com.sangjin.englishmaninkorea.customview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.LinearLayout
import com.sangjin.englishmaninkorea.R
import kotlinx.android.synthetic.main.custom_progressbar.view.*

open class CustomProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.custom_progressbar, this)
    }

    fun settingProgress(correctNum: Int, totalNum: Int) {

        if (totalNum > 0) {

            //progressbar 진행상황 표시
            val percentage = (correctNum * 100) / totalNum
            custom_progressbar.progress = percentage

            //progressbar 아래 진행 상황 숫자로 나타내기
            TV_seekBar.text = "${correctNum}/${totalNum}"

            //텍스트 위치 계산 및 표시
            val xPosition =
                (((custom_progressbar.right - custom_progressbar.left) / custom_progressbar.max) * custom_progressbar.progress) + custom_progressbar.left
            TV_seekBar.translationX = xPosition.toFloat() - (TV_seekBar.width / 2)

        }
    }

}