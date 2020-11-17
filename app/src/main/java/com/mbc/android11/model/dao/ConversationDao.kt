package com.mbc.android11.model.dao

import com.mbc.android11.model.Conversation
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.createObject
import io.realm.kotlin.where

class ConversationDao {

    private val realm = Realm.getDefaultInstance()

    fun create() = realm.createObject<Conversation>()
    fun get(conversationId: Int) =
        realm.where<Conversation>().equalTo("id", conversationId).findFirst()

    fun getAll(): RealmResults<Conversation> = realm.where<Conversation>().findAll()

    fun edit(block: (ConversationDao) -> Unit) {
        realm.executeTransaction {
            block(this)
        }
    }
}

