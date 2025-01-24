package com.spase_y.habittracker.main.navigation_fragment.b.recommend_your_way

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.spase_y.habittracker.R

class VerticalRecommendAdapter(
    private val items: List<VerticalRecommendItem>,
    private val onItemClick: (VerticalRecommendItem) -> Unit
    ) : RecyclerView.Adapter<VerticalRecommendAdapter.VerticalViewHolder>() {

    class VerticalViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.imageViewVertical)
        val title: TextView = view.findViewById(R.id.tvNameVerticalItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_your_way_vertical, parent, false)
        return VerticalViewHolder(view)
    }

    override fun onBindViewHolder(holder: VerticalViewHolder, position: Int) {
        val item = items[position]
        holder.image.setImageResource(item.imageRes)
        holder.title.text = item.title

        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }
    override fun getItemCount(): Int = items.size
}