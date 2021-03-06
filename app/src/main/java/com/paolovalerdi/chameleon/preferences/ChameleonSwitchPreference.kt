package com.paolovalerdi.chameleon.preferences

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.SwitchCompat
import androidx.preference.PreferenceViewHolder
import androidx.preference.SwitchPreferenceCompat
import com.paolovalerdi.chameleon.R
import com.paolovalerdi.chameleon.utils.applyAccentColor

class ChameleonSwitchPreference : SwitchPreferenceCompat {
    constructor(
        context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?) : super(context)


    override fun onBindViewHolder(holder: PreferenceViewHolder?) {
        super.onBindViewHolder(holder)
        holder?.run {
            val switch = findViewById(R.id.switchWidget) as SwitchCompat?
            switch?.applyAccentColor()
        }
    }
}