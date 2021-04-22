package com.rihoko.movieapp.list.movies

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MovieItemDecorator(var marginBottom: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (parent.getChildAdapterPosition(view) == state.itemCount - 1 || parent.getChildAdapterPosition(
                view
            ) == state.itemCount - 2
        ) {
            outRect.bottom = marginBottom + 16 //TODO convert to dp
        }
    }
}