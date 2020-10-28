package com.mbc.android11.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User (
    @PrimaryKey val id: Int,
    val firstName: String,
    val imageUri: String,
    val isMe: Boolean = false)
