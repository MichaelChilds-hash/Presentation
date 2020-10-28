package com.mbc.android11.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message")
data class Message(
    @PrimaryKey val id: Int,
    val conversationId: Int,
    val senderId: Int,
    val content: String,
    val sentAt: Long
)
