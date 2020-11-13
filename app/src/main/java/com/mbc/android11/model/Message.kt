package com.mbc.android11.model

import com.mbc.android11.model.dao.MessageDao
import io.realm.RealmObject

open class Message : RealmObject() {

    var id = 0
    var fromId = 0
    var convoId = 0
    var content = ""
    var sentAt = 0L

    companion object {
        fun createMock(
            dao: MessageDao,
            id: Int,
            fromId: Int,
            convoId: Int,
            content: String,
            sentAt: Long = System.currentTimeMillis()
        ) =
            (dao.get(id) ?: dao.create()).apply {
                this.id = id
                this.fromId = fromId
                this.convoId = convoId
                this.content = content
                this.sentAt = sentAt
            }
    }
}