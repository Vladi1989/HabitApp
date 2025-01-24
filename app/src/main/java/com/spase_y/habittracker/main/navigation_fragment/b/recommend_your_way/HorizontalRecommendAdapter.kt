package com.spase_y.habittracker.main.navigation_fragment.b.recommend_your_way

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.spase_y.habittracker.R

class HorizontalRecommendAdapter(
    private val items: List<HorizontalRecommendItem>,
    private val onItemHorizontalClick: (HorizontalRecommendItem) -> Unit
) : RecyclerView.Adapter<HorizontalRecommendAdapter.RecommendViewHolder>() {

    class RecommendViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.textView)
        val value: TextView = view.findViewById(R.id.textView2)
        val image: ImageView = view.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_your_way_horizontal, parent, false)
        return RecommendViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecommendViewHolder, position: Int) {
        val item = items[position]
        holder.title.text = item.title
        holder.value.text = item.value
        holder.image.setImageResource(item.imageRes)

        holder.itemView.setOnClickListener{
            onItemHorizontalClick(item)
        }
    }

    override fun getItemCount(): Int = items.size
}