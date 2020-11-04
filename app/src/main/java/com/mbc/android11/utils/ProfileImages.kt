package com.mbc.android11.utils

import android.content.Context
import android.util.AttributeSet
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.mbc.android11.model.User


class ProfileImages @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    style: Int = 0
) : RelativeLayout(context, attributeSet, style) {

    private val imageViews = arrayListOf<AppCompatImageView>()

    fun setUsers(users: List<User>) {
        removeAllViews()
        when (users.size) {
            1 -> {
                val view = get(0)
                addView(view, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
                Glide.with(context).load(users[0].imageRes).circleCrop().into(view)
            }
            else -> {
                val baseSize = 48.toPx()
                val imageSize = (baseSize * if(users.size == 2) 0.66f else 0.5f).toInt()
                val maxTranslation = baseSize - imageSize
                users.forEachIndexed { i, user ->
                    val view = get(i)
                    addView(view, LayoutParams(imageSize, imageSize))
                    view.translationX = maxTranslation * (i / users.size.toFloat())
                    view.translationY = maxTranslation * (i / users.size.toFloat())
                    Glide.with(context).load(user.imageRes).circleCrop().into(view)
                }
            }
        }
    }

    private fun get(index: Int) =
        imageViews.getOrNull(index) ?: AppCompatImageView(context).also {
            if(index == 0) {
                it.importantForAccessibility = IMPORTANT_FOR_ACCESSIBILITY_YES
                it.contentDescription = "Profile"
            }
            imageViews.add(it)
        }
}