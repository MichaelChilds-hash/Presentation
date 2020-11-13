package com.mbc.android11.screens.chat

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.setPadding
import com.mbc.android11.R
import com.mbc.android11.model.Message
import com.mbc.android11.utils.ViewHolder
import com.mbc.android11.utils.getColorCompat
import com.mbc.android11.utils.toPx

class ChatCell(val context: Context) : ViewHolder(context) {

    private val text = AppCompatTextView(context)

    init {
        content.setBackgroundResource(R.drawable.bg_rounded_corners_24)

        val layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(8.toPx(), 4.toPx(), 8.toPx(), 4.toPx())
        content.addView(text, layoutParams)
        text.setPadding(8.toPx())

    }

    fun bind(message: Message, fromMe: Boolean) {
        if (fromMe) {
            text.setTextColor(context.getColorCompat(R.color.messageFromMeText))
            content.background.setTint(context.getColorCompat(R.color.messageFromMeBg))
        } else {
            text.setTextColor(context.getColorCompat(R.color.messageFromOtherText))
            content.background.setTint(context.getColorCompat(R.color.messageFromOtherBg))
        }

        text.text = message.content
    }
}