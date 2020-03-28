package com.paolovalerdi.chameleon.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatSeekBar
import com.paolovalerdi.chameleon.utils.applyAccentColor

class ChameleonSeekBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatSeekBar(context, attrs) {

    init {
        applyAccentColor()
    }

}