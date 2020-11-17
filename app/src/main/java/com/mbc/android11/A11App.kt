package com.mbc.android11

import android.app.Application
import com.mbc.android11.model.Conversation
import com.mbc.android11.model.Message
import com.mbc.android11.model.User
import com.mbc.android11.model.dao.ConversationDao
import com.mbc.android11.model.dao.MessageDao
import com.mbc.android11.model.dao.UserDao
import com.mbc.android11.utils.Density
import io.realm.Realm
import io.realm.RealmConfiguration

class A11App : Application() {

    override fun onCreate() {
        super.onCreate()

        Density.value = resources.displayMetrics.density
        Realm.init(this)
        Realm.setDefaultConfiguration(RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build())


        createMockUsers()
        createMockConversations()
        createMockMessages()
    }

    private fun createMockUsers() {
        val dao = UserDao()
        if(dao.getAll().count() == 0) {
            dao.edit {
                var id = 0
                User.createMock(it, id++, "Michael", R.drawable.profile_me, isMe = true)
                User.createMock(it, id++, "Ethan", R.drawable.friend_ethan)
                User.createMock(it, id++, "Brent", R.drawable.friend_brent)
                User.createMock(it, id++, "Chris", R.drawable.friend_chris)
                User.createMock(it, id++, "Lana", R.drawable.friend_lana)
                User.createMock(it, id++, "Jay", R.drawable.ic_launcher_foreground)
                User.createMock(it, id++, "Jorge", R.drawable.ic_launcher_foreground)
                User.createMock(it, id++, "Thomas", R.drawable.ic_launcher_foreground)
            }
        }
    }

    private fun createMockConversations() {
        val userDao = UserDao()
        val dao = ConversationDao()
        if(dao.getAll().count() == 0) {
            dao.edit {
                Conversation.createMock(it, listOfNotNull(userDao.get(1)))
                Conversation.createMock(it, listOfNotNull(userDao.get(2)))
                Conversation.createMock(it, listOfNotNull(userDao.get(1), userDao.get(2)))
                Conversation.createMock(it, listOfNotNull(userDao.get(3), userDao.get(4)))
                Conversation.createMock(it, listOfNotNull(userDao.get(1), userDao.get(2), userDao.get(3)))
            }
        }
    }

    private fun createMockMessages() {
        val dao = MessageDao()
        val userDao = UserDao()
        if(dao.getAll().count() == 0) {
            dao.edit {
                var id = 0
                var convoId = Conversation.resolveConversationId(listOfNotNull(userDao.get(1)))
                Message.createMock(it, id++, 0, convoId, "Hi", System.currentTimeMillis() - 1_000_000L)
                Message.createMock(it, id++, 1, convoId, "Hello", System.currentTimeMillis() - 990_000L)
                Message.createMock(it, id++, 1, convoId, "What doing", System.currentTimeMillis() - 980_000L)
                Message.createMock(it, id++, 1, convoId, "Testing a long message to see what it looks like when I run the app blah blah blah blah bleh", System.currentTimeMillis() - 920_000L)
            }
        }
    }
}