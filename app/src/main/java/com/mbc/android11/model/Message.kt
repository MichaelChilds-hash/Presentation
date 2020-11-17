package com.mbc.android11.model

import androidx.core.text.parseAsHtml
import com.mbc.android11.model.dao.MessageDao
import io.realm.RealmObject

open class Message : RealmObject() {

    var id = 0; private set
    var fromId = 0; private set
    var convoId = 0; private set
    var content = ""; private set
    var sentAt = 0L; private set

    companion object {

        fun fromSendAction(dao: MessageDao, fromId: Int, convoId: Int, content: String) = dao.create().apply {
            id = dao.getAll().count()
            this.fromId = fromId
            this.convoId = convoId
            this.content = content
            sentAt = System.currentTimeMillis()
        }

        fun fromReceiveAction(dao: MessageDao, fromId: Int, content: String, convoId: Int = "${fromId}_".hashCode()) = dao.create().apply {
            id = dao.getAll().count()
            this.fromId = fromId
            this.convoId = convoId
            this.content = content
            sentAt = System.currentTimeMillis()
        }

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