package com.mbc.android11.screens.convo

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mbc.android11.R
import com.mbc.android11.model.Conversation
import com.mbc.android11.screens.chat.ChatActivity
import kotlinx.android.synthetic.main.activity_conversations.*

class ConversationsActivity : AppCompatActivity() {

    private val viewModel: ConversationsViewModel by viewModels()
    private val convoAdapter = ConvoAdapter(this::onConvoPressed)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversations)

        convoList.adapter = convoAdapter
        viewModel.conversations.observe(this, { convoAdapter.data = it })
    }

    private fun onConvoPressed(convo: Conversation) {
        ChatActivity.show(this, convo)
    }
}