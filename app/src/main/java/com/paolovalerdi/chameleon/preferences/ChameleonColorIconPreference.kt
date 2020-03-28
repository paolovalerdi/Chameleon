package com.paolovalerdi.chameleon.preferences

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.util.AttributeSet
import androidx.preference.Preference
import com.paolovalerdi.chameleon.R
import com.paolovalerdi.chameleon.utils.resolveColor

class ChameleonColorIconPreference : Preference {

    constructor(
        context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        obtainStyles(context, attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ) {
        obtainStyles(context, attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        obtainStyles(context, attrs)
    }

    constructor(context: Context?) : super(context) {
        obtainStyles(context, null)
    }

    fun tintIcon(color: Int) {
        generateIcon(icon, color)
    }

    private fun obtainStyles(context: Context?, attrs: AttributeSet?) {
        isIconSpaceReserved = true
        val a = context?.obtainStyledAttributes(
            attrs,
            R.styleable.ChameleonColorIconPreference,
            0, 0
        )
        val color = a?.getColor(
            R.styleable.ChameleonColorIconPreference_iconColor,
            context.resolveColor(R.attr.colorPrimary) ?: 0
        )
        val icon = a?.getDrawable(R.styleable.ChameleonColorIconPreference_iconRes)
        if (icon != null && color != null) {
            setIcon(generateIcon(icon, color))
        }
        a?.recycle()
    }

    private fun generateIcon(icon: Drawable, color: Int): Drawable {
        val inset = context.resources.getDimensionPixelSize(R.dimen.icon_inset)
        icon.mutate().setTint(color)
        val background =
            context.resources.getDrawable(R.drawable.preference_icon_color_background, null)
        background.mutate().setTint(color)
       background.mutate().alpha = 70
        val layers = arrayOf(background, icon)
        val finalIcon = LayerDrawable(layers)
        finalIcon.setLayerInset(1, inset, inset, inset, inset)
        return finalIcon
    }
}