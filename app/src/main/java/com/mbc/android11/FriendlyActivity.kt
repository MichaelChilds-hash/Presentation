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
import com.mbc.android11.model.Conversation
import com.mbc.android11.model.Message
import com.mbc.android11.model.dao.MessageDao
import com.mbc.android11.model.dao.UserDao
import kotlinx.android.synthetic.main.activity_add_friend.*
import java.util.*
import kotlin.concurrent.schedule

class FriendlyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_friend)

        addFriend.setOnClickListener { addFriend(1) }
        addFriend2.setOnClickListener { addFriend(2) }
        addFriend3.setOnClickListener { addFriend(3) }
        addFriend4.setOnClickListener { addFriend(4) }
        addFriend5.setOnClickListener { addFriend(5) }
        addFriend6.setOnClickListener { addFriend(6) }
        addFriend7.setOnClickListener { addFriend(7) }
        addGroup.setOnClickListener { addGroup() }
        messageFromFriend.setOnClickListener {
            Timer().schedule(2000) {
                messageFromFriend()
            }
        }
        messageFromGroup.setOnClickListener {
            Timer().schedule(4000) {
                messageFromGroup()
            }
        }
    }

    private fun addFriend(id: Int) {
        val friend = UserDao().get(id) ?: return
        val convoId = Conversation.resolveConversationId(listOf(friend))
        val shortcutIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("prezo://open/chat?convoId=${convoId}")
        )
        val personIcon = Icon.createWithResource(this, friend.imageRes)
        val person = Person.Builder()
            .setName(friend.name)
            .setIcon(personIcon)
            .build()

        val shortcut = ShortcutInfo.Builder(this, convoId.toString())
            .setLongLived(true)
            .setIntent(shortcutIntent)
            .setShortLabel("Chat")
            .setIcon(personIcon)
            .setPerson(person)
            .build()


        getSystemService(ShortcutManager::class.java).pushDynamicShortcut(shortcut)
    }

    private fun addGroup() {
        val chris = UserDao().get(3) ?: return
        val lana = UserDao().get(4) ?: return
        val convoId = Conversation.resolveConversationId(listOf(chris, lana))
        val shortcutIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("prezo://open/chat?convoId=${convoId}")
        )
        val chrisIcon = Icon.createWithResource(this, chris.imageRes)
        val lanaIcon = Icon.createWithResource(this, lana.imageRes)

        val person = Person.Builder()
            .setName(chris.name)
            .setIcon(chrisIcon)
            .build()
        val lanaPerson = Person.Builder()
            .setName(lana.name)
            .setIcon(lanaIcon)
            .build()

        val shortcut = ShortcutInfo.Builder(this, convoId.toString())
            .setLongLived(true)
            .setIntent(shortcutIntent)
            .setShortLabel("Chat")
            .setIcon(chrisIcon)
            .setPersons(arrayOf(person, lanaPerson))
            .build()


        getSystemService(ShortcutManager::class.java).pushDynamicShortcut(shortcut)
    }

    private fun messageFromFriend() {
        val friend = UserDao().get(1) ?: return
        val convoId = Conversation.resolveConversationId(listOf(friend))

        val content = "Hi ${(0..Int.MAX_VALUE).random()}"
        MessageDao().edit {
            Message.fromReceiveAction(it, 1, content)
        }
        val personIcon = Icon.createWithResource(this, friend.imageRes)

        val person = Person.Builder()
            .setName(friend.name)
            .setIcon(personIcon)
            .build()

        val bubble = Notification.BubbleMetadata.Builder(convoId.toString())
            .setDesiredHeightResId(R.dimen.bubbled_activity_desired_height)
            .build()
        val notification = Notification.Builder(this, MainActivity.CHANNEL_ID)
            .setStyle(
                Notification.MessagingStyle(person)
                    .addMessage(content, System.currentTimeMillis() - 1000L, person)
            )
            .setBubbleMetadata(bubble)
            .setShortcutId(convoId.toString())
            .setSmallIcon(friend.imageRes)
            .setContentTitle("My notification")
            .setContentText("Hello World!")
            .build()

        NotificationManagerCompat.from (this).notify(convoId, notification)
    }

    private fun messageFromGroup() {
        val lana = UserDao().get(4) ?: return
        val chris = UserDao().get(3) ?: return

        val convoId = Conversation.resolveConversationId(listOf(chris, lana))
        val chrisMessage = "My name is Chris"
        val lanaMessage = "My name is Lana."
        MessageDao().edit {
            Message.fromReceiveAction(it, 3, chrisMessage, convoId)
            Message.fromReceiveAction(it, 4, lanaMessage, convoId)
        }
        val personIcon = Icon.createWithResource(this, chris.imageRes)
        val lanaIcon = Icon.createWithResource(this, lana.imageRes)

        val person = Person.Builder()
            .setName(chris.name)
            .setIcon(personIcon)
            .build()
        val lanaPerson = Person.Builder()
            .setName(lana.name)
            .setIcon(lanaIcon)
            .build()

        val bubble = Notification.BubbleMetadata.Builder(convoId.toString())
            .setDesiredHeightResId(R.dimen.bubbled_activity_desired_height)
            .build()
        val notification = Notification.Builder(this, MainActivity.CHANNEL_ID)
            .setStyle(
                Notification.MessagingStyle(person)
                    .addMessage(lanaMessage, System.currentTimeMillis() - 10000L, lanaPerson)
                    .addMessage(chrisMessage, System.currentTimeMillis() - 5000L, person)
            )
            .setBubbleMetadata(bubble)
            .setShortcutId(convoId.toString())
            .setSmallIcon(chris.imageRes)
            .setContentTitle("My notification")
            .setContentText("Hello World!")
            .build()

        NotificationManagerCompat.from (this).notify(convoId, notification)
    }
}