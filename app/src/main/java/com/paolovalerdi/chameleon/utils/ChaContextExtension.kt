package com.paolovalerdi.chameleon.utils

import android.content.Context
import android.content.res.Configuration
import androidx.annotation.AttrRes
import androidx.appcompat.app.AppCompatDelegate
import com.paolovalerdi.chameleon.R

fun Context.resolveColor(@AttrRes attr: Int, fallback: Int = 0): Int {
    val a = theme.obtainStyledAttributes(intArrayOf(attr))
    try {
        return a.getColor(0, fallback)
    } finally {
        a.recycle()
    }
}

fun Context.getBackgroundColor() = resolveColor(android.R.attr.colorBackground)

fun Context.getColorSurface() = resolveColor(R.attr.colorSurface)

fun Context.getColorControlNormal() = resolveColor(R.attr.colorControlNormal)

fun Context.getPrimaryTextColor() = resolveColor(android.R.attr.textColorPrimary)

fun Context.getSecondaryTextColor() = resolveColor(android.R.attr.textColorPrimary)

val Context.currentNightMode: Int
    get() = try {
        resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
    } catch (e: Exception) {
        Configuration.UI_MODE_NIGHT_UNDEFINED
    }

val Context.actualNightMode: Int
    get() = when (ThemeManager(this).currentTheme) {
        ThemeKey.LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
        ThemeKey.DARK -> AppCompatDelegate.MODE_NIGHT_YES
        else -> AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY or AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
    }
