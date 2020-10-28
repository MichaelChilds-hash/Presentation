package com.mbc.android11

import android.app.Activity
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import kotlin.reflect.KClass

fun AppCompatActivity.startActivity(clazz: KClass<out Activity>) {
    startActivity(Intent(this, clazz.java))
}

@MainThread
inline fun <reified VM : ViewModel> ComponentActivity.viewModelsWithApplication(): Lazy<VM> {
    return viewModels { ViewModelProvider.AndroidViewModelFactory(application) }
}