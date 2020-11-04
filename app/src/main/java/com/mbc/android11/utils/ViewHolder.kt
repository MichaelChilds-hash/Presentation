package com.mbc.android11.utils

import android.content.Context
import android.view.View
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView

open class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    constructor(context: Context) : this(RelativeLayout(context))
}