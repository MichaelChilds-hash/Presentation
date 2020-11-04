package com.mbc.android11.screens.friends

import android.content.Context
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.mbc.android11.database.entity.UserEntity

class FriendsAdapter : RecyclerView.Adapter<FriendCell>() {

    var data: List<UserEntity> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FriendCell(parent.context)

    override fun onBindViewHolder(holder: FriendCell, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = data.size

}

class FriendCell(context: Context) : RecyclerView.ViewHolder(RelativeLayout(context)) {

    fun bind() {

    }
}