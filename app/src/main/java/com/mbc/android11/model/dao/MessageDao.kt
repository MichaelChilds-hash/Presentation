package com.mbc.android11.model.dao

import com.mbc.android11.model.Message
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmResults
import io.realm.Sort
import io.realm.kotlin.createObject
import io.realm.kotlin.where

class MessageDao {

    private val realm = Realm.getDefaultInstance()

    fun create() = realm.createObject<Message>()
    fun createFromSendAction(fromId: Int, convoId: Int, content: String) =
        Message.fromSendAction(this, fromId, convoId, content)

    fun get(messageId: Int) =
        realm.where<Message>().equalTo("id", messageId).findFirst()

    fun getFromConvo(convoId: Int): RealmResults<Message> =
        realm.where<Message>().equalTo("convoId", convoId).sort("sentAt", Sort.DESCENDING).findAll()

    fun getAll() = realm.where<Message>().findAll()

    fun edit(block: (MessageDao) -> Unit) {
        realm.executeTransaction {
            block(this)
        }
    }
}