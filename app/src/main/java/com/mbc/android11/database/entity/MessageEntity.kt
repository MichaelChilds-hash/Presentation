package com.mbc.android11.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message")
data class MessageEntity(
    @PrimaryKey val id: Int,
    val conversationId: Int,
    val senderId: Int,
    val content: String,
    val sentAt: Long
)
