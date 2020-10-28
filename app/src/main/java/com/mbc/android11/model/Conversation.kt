package com.mbc.android11.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "conversation")
data class Conversation (
    @PrimaryKey val id: Int,
    val name: String
)