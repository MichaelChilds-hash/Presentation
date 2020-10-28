package com.mbc.android11.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mbc.android11.database.dao.ConversationDao
import com.mbc.android11.database.dao.MessageDao
import com.mbc.android11.database.dao.UserDao
import com.mbc.android11.model.Conversation
import com.mbc.android11.model.Message
import com.mbc.android11.model.User
import com.mbc.android11.model.UserConversations
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Message::class, User::class, Conversation::class, UserConversations::class], version = 1)
abstract class AppRoomDb : RoomDatabase() {
    companion object {
        const val NAME = "a11-database"

        @Volatile
        private var dbInstance: AppRoomDb? = null

        fun getInstance(context: Context, scope: CoroutineScope): AppRoomDb {
            return dbInstance ?: synchronized(this) {
                buildDatabase(context.applicationContext, scope).also {
                    dbInstance = it
                }
            }
        }

        private fun buildDatabase(appContext: Context, scope: CoroutineScope): AppRoomDb {
            return Room.databaseBuilder(appContext, AppRoomDb::class.java, NAME)
                .fallbackToDestructiveMigration()
                .addCallback(DatabaseCallback(scope))
                .build()
        }
    }

    private class DatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            dbInstance?.let { database ->
                scope.launch(Dispatchers.IO) {
                    addUsers(database.userDao())
                    addConversations(database.conversationDao())
                    addMessages(database.messageDao())
                }
            }
        }

        private fun addConversations(conversationDao: ConversationDao) {
            val test01 = "0_1".hashCode()
            val test02 = "0_2".hashCode()
            conversationDao.deleteAll()
            conversationDao.deleteJunctionTable()
            conversationDao.insert(Conversation("0_1".hashCode(), ""))
        }

        private fun addUsers(userDao: UserDao) {
            var id = 0
            userDao.deleteAll()
            userDao.insert(User(id++, "Michael", "", true))
            userDao.insert(User(id++, "Ethan", ""))
            userDao.insert(User(id++, "Brent", ""))
            userDao.insert(User(id++, "Chris", ""))
            userDao.insert(User(id++, "David", ""))
            userDao.insert(User(id++, "Guilherme", ""))
            userDao.insert(User(id++, "Jorge", ""))
            userDao.insert(User(id++, "Kate", ""))
            userDao.insert(User(id++, "Lana", ""))
            userDao.insert(User(id++, "Richard", ""))
            userDao.insert(User(id++, "Taher", ""))
            userDao.insert(User(id++, "Thomas", ""))
            userDao.insert(User(id++, "Vlad", ""))
        }

        private fun addMessages(messageDao: MessageDao) {
           messageDao.deleteAll()
           messageDao.insert(Message(0, 0, 0, "Hi", System.currentTimeMillis() - 1_000_000))
           messageDao.insert(Message(1, 0, 0, "How are you?", System.currentTimeMillis() - 995_000))
           messageDao.insert(Message(2, 0, 1, "Hey, good thanks, and you?", System.currentTimeMillis() - 980_000))
           messageDao.insert(Message(3, 0, 1, "Did thingy do the thing", System.currentTimeMillis() - 975_000))
           messageDao.insert(Message(4, 0, 0, "Nah, said maybe later today", System.currentTimeMillis() - 965_000))
        }
    }

    abstract fun userDao(): UserDao
    abstract fun conversationDao(): ConversationDao
    abstract fun messageDao(): MessageDao
}