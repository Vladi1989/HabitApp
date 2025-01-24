package com.spase_y.habittracker.main.navigation_fragment.a

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.spase_y.habittracker.R
import java.time.LocalDate

class WeekPagerAdapter(
    private val weeks: List<List<LocalDate>>,
    private var selectedDate:LocalDate,
    private val onDayClick: (LocalDate) -> Unit
) : RecyclerView.Adapter<WeekPagerAdapter.WeekViewHolder>() {


    inner class WeekViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val dayViews = listOf<TextView>(
            view.findViewById(R.id.day1),
            view.findViewById(R.id.day2),
            view.findViewById(R.id.day3),
            view.findViewById(R.id.day4),
            view.findViewById(R.id.day5),
            view.findViewById(R.id.day6),
            view.findViewById(R.id.day7)
        )

        private val indicatorViews = listOf<View>(
            view.findViewById(R.id.indicator1),
            view.findViewById(R.id.indicator2),
            view.findViewById(R.id.indicator3),
            view.findViewById(R.id.indicator4),
            view.findViewById(R.id.indicator5),
            view.findViewById(R.id.indicator6),
            view.findViewById(R.id.indicator7)
        )

        fun bind(week: List<LocalDate>) {
            for ((index, date) in week.withIndex()) {
                // Установка числа
                dayViews[index].text = date.dayOfMonth.toString()

                // Видимость индикатора
                if (date == selectedDate) {
                    indicatorViews[index].visibility = View.VISIBLE
                } else {
                    indicatorViews[index].visibility = View.INVISIBLE
                }

                // Обработка кликов
                dayViews[index].setOnClickListener {
                    selectedDate = date // Сохраняем выбранную дату
                    notifyDataSetChanged() // Обновляем адаптер
                    onDayClick(date) // Передаём выбранную дату
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_week, parent, false)
        return WeekViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeekViewHolder, position: Int) {
        holder.bind(weeks[position])
    }

    override fun getItemCount(): Int = weeks.size
}