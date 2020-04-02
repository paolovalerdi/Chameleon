package com.paolovalerdi.chameleon.utils

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import androidx.annotation.ColorInt

fun Activity.enableEdgeToEdge() {
    window.decorView.systemUiVisibility =
        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
}

fun Activity.enableLightStatusBar(enabled: Boolean) {
    var flags = window.decorView.systemUiVisibility
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        flags = if (enabled) {
            flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        }
        window.decorView.systemUiVisibility = flags
    }
}

fun Activity.setNavigationBarColor(@ColorInt color: Int) {
    val fixedColor = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        color
    } else Color.BLACK
    window.navigationBarColor = fixedColor
    enableLightNavigationBar(fixedColor.isLight())
}

fun Activity.enableLightNavigationBar(enabled: Boolean) {
    var flags = window.decorView.systemUiVisibility
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        flags = if (enabled) {
            flags or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        } else {
            flags and View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR.inv()
        }
        window.decorView.systemUiVisibility = flags
    }
}