package com.mbc.android11

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mbc.android11.database.AppRoomDb
import com.mbc.android11.model.User

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val userDao = AppRoomDb.getInstance(application, viewModelScope).userDao()
    val me: User = userDao.getMe()
}