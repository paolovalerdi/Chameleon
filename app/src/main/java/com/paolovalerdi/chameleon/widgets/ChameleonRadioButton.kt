package com.paolovalerdi.chameleon.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatRadioButton
import com.paolovalerdi.chameleon.utils.applyAccentColor

class ChameleonRadioButton : AppCompatRadioButton {

    constructor(context: Context?) : super(context) {
        applyAccentColor()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        applyAccentColor()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ) {
        applyAccentColor()
    }

}