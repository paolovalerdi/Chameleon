package com.paolovalerdi.chameleon.widgets

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.paolovalerdi.chameleon.utils.applyAccentColor

class ChameleonBottomNavigationView : BottomNavigationView {

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