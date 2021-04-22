package com.rihoko.movieapp.list.actors

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ActorsItemDecorator : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.left = 8
        }
        if (parent.getChildAdapterPosition(view) == state.itemCount - 1) {
            outRect.right = 8
        }
    }
}