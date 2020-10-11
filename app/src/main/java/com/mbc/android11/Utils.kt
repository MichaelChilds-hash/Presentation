package com.mbc.android11

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import kotlin.reflect.KClass

fun AppCompatActivity.startActivity(clazz: KClass<out Activity>) {
    startActivity(Intent(this, clazz.java))
}