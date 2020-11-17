package com.mbc.android11.screens.chat

import android.content.Context
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.setPadding
import com.mbc.android11.R
import com.mbc.android11.model.Message
import com.mbc.android11.utils.ViewHolder
import com.mbc.android11.utils.getColorCompat
import com.mbc.android11.utils.toPx
import java.text.SimpleDateFormat
import java.util.*

class ChatCell(val context: Context) : ViewHolder(context) {

    private val textParams = RelativeLayout.LayoutParams(
        RelativeLayout.LayoutParams.WRAP_CONTENT,
        RelativeLayout.LayoutParams.WRAP_CONTENT
    )
    private val text = AppCompatTextView(context)
    private val dateParams = RelativeLayout.LayoutParams(
        RelativeLayout.LayoutParams.WRAP_CONTENT,
        RelativeLayout.LayoutParams.WRAP_CONTENT
    )
    private val date = AppCompatTextView(context)

    init {
        textParams.setMargins(8.toPx(), 4.toPx(), 8.toPx(), 4.toPx())
        val layout = RelativeLayout(context)
        content.addView(
            layout,
            RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            )
        )
        layout.addView(text, textParams)
        text.setPadding(8.toPx())
        text.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16f)
        text.setBackgroundResource(R.drawable.bg_rounded_corners_24)
        text.id = View.generateViewId()
        layout.addView(date, dateParams)
        date.setPadding(8.toPx(), 2.toPx(), 8.toPx(), 2.toPx())
        date.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14f)
        dateParams.addRule(RelativeLayout.BELOW, text.id)
    }

    fun bind(message: Message, fromMe: Boolean) {
        if (fromMe) {
            textParams.setMargins(100.toPx(), 4.toPx(), 8.toPx(), 4.toPx())
            textParams.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE)
            textParams.removeRule(RelativeLayout.ALIGN_PARENT_START)
            dateParams.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE)
            dateParams.removeRule(RelativeLayout.ALIGN_PARENT_START)
            text.setTextColor(context.getColorCompat(R.color.messageFromMeText))
            text.gravity = Gravity.END
            text.background.setTint(context.getColorCompat(R.color.messageFromMeBg))
        } else {
            textParams.setMargins(8.toPx(), 4.toPx(), 100.toPx(), 4.toPx())
            textParams.addRule(RelativeLayout.ALIGN_PARENT_START, RelativeLayout.TRUE)
            textParams.removeRule(RelativeLayout.ALIGN_PARENT_END)
            dateParams.addRule(RelativeLayout.ALIGN_PARENT_START, RelativeLayout.TRUE)
            dateParams.removeRule(RelativeLayout.ALIGN_PARENT_END)
            text.setTextColor(context.getColorCompat(R.color.messageFromOtherText))
            text.gravity = Gravity.START
            text.background.setTint(context.getColorCompat(R.color.messageFromOtherBg))
        }

        text.text = message.content
        date.text = SimpleDateFormat("hh:mm").format(Date(message.sentAt))
    }
}