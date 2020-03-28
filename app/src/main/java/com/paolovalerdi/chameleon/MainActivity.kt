package com.paolovalerdi.chameleon

import android.graphics.Color
import android.os.Bundle
import com.paolovalerdi.chameleon.base.BaseThemeActivity
import com.paolovalerdi.chameleon.utils.ThemeKey
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : BaseThemeActivity() {

    private val colors = listOf(
        "#ff1744", "#651fff", "#2979ff", "#ff9100", "#dd2c00", "#795548"
    )

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
        accentSwitcher.setOnClickListener {
            updateAccentColor(Color.parseColor(colors[Random.nextInt(0, 5)]))
        }
        amoled.isChecked = themeManager.usesAmoledTheme
        amoled.setOnCheckedChangeListener { buttonView, isChecked ->
            themeManager.usesAmoledTheme = isChecked
            onThemeChanged()
        }
        saturate.isChecked = themeManager.isAccentColorDesaturated
        saturate.setOnCheckedChangeListener { buttonView, isChecked ->
            updateShouldUseDesaturatedColor(isChecked)
        }
        accent.setBackgroundColor(themeManager.accentColor)
    }

}
