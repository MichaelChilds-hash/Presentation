package com.mbc.android11.screens.convo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mbc.android11.model.dao.ConversationDao

class ConversationsViewModel : ViewModel() {
    private val convoDao = ConversationDao()
    val conversations = MutableLiveData(convoDao.getAll())
}