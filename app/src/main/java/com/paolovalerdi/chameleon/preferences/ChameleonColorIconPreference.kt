package com.paolovalerdi.chameleon.preferences

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.util.AttributeSet
import android.widget.ImageView
import androidx.core.view.updateLayoutParams
import androidx.preference.Preference
import androidx.preference.PreferenceViewHolder
import com.paolovalerdi.chameleon.R
import com.paolovalerdi.chameleon.utils.desaturate
import com.paolovalerdi.chameleon.utils.resolveColor
import dev.jorgecastillo.androidcolorx.library.isDark
import dev.jorgecastillo.androidcolorx.library.lighten

class ChameleonColorIconPreference @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0
) : Preference(context, attrs, defStyleAttr, defStyleRes) {
    init {
        obtainStyles(context, attrs)
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
            context.resolveColor(R.attr.colorPrimary)
        )
        val icon = a?.getDrawable(R.styleable.ChameleonColorIconPreference_iconRes)
        if (icon != null && color != null) {
            setIcon(generateIcon(icon, color))
        }
        a?.recycle()
    }

    private fun generateIcon(icon: Drawable, color: Int): Drawable {
        val fixedColor = if (context.resolveColor(R.attr.colorSurface).isDark()) {
            color.desaturate(.40f, 0.3f).lighten(0.10f)
        } else color
        val inset = context.resources.getDimensionPixelSize(R.dimen.icon_inset)
        icon.mutate().setTint(fixedColor)
        val background = context.resources.getDrawable(
            R.drawable.preference_icon_color_background,
            null
        ).apply {
            mutate().setTint(fixedColor)
            alpha = 70
        }
        val layers = arrayOf(background, icon)
        val finalIcon = LayerDrawable(layers)
        finalIcon.setLayerInset(1, inset, inset, inset, inset)
        return finalIcon
    }

    override fun onBindViewHolder(holder: PreferenceViewHolder?) {
        super.onBindViewHolder(holder)
        val image = holder?.itemView?.findViewById<ImageView>(android.R.id.icon)
        image?.updateLayoutParams {
            height = holder.itemView.resources?.getDimensionPixelSize(R.dimen.icon_size) ?: 0
            width = holder.itemView.resources?.getDimensionPixelSize(R.dimen.icon_size) ?: 0
        }
    }

}