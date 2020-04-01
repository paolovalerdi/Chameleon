package com.paolovalerdi.chameleon.utils

import android.app.Activity
import android.content.Intent

inline fun Activity.restart(intentBuilder: Intent.() -> Unit = {}) {
    val i = Intent(this, this::class.java)
    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    i.addCategory(Intent.CATEGORY_HOME)
    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    intent?.extras?.let { i.putExtras(it) }
    i.intentBuilder()
    startActivity(i)
    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    finish()
    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
}
