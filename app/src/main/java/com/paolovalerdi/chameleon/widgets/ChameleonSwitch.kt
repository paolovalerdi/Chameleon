package com.paolovalerdi.chameleon.widgets

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.switchmaterial.SwitchMaterial
import com.paolovalerdi.chameleon.utils.applyAccentColor

class ChameleonSwitch @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : SwitchMaterial(context, attrs) {

    init {
        applyAccentColor()
    }

}