package com.mbc.android11.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.annotation.ColorRes
import androidx.annotation.MainThread
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.reflect.KClass

object Density {
    var value = 1f
}

fun Activity.startActivity(clazz: KClass<out Activity>, bundle: Bundle? = null) {
    startActivity(Intent(this, clazz.java), bundle)
}

@MainThread
inline fun <reified VM : ViewModel> ComponentActivity.viewModelsWithApplication(): Lazy<VM> {
    return viewModels { ViewModelProvider.AndroidViewModelFactory(application) }
}

fun Number.toPx() = (this.toFloat() * Density.value).toInt()

fun Context.getColorCompat(@ColorRes colorRes: Int) = ContextCompat.getColor(this, colorRes)