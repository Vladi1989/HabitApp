package com.spase_y.habittracker.main.navigation_fragment.c

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spase_y.habittracker.R
import com.spase_y.habittracker.data.HabitSettings
import com.spase_y.habittracker.data.HarmfulHabit
import com.spase_y.habittracker.data.OneTimeHabit
import com.spase_y.habittracker.data.RegularHabit
import com.spase_y.habittracker.databinding.ItemHabitBinding
import com.spase_y.habittracker.databinding.ItemHabitHistoryBinding


class HistoryAdapter(
    private val habits: List<HabitSettings>
) : RecyclerView.Adapter<HistoryAdapter.HabitViewHolder>() {

    inner class HabitViewHolder(private val binding: ItemHabitHistoryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(habitSettings: HabitSettings) {
            when(habitSettings) {
                is RegularHabit -> {
                    binding.textView.text = habitSettings.name
                    binding.imageView3.setImageResource(habitSettings.icon)
                    binding.clbg2.setBackgroundTintList(ColorStateList.valueOf(habitSettings.iconColor))
                }
                is OneTimeHabit -> {
                    binding.textView.text = habitSettings.name
                    binding.imageView3.setImageResource(habitSettings.icon)
                    binding.clbg2.setBackgroundTintList(ColorStateList.valueOf(habitSettings.iconColor))
                }
                is HarmfulHabit -> {
                    binding.textView.text = habitSettings.name
                    binding.imageView3.setImageResource(habitSettings.icon)
                    binding.clbg2.setBackgroundTintList(ColorStateList.valueOf(habitSettings.iconColor))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val binding = ItemHabitHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HabitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        holder.bind(habits[position])

    }

    override fun getItemCount(): Int = habits.size
}