package com.mbc.android11.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mbc.android11.model.User

@Dao
interface UserDao {
    @Insert
    fun insert(user: User)

    @Query("select * from user where isMe")
    fun getMe(): User

    @Query("delete from user")
    fun deleteAll()
}