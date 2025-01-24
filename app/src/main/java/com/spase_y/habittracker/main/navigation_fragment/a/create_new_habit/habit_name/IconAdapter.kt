package com.spase_y.habittracker.main.navigation_fragment.a.create_new_habit.habit_name

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.spase_y.habittracker.R

class IconAdapter(
    private val icons: List<Int>,
    private val onIconClick: (Int) -> Unit
) : RecyclerView.Adapter<IconAdapter.IconViewHolder>() {

    inner class IconViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iconImageView: ImageView = itemView.findViewById(R.id.iconImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IconViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_icon, parent, false)
        return IconViewHolder(view)
    }

    override fun onBindViewHolder(holder: IconViewHolder, position: Int) {
        holder.iconImageView.setImageResource(icons[position])
        holder.itemView.setOnClickListener {
            onIconClick(icons[position])
        }
    }

    override fun getItemCount(): Int = icons.size
}