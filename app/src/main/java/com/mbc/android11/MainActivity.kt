package com.mbc.android11

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.mbc.android11.database.AppRoomDb
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFriend.setOnClickListener {
            startActivity(FriendlyActivity::class)
        }
        chat.setOnClickListener {
            startActivity(ChatActivity::class)
        }
        createNotificationChannel()

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val user = viewModel.me
        val user2 = viewModel.me

    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = CHANNEL_DESC
        }
        (getSystemService(NOTIFICATION_SERVICE) as NotificationManager)
            .createNotificationChannel(channel)
    }

    companion object {
        const val CHANNEL_NAME = "PrezoChannelName"
        const val CHANNEL_ID = "PrezoChannelId"
        const val CHANNEL_DESC = "A channel for presentation"
    }
}