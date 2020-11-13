package com.mbc.android11.screens.chat

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mbc.android11.model.Message

class ChatAdapter(private val myId: Int): RecyclerView.Adapter<ChatCell>() {

    var data: List<Message> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ChatCell(parent.context)

    override fun onBindViewHolder(holder: ChatCell, position: Int) {
        val message = data[position]
        holder.bind(message, message.fromId == myId)
    }

    override fun getItemCount() = data.size

}