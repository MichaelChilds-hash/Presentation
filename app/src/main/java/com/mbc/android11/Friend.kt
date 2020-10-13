package com.mbc.android11

class Friend(
    val name: String,
    val iconRes: Int = R.drawable.friend_ethan
) {
    val id: String = name.hashCode().toString()

    companion object {
        const val TEST_NAME = "Ethan"
        fun createDefault() = Friend(TEST_NAME)
    }

}