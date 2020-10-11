package com.mbc.android11

import android.app.Person
import android.content.Context
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_trigger_shortcut.*

class TriggerShortcutActivity : AppCompatActivity() {

    companion object {
        const val SHORTCUT_ID = "FriendShortcutId"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trigger_shortcut)
        triggerShortcut.setOnClickListener {
            onTriggerShortcut()
        }
    }

    private fun onTriggerShortcut() {
        val shortcutIntent = Intent(this, Shortcut::class.java)
        shortcutIntent.action = SHORTCUT_ID
        val personIcon = Icon.createWithResource(this, R.drawable.ic_launcher_foreground)
        val person = Person.Builder()
            .setName("Friend")
            .setIcon(personIcon)
            .build()

        val shortcut = ShortcutInfo.Builder(this, SHORTCUT_ID)
            .setLongLived(true)
            .setIntent(shortcutIntent)
            .setShortLabel("Short Label")
            .setIcon(personIcon)
            .setPerson(person)
            //.setCategories() //sharing
            .build()

        (getSystemService(Context.SHORTCUT_SERVICE) as? ShortcutManager)
            ?.pushDynamicShortcut(shortcut)
    }
}