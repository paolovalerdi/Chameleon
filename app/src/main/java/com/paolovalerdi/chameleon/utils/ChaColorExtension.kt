package com.paolovalerdi.chameleon.utils

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange
import androidx.core.graphics.ColorUtils
import androidx.core.math.MathUtils.clamp
import dev.jorgecastillo.androidcolorx.library.isDark
import dev.jorgecastillo.androidcolorx.library.lighten

@ColorInt
fun Int.withAlpha(@FloatRange(from = 0.0, to = 1.0) alpha: Float): Int {
    val a = Math.min(255, Math.max(0, (alpha * 255).toInt())) shl 24
    val rgb = 0x00ffffff and this
    return a + rgb
}

fun Int.isLight() = isDark().not()

fun getPrimaryTextColor(isDark: Boolean) = if (isDark) Color.WHITE else Color.BLACK

fun getSecondaryTextColor(isDark: Boolean) =
    if (isDark) Color.WHITE.withAlpha(ALPHA_MEDIUM) else Color.BLACK.withAlpha(ALPHA_MEDIUM)

@ColorInt
fun Int.desaturate(amount: Float, minDesaturation: Float): Int {
    val originalAlpha = Color.alpha(this)
    if (this == Color.TRANSPARENT || originalAlpha == 0) {
        return this
    }
    val colorWithFullAlpha = ColorUtils.setAlphaComponent(this, 255)
    val hsl = FloatArray(3)
    ColorUtils.colorToHSL(colorWithFullAlpha, hsl)
    if (hsl[1] > minDesaturation) {
        hsl[1] = clamp(
            hsl[1] - amount,
            minDesaturation,
            1f
        )
    }
    val desaturatedColorWithFullAlpha = ColorUtils.HSLToColor(hsl)
    return ColorUtils.setAlphaComponent(desaturatedColorWithFullAlpha, originalAlpha)
}