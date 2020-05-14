package com.paolovalerdi.chameleon.preferences

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.preference.Preference
import androidx.preference.PreferenceViewHolder
import com.paolovalerdi.chameleon.R
import com.paolovalerdi.chameleon.utils.ThemeManager

class ChameleonColorPreference(
    context: Context? = null,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) :
    Preference(context, attrs, defStyleAttr, defStyleRes) {

    init {
        widgetLayoutResource = R.layout.preference_color
    }

    override fun onBindViewHolder(holder: PreferenceViewHolder?) {
        super.onBindViewHolder(holder)
        holder?.let {
            val colorView = it.itemView.findViewById<View?>(R.id.colorDrawable)
            colorView?.background?.mutate()?.setTint(ThemeManager(it.itemView.context).accentColor)
        }
    }
}