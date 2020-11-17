package com.mbc.android11.screens.chat

import android.app.Activity
import android.content.pm.ShortcutManager
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mbc.android11.R
import com.mbc.android11.model.Conversation
import com.mbc.android11.utils.startActivity
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity() {

    private val viewModel: ChatViewModel by viewModels {
        val convoId =
            intent.data?.getQueryParameter("convoId")?.toIntOrNull() ?:
            intent.getIntExtra(CONVO_ID, -1)
        getSystemService(ShortcutManager::class.java).reportShortcutUsed(convoId.toString())
        ChatViewModelFactory(convoId)
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

        send.setOnClickListener {
            viewModel.onSendPressed(edit.text?.toString() ?: "")
            edit.text?.clear()
        }
        edit.setOnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                send.performClick()
                true
            } else false
        }

        viewModel.chatLiveData.observe(this, { chatAdapter.data = it })
        viewModel.chatUsers.observe(this, { users ->
            users?.let {
                pic.setUsers(users)
                names.text = users.map { it.name }.sorted().joinToString(", ")
            }
        })
    }
}