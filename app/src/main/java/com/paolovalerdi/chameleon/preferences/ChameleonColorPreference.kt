package com.paolovalerdi.chameleon.preferences

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.preference.Preference
import androidx.preference.PreferenceViewHolder
import com.paolovalerdi.chameleon.R
import com.paolovalerdi.chameleon.utils.ThemeManager

class ChameleonColorPreference : Preference {

    constructor(
        context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        widgetLayoutResource = R.layout.preference_color
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ) {
        widgetLayoutResource = R.layout.preference_color
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        widgetLayoutResource = R.layout.preference_color
    }

    constructor(context: Context?) : super(context) {
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