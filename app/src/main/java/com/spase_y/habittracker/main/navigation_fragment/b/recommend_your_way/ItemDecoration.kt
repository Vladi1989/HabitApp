package com.spase_y.habittracker.main.navigation_fragment.b.recommend_your_way

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class EdgeSpacingItemDecoration(
    private val sidePadding: Int // Отступы по краям
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        val itemCount = parent.adapter?.itemCount ?: 0

        // Добавляем отступы
        when (position) {
            0 -> outRect.left = sidePadding // Для первого элемента
            itemCount - 1 -> outRect.right = sidePadding // Для последнего элемента
        }
    }
}