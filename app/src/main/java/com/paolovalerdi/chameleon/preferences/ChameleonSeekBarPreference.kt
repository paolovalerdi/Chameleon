package com.paolovalerdi.chameleon.preferences

import android.content.Context
import android.util.AttributeSet
import android.widget.SeekBar
import androidx.preference.PreferenceViewHolder
import androidx.preference.SeekBarPreference
import com.paolovalerdi.chameleon.R
import com.paolovalerdi.chameleon.utils.applyAccentColor

class ChameleonSeekBarPreference : SeekBarPreference {

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
        holder?.let {
            val seekbar = it.itemView.findViewById<SeekBar?>(R.id.seekbar)
            seekbar?.applyAccentColor()
        }
    }

}