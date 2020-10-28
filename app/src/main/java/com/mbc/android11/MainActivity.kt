package com.mbc.android11

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModelsWithApplication()

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

        viewModel.me.observe(this, Observer { Log.e("please", it.firstName) })
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