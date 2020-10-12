package com.mbc.android11

class Friend(
    val name: String,
    val iconRes: Int = R.drawable.ic_launcher_foreground
) {
    val id: String = name.hashCode().toString()

    companion object {
        const val TEST_NAME = "Friend"
        fun createDefault() = Friend(TEST_NAME)
    }

}