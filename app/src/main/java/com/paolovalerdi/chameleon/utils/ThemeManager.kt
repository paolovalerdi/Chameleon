package com.paolovalerdi.chameleon.utils

import android.content.Context
import android.graphics.Color
import dev.jorgecastillo.androidcolorx.library.isDark
import dev.jorgecastillo.androidcolorx.library.lighten

class ThemeManager(private val context: Context) {

    companion object {
        private const val PREFERENCES_NAME = "cham_prefs"
        private const val CURRENT_THEME = "current_theme"
        private const val LAST_NIGHT_MODE = "last_night_mode"
        private const val USES_AMOLED_THEME = "uses_amoled_theme"
        private const val ACCENT_COLOR = "theme_accent_color"
        private const val DESATURATED_ACCENT_COLOR = "desaturated_accent_color"
    }

    private val prefs =
        context.applicationContext.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    private val prefsEditor = prefs.edit()

    var currentTheme: ThemeKey
        get() = ThemeKey.fromValue(prefs.getInt(CURRENT_THEME, ThemeKey.FOLLOW_SYSTEM.value))
        set(value) = prefsEditor.putInt(CURRENT_THEME, value.value).apply()

    var lastNightMode: Int
        get() = prefs.getInt(LAST_NIGHT_MODE, context.currentNightMode)
        set(value) = prefsEditor.putInt(LAST_NIGHT_MODE, value).apply()

    var usesAmoledTheme: Boolean
        get() = prefs.getBoolean(USES_AMOLED_THEME, false)
        set(value) = prefsEditor.putBoolean(USES_AMOLED_THEME, value).apply()

    var accentColor: Int
        get() = if (isAccentColorDesaturated && context.getBackgroundColor().isDark()) {
            val color = prefs.getInt(ACCENT_COLOR, Color.parseColor("#2979ff"))
            color.desaturate(0.35f, 0.65f).lighten(.12f)
        } else prefs.getInt(ACCENT_COLOR, Color.parseColor("#2979ff"))
        set(value) = prefsEditor.putInt(ACCENT_COLOR, value).apply()

    var isAccentColorDesaturated: Boolean
        get() = prefs.getBoolean(DESATURATED_ACCENT_COLOR, true)
        set(value) = prefsEditor.putBoolean(DESATURATED_ACCENT_COLOR, value).apply()

}