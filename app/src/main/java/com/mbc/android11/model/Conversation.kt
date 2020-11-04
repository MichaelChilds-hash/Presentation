package com.mbc.android11.model

import com.mbc.android11.model.dao.ConversationDao
import io.realm.RealmList
import io.realm.RealmObject

open class Conversation : RealmObject() {
    var id = 0; private set
    var users = RealmList<User>(); private set

    val name get() = users.asSequence().sortedBy { it.name }.map { it.name }.joinToString(", ")

    companion object {

        fun resolveConversationId(users: List<User>) = users.sortedBy { it.id }.joinToString { "${it.id}_" }.hashCode()

        fun createMock(dao: ConversationDao, users: List<User>) {
            val id = resolveConversationId(users)
            (dao.get(id) ?: dao.create()).apply {
                this.id = id
                this.users.clear()
                this.users.addAll(users)
            }
        }
    }
}


