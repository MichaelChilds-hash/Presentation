package com.mbc.android11.utils

import android.app.Activity
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.reflect.KClass

object Density {
    var value = 1f
}
fun AppCompatActivity.startActivity(clazz: KClass<out Activity>) {
    startActivity(Intent(this, clazz.java))
}

@MainThread
inline fun <reified VM : ViewModel> ComponentActivity.viewModelsWithApplication(): Lazy<VM> {
    return viewModels { ViewModelProvider.AndroidViewModelFactory(application) }
}

fun Number.toPx() = (this.toFloat() * Density.value).toInt()