package com.paolovalerdi.chameleon.utils

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange
import androidx.appcompat.widget.AppCompatSeekBar
import androidx.core.graphics.ColorUtils
import androidx.core.widget.CompoundButtonCompat
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.switchmaterial.SwitchMaterial
import com.paolovalerdi.chameleon.R

const val ALPHA_FULL = 1.00f
const val ALPHA_MEDIUM = 0.54f
const val ALPHA_DISABLED = 0.38f
const val ALPHA_LOW = 0.32f
const val ALPHA_DISABLED_LOW = 0.12f

fun layer(
    @ColorInt backgroundColor: Int,
    @ColorInt overlayColor: Int,
    @FloatRange(from = 0.0, to = 1.0) overlayAlpha: Float
): Int {
    val computedAlpha =
        Math.round(Color.alpha(overlayColor) * overlayAlpha)
    val computedOverlayColor = ColorUtils.setAlphaComponent(overlayColor, computedAlpha)
    return layer(backgroundColor, computedOverlayColor)
}

fun layer(@ColorInt backgroundColor: Int, @ColorInt overlayColor: Int): Int {
    return ColorUtils.compositeColors(overlayColor, backgroundColor)
}

fun AppCompatSeekBar.applyAccentColor() {
    val accentColor = ThemeManager(context).accentColor
    thumbTintList = ColorStateList.valueOf(accentColor)
    progressTintList = ColorStateList.valueOf(accentColor)
}

fun MaterialCheckBox.applyAccentColor() {
    val states = arrayOf(
        intArrayOf(android.R.attr.state_enabled, android.R.attr.state_checked),
        intArrayOf(android.R.attr.state_enabled, -android.R.attr.state_checked),
        intArrayOf(-android.R.attr.state_enabled, android.R.attr.state_checked),
        intArrayOf(-android.R.attr.state_enabled, -android.R.attr.state_checked)
    )
    val accentColor = ThemeManager(context).accentColor
    val colorSurface = context.resolveColor(R.attr.colorSurface)
    val colorOnSurface = context.resolveColor(R.attr.colorOnSurface)
    val colorStateList = ColorStateList(
        states,
        intArrayOf(
            layer(colorSurface, accentColor, ALPHA_FULL),
            layer(colorSurface, colorOnSurface, ALPHA_MEDIUM),
            layer(colorSurface, colorOnSurface, ALPHA_DISABLED),
            layer(colorSurface, colorOnSurface, ALPHA_DISABLED)
        )
    )
    CompoundButtonCompat.setButtonTintList(this, colorStateList)
}

fun SwitchMaterial.applyAccentColor() {
    val states = arrayOf(
        intArrayOf(android.R.attr.state_enabled, android.R.attr.state_checked),
        intArrayOf(android.R.attr.state_enabled, -android.R.attr.state_checked),
        intArrayOf(-android.R.attr.state_enabled, android.R.attr.state_checked),
        intArrayOf(-android.R.attr.state_enabled, -android.R.attr.state_checked)
    )
    val accentColor = ThemeManager(context).accentColor
    val colorSurface = context.resolveColor(R.attr.colorSurface)
    val colorOnSurface = context.resolveColor(R.attr.colorOnSurface)
    val thumbColorStateList = ColorStateList(
        states,
        intArrayOf(
            layer(colorSurface, accentColor, ALPHA_FULL),
            colorSurface,
            layer(colorSurface, accentColor, ALPHA_DISABLED),
            colorSurface
        )
    )
    val trackColorSwitchMaterial = ColorStateList(
        states,
        intArrayOf(
            layer(colorSurface, accentColor, ALPHA_MEDIUM),
            layer(colorSurface, colorOnSurface, ALPHA_LOW),
            layer(colorSurface, accentColor, ALPHA_DISABLED_LOW),
            layer(colorSurface, colorOnSurface, ALPHA_DISABLED_LOW)
        )
    )
    thumbTintList = thumbColorStateList
    trackTintList = trackColorSwitchMaterial
}