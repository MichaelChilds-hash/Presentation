package com.mbc.android11.model

import com.mbc.android11.R
import com.mbc.android11.model.dao.UserDao
import io.realm.RealmObject

open class User : RealmObject() {
    var id = 0; private set
    var name = ""; private set
    var imageRes = 0; private set
    var isMe = false; private set

    companion object {
        fun createMock(dao: UserDao, id: Int, name: String, imageRes: Int = R.drawable.friend_ethan, isMe: Boolean = false) {
            (dao.get(id) ?: dao.create()).apply {
                this.id = id
                this.name = name
                this.imageRes = imageRes
                this.isMe = isMe
            }
        }
    }
}