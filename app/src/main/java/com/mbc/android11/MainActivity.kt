package com.mbc.android11

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Bundle
import android.util.Log
import android.view.textclassifier.ConversationAction
import androidx.appcompat.app.AppCompatActivity
import com.mbc.android11.screens.convo.ConversationsActivity
import com.mbc.android11.screens.friends.FriendActivity
import com.mbc.android11.utils.startActivity
import com.mbc.android11.utils.viewModelsWithApplication
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModelsWithApplication()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.me.observe(this, { profile.setUsers(listOf(it)) })
        profile.setOnClickListener { startActivity(ConversationsActivity::class) }
        settings.setOnClickListener { startActivity(ConversationsActivity::class) }
        friends.setOnClickListener { startActivity(FriendActivity::class) }
        addFriend.setOnClickListener { startActivity(FriendlyActivity::class) }
        conversations.setOnClickListener { startActivity(ConversationsActivity::class) }

        createNotificationChannel()
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