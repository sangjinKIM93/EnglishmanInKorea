package com.sangjin.englishmaninkorea.customview

import android.annotation.SuppressLint
import android.content.Context
import android.text.InputFilter
import android.util.AttributeSet
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText

open class OnlyAlphabetEditText : AppCompatEditText {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        onlyAlphabetFilterToEnglishET()
    }

    private fun onlyAlphabetFilterToEnglishET() {
        this.setFilters(arrayOf(
            InputFilter { src, start, end, dst, dstart, dend ->
                if (src == " ") { // for space
                    return@InputFilter src
                }
                if (src == "") { // for backspace
                    return@InputFilter src
                }
                if (src.matches(Regex("[a-zA-Z]+"))) {
                    return@InputFilter src
                }
                Toast.makeText(context, "영단어를 입력해주세요", Toast.LENGTH_LONG).show()
                this.setText("")
                return@InputFilter ""

            }
        ))
    }
}