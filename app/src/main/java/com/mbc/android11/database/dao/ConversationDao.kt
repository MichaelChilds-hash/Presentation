package com.mbc.android11.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mbc.android11.database.entity.ConversationEntity
import com.mbc.android11.database.entity.UserConversationsEntity

@Dao
interface ConversationDao {

    @Query(
        """
            select conversation.*
            from user
            left join user_conversations on user_conversations.userId = user.id
            left join conversation on conversation.id = user_conversations.conversationId
            where user.id = :userId
        """
    )
    fun getAllForUser(userId: Int): LiveData<List<ConversationEntity>>

    @Query("select * from conversation")
    fun getAll(): LiveData<List<ConversationEntity>>

    @Query("select userId from user_conversations where user_conversations.conversationId = :conversationEntityId")
    fun getUsersInConversation(conversationEntityId: Int): LiveData<List<Int>>

    @Insert
    fun insert(conversationEntity: ConversationEntity)

    @Insert
    fun junction(userConversationsEntity: UserConversationsEntity)

    @Query("DELETE FROM conversation")
    fun deleteAll()

    @Query("DELETE FROM user_conversations")
    fun deleteJunctionTable()
}
