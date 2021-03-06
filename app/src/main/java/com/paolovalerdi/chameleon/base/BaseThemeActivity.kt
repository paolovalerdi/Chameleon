package com.paolovalerdi.chameleon.base

import android.os.Bundle
import android.os.Handler
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.paolovalerdi.chameleon.R
import com.paolovalerdi.chameleon.utils.ThemeKey
import com.paolovalerdi.chameleon.utils.ThemeManager
import com.paolovalerdi.chameleon.utils.actualNightMode
import com.paolovalerdi.chameleon.utils.currentNightMode

abstract class BaseThemeActivity : AppCompatActivity() {

    private var lastTheme = ThemeKey.FOLLOW_SYSTEM
    private var usingAmoledTheme = false
    private var _themeManager: ThemeManager? = null
    val themeManager get() = _themeManager!!

    open val defaultTheme = R.style.Theme_Base_Chameleon_SplashScreenTheme
    open val amoledTheme = R.style.Theme_Base_Chameleon_SplashScreenTheme_Amoled

    override fun onCreate(savedInstanceState: Bundle?) {
        _themeManager = ThemeManager(this)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        AppCompatDelegate.setDefaultNightMode(actualNightMode)
        setCustomTheme()
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        if (
            lastTheme != themeManager.currentTheme ||
            currentNightMode != themeManager.lastNightMode ||
            usingAmoledTheme != themeManager.usesAmoledTheme
        ) {
            themeManager.lastNightMode = currentNightMode
            onThemeChanged()
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        themeManager.let {
            lastTheme = it.currentTheme
            usingAmoledTheme = it.usesAmoledTheme
        }
    }

    fun updateAccentColor(@ColorInt color: Int) {
        themeManager.accentColor = color
        onThemeChanged()
    }

    fun updateShouldUseDesaturatedColor(shouldUse: Boolean) {
        themeManager.isAccentColorDesaturated = shouldUse
        onThemeChanged()
    }

    fun onThemeChanged() {
        Handler().post { recreate() }
    }

    private fun setCustomTheme() {
        setTheme(if (themeManager.usesAmoledTheme) amoledTheme else defaultTheme)
    }
}