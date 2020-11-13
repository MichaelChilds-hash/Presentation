package com.mbc.android11.screens.chat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mbc.android11.model.Message
import com.mbc.android11.model.dao.ConversationDao
import com.mbc.android11.model.dao.MessageDao

class ChatViewModel(convoId: Int) : ViewModel() {
    val messageDao = MessageDao()
    val convoDao = ConversationDao()
    val chatData = MutableLiveData<List<Message>>()
}

class ChatViewModelFactory(val convoId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ChatViewModel(convoId) as T
    }
}