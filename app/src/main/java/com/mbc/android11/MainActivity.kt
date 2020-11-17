package com.mbc.android11

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.mbc.android11.screens.chat.ChatWindowInsetActivity
import com.mbc.android11.screens.convo.ConversationsActivity
import com.mbc.android11.utils.startActivity
import com.mbc.android11.utils.viewModelsWithApplication
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModelsWithApplication()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.me.observe(this, { profile.setUsers(listOf(it)) })
        profile.setOnClickListener { startActivity(ConversationsActivity::class) }
        settings.setOnClickListener { startActivity(ConversationsActivity::class) }
        camera.setOnClickListener { requestCam() }
        foreground.setOnClickListener { requestForegroundLocation() }
        background.setOnClickListener { requestBackgroundLocation() }
        addFriend.setOnClickListener { startActivity(FriendlyActivity::class) }
        conversations.setOnClickListener { startActivity(ConversationsActivity::class) }
        chatInsets.setOnClickListener { startActivity(ChatWindowInsetActivity::class) }

        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = CHANNEL_DESC
        }
        (getSystemService(NOTIFICATION_SERVICE) as NotificationManager)
            .createNotificationChannel(channel)
    }

    fun requestCam() {
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_REQUEST_CODE)
        } else {
            openCamera()
        }
    }

    fun requestForegroundLocation() {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), FOREGROUND_PERMISSION_REQUEST_CODE)
        } else {
            //openCamera()
        }
    }

    fun requestBackgroundLocation() {
        if (checkSelfPermission(Manifest.permission.ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_BACKGROUND_LOCATION), BACKGROUND_PERMISSION_REQUEST_CODE)
        } else {
            //openCamera()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode) {
            CAMERA_PERMISSION_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    openCamera()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode) {
            CAMERA_PHOTO_CODE -> {
                (data?.getExtras()?.get("data") as? Bitmap)?.let {
                    tempImage.setImageBitmap(it)
                }
            }
        }
    }

    fun openCamera() {
        startActivityForResult(Intent("android.media.action.IMAGE_CAPTURE"), CAMERA_PHOTO_CODE)
    }



    companion object {
        const val CHANNEL_NAME = "PrezoChannelName"
        const val CHANNEL_ID = "PrezoChannelId"
        const val CHANNEL_DESC = "A channel for presentation"

        const val CAMERA_PERMISSION_REQUEST_CODE = 100
        const val FOREGROUND_PERMISSION_REQUEST_CODE = 101
        const val BACKGROUND_PERMISSION_REQUEST_CODE = 102
        const val CAMERA_PHOTO_CODE = 1000
    }
}