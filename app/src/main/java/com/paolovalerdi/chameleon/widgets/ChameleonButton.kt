package com.paolovalerdi.chameleon.widgets

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.button.MaterialButton
import com.paolovalerdi.chameleon.utils.applyAccentColor

class ChameleonButton : MaterialButton {

    constructor(context: Context) : super(context) {
        applyAccentColor()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        applyAccentColor()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ) {
        applyAccentColor()
    }
}