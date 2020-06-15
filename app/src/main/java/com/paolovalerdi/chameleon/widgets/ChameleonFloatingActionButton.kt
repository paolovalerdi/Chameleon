package com.paolovalerdi.chameleon.widgets

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.paolovalerdi.chameleon.utils.applyAccentColor
import com.paolovalerdi.chameleon.utils.getPrimaryTextColor
import com.paolovalerdi.chameleon.utils.isLight
import dev.jorgecastillo.androidcolorx.library.isDark

open class ChameleonFloatingActionButton : FloatingActionButton {

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

    open fun updateTintFor(color: Int) {
        drawable?.setTint(getPrimaryTextColor(color.isDark()))
    }
}