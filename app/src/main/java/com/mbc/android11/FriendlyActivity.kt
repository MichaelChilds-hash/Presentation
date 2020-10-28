package com.mbc.android11

import android.app.Notification
import android.app.Person
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_add_friend.*
import java.util.*
import kotlin.concurrent.schedule

class FriendlyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_friend)

        addFriend.setOnClickListener {
            addFriend()
        }
        messageFromFriend.setOnClickListener {
            Timer().schedule(2000) {
                messageFromFriend()
            }
        }
    }

    private fun addFriend() {
        val friend = Friend.createDefault()
        val shortcutIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("prezo://open/chat?${friend.id}")
        )
        val personIcon = Icon.createWithResource(this, friend.iconRes)
        val person = Person.Builder()
            .setName(friend.name)
            .setIcon(personIcon)
            .build()

        val shortcut = ShortcutInfo.Builder(this, friend.id)
            .setLongLived(true)
            .setIntent(shortcutIntent)
            .setShortLabel("Chat")
            .setIcon(personIcon)
            .setPerson(person)
            //.setCategories() //for sharing types probably won't implement
            .build()


        getSystemService(ShortcutManager::class.java).pushDynamicShortcut(shortcut)
    }

    private fun messageFromFriend() {
        val friend = Friend.createDefault()
        val personIcon = Icon.createWithResource(this, friend.iconRes)

        val person = Person.Builder()
            .setName(friend.name)
            .setIcon(personIcon)
            .build()

        val bubble = Notification.BubbleMetadata.Builder(friend.id).build()
        val notification = Notification.Builder(this, MainActivity.CHANNEL_ID)
            .setStyle(
                Notification.MessagingStyle(person)
                    .addMessage("Hi ${(0..Int.MAX_VALUE).random()}", System.currentTimeMillis() - 1000L, person)
            )
            .setBubbleMetadata(bubble)
            .setShortcutId(friend.id)
            .setSmallIcon(friend.iconRes)
            .setContentTitle("My notification")
            .setContentText("Hello World!")
            .build()

        NotificationManagerCompat.from (this).notify(friend.id.toIntOrNull() ?: 0, notification)
    }
}