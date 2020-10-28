package com.mbc.android11.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_conversations")
data class UserConversations(
    @PrimaryKey val id: Int,
    val conversationId: Int,
    val userId: Int
)
