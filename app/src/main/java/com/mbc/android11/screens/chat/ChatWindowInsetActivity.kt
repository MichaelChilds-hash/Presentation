package com.mbc.android11.screens.chat

import android.content.Context
import android.graphics.Insets
import android.os.Bundle
import android.util.TypedValue
import android.view.*
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.marginBottom
import androidx.core.view.updateLayoutParams
import androidx.core.view.updateMargins
import androidx.recyclerview.widget.RecyclerView
import com.mbc.android11.R
import kotlinx.android.synthetic.main.activity_chat.*


/**
 * Created by Michael on 18/11/20.
 */

class ChatWindowInsetActivity : AppCompatActivity() {

//    var scroll = 0
//    var animationController: WindowInsetsAnimationController? = null
//    val insetListener = object : WindowInsetsAnimationControlListener {
//        override fun onCancelled(p0: WindowInsetsAnimationController?) {
//            animationController = null
//        }
//
//        override fun onReady(p0: WindowInsetsAnimationController, p1: Int) {
//            animationController = p0
//        }
//
//        override fun onFinished(p0: WindowInsetsAnimationController) {
//            animationController = null
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        window.setDecorFitsSystemWindows(false)

        names.text = "Window Insets"
        val inputLayoutMarginBottom = edit.marginBottom
        val callback = object : WindowInsetsAnimation.Callback(DISPATCH_MODE_STOP) {
            override fun onProgress(
                insets: WindowInsets,
                animations: MutableList<WindowInsetsAnimation>
            ): WindowInsets {
                root.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                    updateMargins(
                        bottom = inputLayoutMarginBottom +
                                insets.getInsets(WindowInsets.Type.ime()).bottom
                    )
                }
                return insets
            }
        }
        edit.setWindowInsetsAnimationCallback(callback)
        send.setOnClickListener {
            root.windowInsetsController?.show(WindowInsets.Type.ime())
        }


//        window.insetsController?.controlWindowInsetsAnimation(
//            WindowInsets.Type.ime(),
//            -1,
//            null,
//            null,
//            insetListener
//        )
//        history.adapter = TextAdapter()
//        history.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                scroll += dy
//                scroll = scroll.coerceIn(0, 400)
//                animationController?.setInsetsAndAlpha(Insets.of(0, 0, 0, scroll), 1f, 0f)
//            }
//        })
    }
}

class TextAdapter : RecyclerView.Adapter<TextLayout>() {
    val data = (0..100).toList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TextLayout(parent.context)

    override fun onBindViewHolder(holder: TextLayout, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = data.size
}

class TextLayout(context: Context) : RecyclerView.ViewHolder(RelativeLayout(context)) {

    val text = AppCompatTextView(context)
    init {
        text.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20f)
        (itemView as? RelativeLayout)?.addView(text, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
    }
    fun bind(pos: Int) {
        text.text = pos.toString()
    }
}