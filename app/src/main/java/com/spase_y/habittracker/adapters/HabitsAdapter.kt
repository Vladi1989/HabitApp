package com.spase_y.habittracker.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spase_y.habittracker.data.HabitSettings
import com.spase_y.habittracker.data.HarmfulHabit
import com.spase_y.habittracker.data.OneTimeHabit
import com.spase_y.habittracker.data.RegularHabit
import com.spase_y.habittracker.databinding.ItemHabitBinding
import com.spase_y.habittracker.databinding.TextBinding

class HabitsAdapter(): RecyclerView.Adapter<HabitsAdapter.VH>() {

    var listHabits = listOf<HabitSettings>()
    inner class VH(val binding: ItemHabitBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(habitSettings: HabitSettings) {

            when(habitSettings) {
                is RegularHabit -> {
                    binding.textView.text = habitSettings.name
                    binding.imageView3.setImageResource(habitSettings.icon)
                    binding.imageView3.drawable?.setTint(habitSettings.iconColor)
                }
                is OneTimeHabit -> {
                    binding.textView.text = habitSettings.name
                    binding.imageView3.setImageResource(habitSettings.icon)
                    binding.imageView3.drawable?.setTint(habitSettings.iconColor)
                }
                is HarmfulHabit -> {
                    binding.textView.text = habitSettings.name
                    binding.imageView3.setImageResource(habitSettings.icon)
                    binding.imageView3.drawable?.setTint(habitSettings.iconColor)
                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemHabitBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return listHabits.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(listHabits[position])
    }
}