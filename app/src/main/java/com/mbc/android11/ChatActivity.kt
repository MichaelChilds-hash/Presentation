package com.mbc.android11

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.mbc.android11.database.AppRoomDb

class ChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
    }
}