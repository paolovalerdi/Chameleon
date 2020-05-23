package com.paolovalerdi.chameleon.preferences

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.preference.PreferenceCategory
import androidx.preference.PreferenceViewHolder
import com.paolovalerdi.chameleon.utils.ThemeManager

class ChameleonPreferenceCategory @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : PreferenceCategory(context, attrs, defStyleAttr, defStyleRes) {

    override fun onBindViewHolder(holder: PreferenceViewHolder?) {
        super.onBindViewHolder(holder)
        holder?.run {
            val title = findViewById(android.R.id.title) as TextView?
            title?.setTextColor(ThemeManager(context).accentColor)
        }
    }
}