package com.mbc.android11.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mbc.android11.R
import com.mbc.android11.database.dao.ConversationDao
import com.mbc.android11.database.dao.MessageDao
import com.mbc.android11.database.dao.UserDao
import com.mbc.android11.database.entity.ConversationEntity
import com.mbc.android11.database.entity.MessageEntity
import com.mbc.android11.database.entity.UserEntity
import com.mbc.android11.database.entity.UserConversationsEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [MessageEntity::class, UserEntity::class, ConversationEntity::class, UserConversationsEntity::class], version = 1)
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
            conversationDao.deleteAll()
            conversationDao.deleteJunctionTable()
            val convoId = "0_1".hashCode()
            conversationDao.insert(ConversationEntity(convoId, "Ethan Convo", R.drawable.friend_ethan))
            conversationDao.junction(UserConversationsEntity(0, convoId, 0))
            conversationDao.junction(UserConversationsEntity(1, convoId, 1))
        }

        private fun addUsers(userDao: UserDao) {
            var id = 0
            userDao.deleteAll()
            userDao.insert(UserEntity(id++, "Michael", 0, true))
            userDao.insert(UserEntity(id++, "Ethan", R.drawable.friend_ethan))
            userDao.insert(UserEntity(id++, "Brent"))
            userDao.insert(UserEntity(id++, "Chris"))
            userDao.insert(UserEntity(id++, "David"))
            userDao.insert(UserEntity(id++, "Guilherme"))
            userDao.insert(UserEntity(id++, "Jorge"))
            userDao.insert(UserEntity(id++, "Kate"))
            userDao.insert(UserEntity(id++, "Lana"))
            userDao.insert(UserEntity(id++, "Richard"))
            userDao.insert(UserEntity(id++, "Taher"))
            userDao.insert(UserEntity(id++, "Thomas"))
            userDao.insert(UserEntity(id++, "Vlad"))
        }

        private fun addMessages(messageDao: MessageDao) {
           messageDao.deleteAll()
           messageDao.insert(MessageEntity(0, 0, 0, "Hi", System.currentTimeMillis() - 1_000_000))
           messageDao.insert(MessageEntity(1, 0, 0, "How are you?", System.currentTimeMillis() - 995_000))
           messageDao.insert(MessageEntity(2, 0, 1, "Hey, good thanks, and you?", System.currentTimeMillis() - 980_000))
           messageDao.insert(MessageEntity(3, 0, 1, "Did thingy do the thing", System.currentTimeMillis() - 975_000))
           messageDao.insert(MessageEntity(4, 0, 0, "Nah, said maybe later today", System.currentTimeMillis() - 965_000))
        }
    }

    abstract fun userDao(): UserDao
    abstract fun conversationDao(): ConversationDao
    abstract fun messageDao(): MessageDao
}