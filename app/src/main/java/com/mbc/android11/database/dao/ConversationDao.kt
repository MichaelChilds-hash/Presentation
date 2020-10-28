package com.mbc.android11.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mbc.android11.model.Conversation

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
    fun getAllForUser(userId: Int): LiveData<List<Conversation>>

    @Insert
    fun insert(conversation: Conversation)

    @Query("DELETE FROM conversation")
    fun deleteAll()

    @Query("DELETE FROM user_conversations")
    fun deleteJunctionTable()
}
