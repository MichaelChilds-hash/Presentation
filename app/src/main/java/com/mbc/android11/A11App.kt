package com.mbc.android11

import android.app.Application
import com.mbc.android11.model.Conversation
import com.mbc.android11.model.User
import com.mbc.android11.model.dao.ConversationDao
import com.mbc.android11.model.dao.UserDao
import com.mbc.android11.utils.Density
import io.realm.Realm

class A11App : Application() {

    override fun onCreate() {
        super.onCreate()

        Density.value = resources.displayMetrics.density
        Realm.init(this)

        createMockUsers()
        createMockConversations()
    }

    private fun createMockUsers() {
        val dao = UserDao()
        if(dao.getAll().isEmpty()) {
            dao.edit {
                var id = 0
                User.createMock(it, id++, "Michael", R.drawable.profile_me, isMe = true)
                User.createMock(it, id++, "Ethan", R.drawable.friend_ethan)
                User.createMock(it, id++, "Brent", R.drawable.friend_brent)
                User.createMock(it, id++, "Chris", R.drawable.friend_chris)
                User.createMock(it, id++, "Lana", R.drawable.friend_lana)
            }
        }
    }

    private fun createMockConversations() {
        val userDao = UserDao()
        val dao = ConversationDao()
        if(dao.getAll().isEmpty()) {
            dao.edit {
                Conversation.createMock(it, listOfNotNull(userDao.get(1)))
                Conversation.createMock(it, listOfNotNull(userDao.get(2)))
                Conversation.createMock(it, listOfNotNull(userDao.get(1), userDao.get(2)))
                Conversation.createMock(it, listOfNotNull(userDao.get(3), userDao.get(4)))
                Conversation.createMock(it, listOfNotNull(userDao.get(1), userDao.get(2), userDao.get(3)))
            }
        }
    }
}