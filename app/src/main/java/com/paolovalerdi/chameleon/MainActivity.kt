package com.paolovalerdi.chameleon

import android.os.Bundle
import com.paolovalerdi.chameleon.base.BaseThemeActivity
import com.paolovalerdi.chameleon.utils.ThemeKey

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseThemeActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        light.setOnClickListener {
            themeManager.currentTheme = ThemeKey.LIGHT
            onThemeChanged()
        }
        dark.setOnClickListener {
            themeManager.currentTheme = ThemeKey.DARK
            onThemeChanged()
        }
        follow_system.setOnClickListener {
            themeManager.currentTheme = ThemeKey.FOLLOW_SYSTEM
            onThemeChanged()
        }
        amoled.isChecked = themeManager.usesAmoledTheme
        amoled.setOnCheckedChangeListener { buttonView, isChecked ->
            themeManager.usesAmoledTheme = isChecked
            onThemeChanged()
        }
        accent.setBackgroundColor(themeManager.accentColor)
    }

}
