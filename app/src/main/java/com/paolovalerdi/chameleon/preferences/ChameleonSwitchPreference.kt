package com.paolovalerdi.chameleon.preferences

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.SwitchCompat
import androidx.preference.PreferenceViewHolder
import androidx.preference.SwitchPreferenceCompat
import com.paolovalerdi.chameleon.R
import com.paolovalerdi.chameleon.utils.applyAccentColor

class ChameleonSwitchPreference @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : SwitchPreferenceCompat(context, attrs, defStyleAttr, defStyleRes) {

    override fun onBindViewHolder(holder: PreferenceViewHolder?) {
        super.onBindViewHolder(holder)
        holder?.run {
            val switch = findViewById(R.id.switchWidget) as SwitchCompat?
            switch?.applyAccentColor()
        }
    }
}