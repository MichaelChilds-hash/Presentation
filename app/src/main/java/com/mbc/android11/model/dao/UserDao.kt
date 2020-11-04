package com.mbc.android11.model.dao

import com.mbc.android11.R
import com.mbc.android11.model.User
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where

class UserDao {

    private val realm = Realm.getDefaultInstance()

    fun create() = realm.createObject<User>()
    fun get(userId: Int) = realm.where<User>().equalTo("id", userId).findFirst()
    fun getAll(): List<User> = realm.where<User>().findAll()
    fun getMe() = realm.where<User>().equalTo("isMe", true).findFirst()

    fun edit(block: (UserDao) -> Unit) {
        realm.executeTransaction {
            block(this)
        }
    }
}