package com.paolovalerdi.chameleon.preferences

import android.content.Context
import android.util.AttributeSet
import android.widget.SeekBar
import androidx.preference.PreferenceViewHolder
import androidx.preference.SeekBarPreference
import com.paolovalerdi.chameleon.R
import com.paolovalerdi.chameleon.utils.applyAccentColor

class ChameleonSeekBarPreference @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : SeekBarPreference(context, attrs, defStyleAttr, defStyleRes) {

    override fun onBindViewHolder(holder: PreferenceViewHolder?) {
        super.onBindViewHolder(holder)
        holder?.let {
            val seekbar = it.itemView.findViewById<SeekBar?>(R.id.seekbar)
            seekbar?.applyAccentColor()
        }
    }

}