package com.paolovalerdi.chameleon.widgets

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.checkbox.MaterialCheckBox
import com.paolovalerdi.chameleon.utils.applyAccentColor

class ChameleonCheckBox @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MaterialCheckBox(context, attrs) {

    init {
        applyAccentColor()
    }

}