package com.mbc.android11.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mbc.android11.database.entity.UserEntity

@Dao
interface UserDao {
    @Insert
    fun insert(userEntity: UserEntity)

    @Query("select * from user where isMe")
    fun getMe(): LiveData<UserEntity>

    @Query("select * from user")
    fun getUsers(): LiveData<List<UserEntity>>

    @Query("select * from user where not isMe")
    fun getNotMe(): LiveData<List<UserEntity>>

    @Query("delete from user")
    fun deleteAll()
}