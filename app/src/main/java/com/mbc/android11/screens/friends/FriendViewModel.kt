package com.mbc.android11.screens.friends

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.mbc.android11.database.AppRoomDb
import com.mbc.android11.database.entity.UserEntity

class FriendViewModel(application: Application) : AndroidViewModel(application) {
    private val userDao = AppRoomDb.getInstance(application, viewModelScope).userDao()
    val users: LiveData<List<UserEntity>> = userDao.getNotMe()
}