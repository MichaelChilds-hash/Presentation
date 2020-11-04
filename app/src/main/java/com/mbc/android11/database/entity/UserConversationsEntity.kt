package com.mbc.android11.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_conversations")
data class UserConversationsEntity(
    @PrimaryKey val id: Int,
    val conversationId: Int,
    val userId: Int
)
