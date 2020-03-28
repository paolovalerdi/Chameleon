package com.paolovalerdi.chameleon.utils

import androidx.annotation.StringRes
import com.paolovalerdi.chameleon.R

enum class ThemeKey(val value: Int, @StringRes val stringResId: Int) {
    LIGHT(0, R.string.light_theme),
    DARK(1, R.string.dark_theme),
    FOLLOW_SYSTEM(2, R.string.follow_system);

    companion object {
        fun fromValue(value: Int): ThemeKey = when (value) {
            0 -> LIGHT
            1 -> DARK
            else -> FOLLOW_SYSTEM
        }
    }
}