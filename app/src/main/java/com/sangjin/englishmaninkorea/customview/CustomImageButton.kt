package com.sangjin.englishmaninkorea.customview

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.sangjin.englishmaninkorea.R
import kotlinx.android.synthetic.main.custom_imagebutton.view.*

open class CustomImageButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        initView()
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImageButton, defStyleAttr, 0)
        setTypedArray(typedArray)
    }

    private fun initView() {
        inflate(context, R.layout.custom_imagebutton, this)
    }


    private fun setTypedArray(typedArray: TypedArray) {

        val bg_resId = typedArray.getColor(R.styleable.ImageButton_bg, 0)
        bg.setBackgroundColor(bg_resId)

        val symbol_resID = typedArray.getResourceId(R.styleable.ImageButton_symbol, R.drawable.ic_add_white)
        symbol.setBackgroundResource(symbol_resID)

//        val symbolSize = typedArray.getDimension(R.styleable.ImageButton_symbolSize, 0F)
//        symbolSize.(symbolSize)

        val textString = typedArray.getString(R.styleable.ImageButton_text)
        text.setText(textString)

        typedArray.recycle()
    }

}
