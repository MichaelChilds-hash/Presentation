package com.mbc.android11.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "conversation")
data class ConversationEntity (
    @PrimaryKey val id: Int,
    val name: String,
    val imageRes: Int
)