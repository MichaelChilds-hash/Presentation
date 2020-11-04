package com.mbc.android11.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mbc.android11.R

@Entity(tableName = "user")
data class UserEntity (
    @PrimaryKey val id: Int,
    val firstName: String,
    val imageRes: Int = R.drawable.ic_launcher_foreground,
    val isMe: Boolean = false)
