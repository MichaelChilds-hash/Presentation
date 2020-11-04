package com.mbc.android11

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mbc.android11.database.AppRoomDb
import com.mbc.android11.database.entity.UserEntity
import com.mbc.android11.model.User
import com.mbc.android11.model.dao.UserDao

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val userDao = UserDao()
    val me = MutableLiveData<User>(userDao.getMe())
}