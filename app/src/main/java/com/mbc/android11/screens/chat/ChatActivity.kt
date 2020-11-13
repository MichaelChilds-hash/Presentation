package com.mbc.android11.screens.chat

import android.app.Activity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mbc.android11.R
import com.mbc.android11.model.Conversation
import com.mbc.android11.utils.startActivity
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity() {

    private val viewModel: ChatViewModel by viewModels {
        ChatViewModelFactory(intent.getIntExtra(CONVO_ID, -1))
    }

    companion object {
        private const val CONVO_ID = "CONVO_ID"

        fun show(activity: Activity, convo: Conversation) {
            activity.startActivity(ChatActivity::class, Bundle().apply {
                putInt(CONVO_ID, convo.id)
            })
        }
    }

    private val chatAdapter = ChatAdapter(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        history.adapter = chatAdapter

        viewModel.chatData.observe(this, { chatAdapter.data = it })
    }
}