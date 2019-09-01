package com.vverbytskyi.codingtask.ui.common.decorators

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

class VerticalSpaceItemDecoration(
    context: Context,
    @DimenRes verticalOffset: Int,
    private val drawFirst: Boolean = false,
    private val drawLast: Boolean = false
) : RecyclerView.ItemDecoration() {

    private var verticalOffsetPixelSie = context.resources.getDimensionPixelSize(verticalOffset)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val itemLastIndex = parent.adapter?.itemCount?.dec()

        when (parent.getChildAdapterPosition(view)) {
            0 -> if (drawFirst) addBottomOffset(outRect)
            itemLastIndex -> if (drawLast) addBottomOffset(outRect)
            else -> addBottomOffset(outRect)
        }
    }

    private fun addBottomOffset(outRect: Rect) {
        outRect.bottom = verticalOffsetPixelSie
    }
}
