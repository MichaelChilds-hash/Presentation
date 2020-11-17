package com.mbc.android11.screens.chat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mbc.android11.model.Message
import com.mbc.android11.model.dao.ConversationDao
import com.mbc.android11.model.dao.MessageDao
import com.mbc.android11.model.dao.UserDao
import io.realm.RealmChangeListener
import io.realm.RealmResults

class ChatViewModel(private val convoId: Int) : ViewModel(),
    RealmChangeListener<RealmResults<Message>> {
    val messageDao = MessageDao()
    val convoDao = ConversationDao()
    val userDao = UserDao()
    private val chatRealmMessages = messageDao.getFromConvo(convoId)
    val chatLiveData = MutableLiveData(chatRealmMessages)
    private val chatRealmUsers = convoDao.get(convoId)?.users
    val chatUsers = MutableLiveData(chatRealmUsers)

    init {
        chatRealmMessages.addChangeListener(this)
    }

    override fun onChange(t: RealmResults<Message>) {
        chatLiveData.value = chatRealmMessages
        chatUsers.value = chatRealmUsers
    }

    override fun onCleared() {
        super.onCleared()
        chatRealmMessages.removeChangeListener(this)
    }

    fun onSendPressed(contents: String) {
        messageDao.edit {
            messageDao.createFromSendAction(userDao.getMe().id, convoId, contents)
        }
    }

}

class ChatViewModelFactory(val convoId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ChatViewModel(convoId) as T
    }
}