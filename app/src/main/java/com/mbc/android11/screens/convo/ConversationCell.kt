package com.mbc.android11.screens.convo

import android.content.Context
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatTextView
import com.mbc.android11.R
import com.mbc.android11.model.Conversation
import com.mbc.android11.utils.ProfileImages
import com.mbc.android11.utils.ViewHolder

class ConversationCell(context: Context, private val callback: (Conversation) -> Unit) :
    ViewHolder(context) {

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.view_friend_cell, itemView as RelativeLayout, true)
    }

    fun bind(convo: Conversation) {
        itemView.findViewById<AppCompatTextView>(R.id.name).text = convo.name
        itemView.findViewById<ProfileImages>(R.id.pic).setUsers(convo.users)

        itemView.setOnClickListener { callback(convo) }
    }
}