package com.mbc.android11.model.dao

import com.mbc.android11.model.Message
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where

class MessageDao {

    private val realm = Realm.getDefaultInstance()

    fun create() = realm.createObject<Message>()

    fun get(messageId: Int) =
        realm.where<Message>().equalTo("id", messageId).findFirst()

    fun getFromConvo(convoId: Int): List<Message> =
        realm.where<Message>().equalTo("convoId", convoId).findAll()

    fun getAll() = realm.where<Message>().findAll()

    fun edit(block: (MessageDao) -> Unit) {
        realm.executeTransaction {
            block(this)
        }
    }
}