package com.mbc.android11.screens.friends

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mbc.android11.R
import com.mbc.android11.utils.viewModelsWithApplication
import kotlinx.android.synthetic.main.activity_friend.*

class FriendActivity : AppCompatActivity() {

    private val viewModel: FriendViewModel by viewModelsWithApplication()
    private val friendsAdapter = FriendsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend)

        list.adapter = friendsAdapter
        viewModel.users.observe(this, { friendsAdapter.data = it })
    }
}