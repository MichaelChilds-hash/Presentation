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
import kotlinx.android.synthetic.main.activity_add_friend.*

class FriendlyActivity : AppCompatActivity() {

    companion object {
        const val CHANNEL_ID = "FriendlyChannelId_"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_friend)

        addFriend.setOnClickListener {
            addFriend()
        }
        notificationFromFriend.setOnClickListener {
            notificationFromFriend()
        }
    }

    private fun addFriend() {
        val friend = Friend.createDefault()
        val shortcutIntent = Intent(Intent.ACTION_VIEW,
            Uri.parse("prezo://open/chat?${friend.id}"))
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

    private fun notificationFromFriend() {
        val friend = Friend.createDefault()
        val personIcon = Icon.createWithResource(this, friend.iconRes)
        val person = Person.Builder()
            .setName(friend.name)
            .setIcon(personIcon)
            .build()

        val notification = Notification.Builder(this, "$CHANNEL_ID${friend.id}")
            .setStyle(Notification.MessagingStyle(person)
                .addMessage("Hi", System.currentTimeMillis()-20000L, person)
                .addMessage("... Hello?", System.currentTimeMillis()-5000L, person)
            )
            .setShortcutId(friend.id)
            .build()
    }
}