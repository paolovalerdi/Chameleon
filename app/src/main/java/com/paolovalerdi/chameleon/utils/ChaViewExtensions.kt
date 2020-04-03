package com.paolovalerdi.chameleon.utils

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import android.view.animation.Interpolator
import android.view.animation.PathInterpolator
import android.widget.CompoundButton
import android.widget.SeekBar
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.SwitchCompat
import androidx.core.animation.doOnStart
import androidx.core.graphics.ColorUtils
import androidx.core.widget.CompoundButtonCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.paolovalerdi.chameleon.R
import dev.jorgecastillo.androidcolorx.library.isDark

const val ALPHA_FULL = 1.00f
const val ALPHA_MEDIUM = 0.54f
const val ALPHA_DISABLED = 0.38f
const val ALPHA_LOW = 0.32f
const val ALPHA_DISABLED_LOW = 0.12f

private const val ANIMATION_DURATION = 250L

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

fun MaterialButton.applyColor(@ColorInt color: Int, outline: Boolean = false) {
    if (outline) {
        strokeColor = ColorStateList.valueOf(color)
        rippleColor = ColorStateList.valueOf(color)
        setTextColor(color)
    } else {
        val textColor = getPrimaryTextColor(color.isDark())
        backgroundTintList = ColorStateList.valueOf(color)
        rippleColor = ColorStateList.valueOf(color)
        setTextColor(textColor)
    }
}

fun SeekBar.applyColor(@ColorInt color: Int) {
    thumbTintList = ColorStateList.valueOf(color)
    progressTintList = ColorStateList.valueOf(color)
}

@SuppressLint("ObjectAnimatorBinding")
fun SeekBar.colorTransition(@ColorInt from: Int, @ColorInt to: Int) {
    ObjectAnimator.ofArgb(this, "thumbTint", from, to).apply {
        duration = ANIMATION_DURATION
        setEvaluator(ArgbEvaluator())
        interpolator = PathInterpolator(0.4f, 0f, 1f, 1f)
        addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Int
            applyColor(animatedValue)
        }
    }.start()
}

@SuppressLint("ObjectAnimatorBinding")
fun FloatingActionButton.colorTransition(@ColorInt from: Int, @ColorInt to: Int) {
    ObjectAnimator.ofArgb(this, "backgroundTintList", from, to).apply {
        duration = ANIMATION_DURATION
        setEvaluator(ArgbEvaluator())
        interpolator = PathInterpolator(0.4f, 0f, 1f, 1f)
        addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Int
            backgroundTintList = ColorStateList.valueOf(animatedValue)
        }
        doOnStart {
            drawable?.run {
                setColorFilter(getPrimaryTextColor(to.isDark()))
            }
        }
    }.start()
}

fun AppCompatTextView.colorTransition(@ColorInt from: Int, @ColorInt to: Int) {
    ObjectAnimator.ofArgb(this, "textColor", from, to).apply {
        duration = ANIMATION_DURATION
        interpolator = PathInterpolator(0.4f, 0f, 1f, 1f)
    }.start()
}

fun View.backgroundColorTransition(
    @ColorInt from: Int,
    @ColorInt to: Int,
    animInterpolator: Interpolator = PathInterpolator(0.4f, 0f, 1f, 1f)
) {
    ObjectAnimator.ofArgb(this, "backgroundColor", from, to).apply {
        duration = ANIMATION_DURATION
        interpolator = animInterpolator
    }.start()
}

internal fun SeekBar.applyAccentColor() {
    applyColor(ThemeManager(context).accentColor)
}

internal fun MaterialButton.applyAccentColor() {
    applyColor(ThemeManager(context).accentColor)
}

internal fun CompoundButton.applyAccentColor() {
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

internal fun FloatingActionButton.applyAccentColor() {
    val accentColor = ThemeManager(context).accentColor
    val iconColor = if (accentColor.isDark()) Color.WHITE else Color.BLACK
    backgroundTintList = ColorStateList.valueOf(accentColor)
    setRippleColor(ColorStateList.valueOf(accentColor))
    drawable?.mutate()?.setTint(iconColor)
}

internal fun BottomNavigationView.applyAccentColor() {
    val color = ColorStateList(
        arrayOf(
            intArrayOf(-android.R.attr.state_checked),
            intArrayOf(android.R.attr.state_checked)
        ),
        intArrayOf(
            context.getColorControlNormal(),
            ThemeManager(context).accentColor
        )
    )
    itemIconTintList = color
    itemTextColor = color
    itemRippleColor = ColorStateList.valueOf(ThemeManager(context).accentColor.withAlpha(0.10f))
}

internal fun SwitchCompat.applyAccentColor() {
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
            layer(colorSurface, colorOnSurface, ALPHA_MEDIUM),
            layer(colorSurface, accentColor, ALPHA_DISABLED),
            layer(colorSurface, colorOnSurface, ALPHA_MEDIUM)
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