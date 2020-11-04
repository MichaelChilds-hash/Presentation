package com.mbc.android11.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mbc.android11.database.entity.MessageEntity


@Dao
interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(messageEntity: MessageEntity)

    @Query("DELETE FROM message")
    fun deleteAll()
}