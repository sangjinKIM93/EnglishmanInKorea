package com.sangjin.englishmaninkorea.customview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.sangjin.englishmaninkorea.R
import kotlinx.android.synthetic.main.custom_hint_textview.view.*

open class HintTextView @JvmOverloads constructor(context: Context, attrs: AttributeSet?=null, defStyleAttr: Int=0)
    : LinearLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.custom_hint_textview, this)
    }

    fun showHint(correctAnswer : String){
        var hintFront = correctAnswer!!.substring(0, 3)
        var hintBack = "_"
        for (i in 0..correctAnswer!!.length.minus(4)) {
            hintBack += "_"
        }
        custom_tv_hintword.text = hintFront + hintBack

        custom_tv_hintgiude.visibility = View.VISIBLE
        custom_tv_hintword.visibility = View.VISIBLE
    }

    fun hideHinT(){
        custom_tv_hintgiude.visibility = View.INVISIBLE
        custom_tv_hintword.visibility = View.INVISIBLE
    }

}