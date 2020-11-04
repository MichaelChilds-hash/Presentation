package com.mbc.android11.screens.convo

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mbc.android11.model.Conversation

class ConvoAdapter(private val cellCallback: (Conversation) -> Unit) :
    RecyclerView.Adapter<ConversationCell>() {

    var data = listOf<Conversation>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ConversationCell(parent.context, cellCallback)

    override fun onBindViewHolder(holder: ConversationCell, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

}