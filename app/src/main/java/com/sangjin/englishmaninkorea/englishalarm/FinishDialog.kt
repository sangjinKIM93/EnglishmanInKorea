package com.sangjin.englishmaninkorea.englishalarm

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.Button
import com.sangjin.englishmaninkorea.R

class FinishDialog(context : Context){

    private val dialog = Dialog(context)
    var clickListener: ((Int) -> Unit) ?= null

    fun start(){
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_success)
        dialog.setCancelable(false)
        dialog.window!!.attributes.windowAnimations = R.style.DialogAnimation
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val btnFinishApp = dialog.findViewById<Button>(R.id.btn_finish_app)
        btnFinishApp.setOnClickListener {

            clickListener?.invoke(1)
            dialog.dismiss()
        }

        dialog.show()


    }
}