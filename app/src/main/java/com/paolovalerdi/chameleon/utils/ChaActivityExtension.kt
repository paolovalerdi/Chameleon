package com.paolovalerdi.chameleon.utils

import android.app.Activity
import android.os.Build
import android.view.View
import androidx.annotation.ColorInt
import dev.jorgecastillo.androidcolorx.library.HSLAColor
import dev.jorgecastillo.androidcolorx.library.asColorInt
import dev.jorgecastillo.androidcolorx.library.asHsla


internal inline fun Activity.updateSystemUiVisibility(block: (flags: Int) -> Int) {
    window.decorView.systemUiVisibility = block(window.decorView.systemUiVisibility)
}

fun Activity.enableEdgeToEdge() {
    updateSystemUiVisibility { flags ->
        flags or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                //View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
    }
}

fun Activity.enableLightStatusBar(enabled: Boolean) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        updateSystemUiVisibility { flags ->
            if (enabled) {
                flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            }
        }
    }
}

fun Activity.setNavigationBarColor(@ColorInt color: Int) {
    val fixedColor = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
        val hsla = color.asHsla()
        HSLAColor(hsla.hue, hsla.saturation, 0.4f, hsla.alpha).asColorInt()
    } else color
    window.navigationBarColor = fixedColor
    enableLightNavigationBar(fixedColor.isLight())
}

fun Activity.enableLightNavigationBar(enabled: Boolean) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        updateSystemUiVisibility { flags ->
            if (enabled) {
                flags or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
            } else {
                flags and View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR.inv()
            }
        }
    }
}