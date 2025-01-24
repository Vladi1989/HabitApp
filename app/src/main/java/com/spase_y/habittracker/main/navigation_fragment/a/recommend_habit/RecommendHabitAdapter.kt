package com.spase_y.habittracker.main.navigation_fragment.a.recommend_habit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.spase_y.habittracker.R

class RecommendHabitAdapter(private val items: List<Habit>) :
    RecyclerView.Adapter<RecommendHabitAdapter.HabitViewHolder>() {

    inner class HabitViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageViewIcon: ImageView = view.findViewById(R.id.imageView5)
        val textViewTitle: TextView = view.findViewById(R.id.textView15)
        val textViewDescription: TextView = view.findViewById(R.id.textView16)
        val imageViewArrow: ImageView = view.findViewById(R.id.imageView6)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recommend_habits, parent, false)
        return HabitViewHolder(view)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val habit = items[position]
        holder.textViewTitle.text = habit.title
        holder.textViewDescription.text = habit.description
        holder.imageViewIcon.setImageResource(habit.iconResId)
        holder.imageViewArrow.setImageResource(habit.arrowResId)
    }

    override fun getItemCount(): Int = items.size
}